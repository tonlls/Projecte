package com.app.socis;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import com.app.socis.input_obj.info_atraccions_obj;
import com.app.socis.input_obj.info_parcs_obj;
import com.app.socis.model.atraccio;
import com.app.socis.model.parc;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.utils.StorageUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,IActivity, atraccioFragment.OnListFragmentInteractionListener, SearchView.OnQueryTextListener {
	public boolean updating=false;
	public List<parc> parcs;
	public HashMap<Integer,ArrayList<atraccio>> atraccions=new HashMap<Integer,ArrayList<atraccio>>();
	private UpdateTask ut;
	private MainActivity.MyPageAdapter pa;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Toolbar toolbar = findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		DrawerLayout drawer = findViewById(R.id.drawer_layout);
		NavigationView navigationView = findViewById(R.id.nav_view);
		ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
				this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
		drawer.addDrawerListener(toggle);
		toggle.syncState();
		navigationView.setNavigationItemSelectedListener(this);
		File cacheDir = StorageUtils.getCacheDirectory(this);
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
				.memoryCacheExtraOptions(480, 800)
				.diskCacheExtraOptions(4800, 1800, null)
				.threadPoolSize(5) // default
				.denyCacheImageMultipleSizesInMemory()
				.memoryCacheSize(200 * 1024 * 1024)
				.memoryCacheSizePercentage(13) // default
				.diskCache(new UnlimitedDiskCache(cacheDir)) // default
				.diskCacheSize(500 * 1024 * 1024)
				.diskCacheFileCount(100)
				.build();
		ImageLoader.getInstance().init(config);
		Server at = new Server(this, info_parcs_obj.class);
		at.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Request("getInfoParcs",new Object[0]));
	}

	@Override
	public void onBackPressed() {
		DrawerLayout drawer = findViewById(R.id.drawer_layout);
		if (drawer.isDrawerOpen(GravityCompat.START)) {
			drawer.closeDrawer(GravityCompat.START);
		} else {
			super.onBackPressed();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		SearchManager searchManager = (SearchManager)
				getSystemService(Context.SEARCH_SERVICE);
		MenuItem searchMenuItem = menu.findItem(R.id.search);
		SearchView searchView = (SearchView) searchMenuItem.getActionView();

		searchView.setSearchableInfo(searchManager.
				getSearchableInfo(getComponentName()));
		searchView.setOnQueryTextListener(this);
		return true;
	}


	@Override
	public boolean onNavigationItemSelected(MenuItem item) {
		// Handle navigation view item clicks here.
		int id = item.getItemId();

		if (id == R.id.nav_passis) {
			ut.Stop();
			Intent intent = new Intent(this, PassisActivity.class);
			startActivity(intent);
		} else if (id == R.id.nav_atraccions) {
			Intent intent = new Intent(this, MainActivity.class);
			startActivity(intent);
		}

		DrawerLayout drawer = findViewById(R.id.drawer_layout);
		//drawer.closeDrawer(GravityCompat.START);
		return true;
	}
	@Override
	public void setResult(Object o, Class c,int extra) {
		if(c== info_parcs_obj.class){
			parcs=((info_parcs_obj)o).parcs;

			for(parc p:parcs){
				Object[] arr=new Object[1];
				Server at = new Server(this, info_atraccions_obj.class);
				arr[0]=new Integer(p.id);
				at.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Request("getInfoAtraccions",arr));
			}
		}
		else if(c==info_atraccions_obj.class){
			info_atraccions_obj a=((info_atraccions_obj)o);
			atraccions.put(extra,(ArrayList)a.estats_atraccions);
			if(atraccions.size()==parcs.size()){
				pa=new MainActivity.MyPageAdapter(getSupportFragmentManager(),parcs,atraccions);
				((ViewPager)findViewById(R.id.pager)).setAdapter(pa);
				((TabLayout)findViewById(R.id.tabL)).setupWithViewPager((ViewPager)findViewById(R.id.pager));
				ut=new UpdateTask(this);
				ut.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,atraccions);
			}
		}
	}
	@Override
	public void onListFragmentInteraction(atraccio a) {
		Intent myIntent = new Intent(this, DetallActivity.class);
		myIntent.putExtra("ATRACCIO",a);
		this.startActivity(myIntent);
	}

	public void updateHash() {
		pa.notifiChanges();
	}

	@Override
	public boolean onQueryTextSubmit(String s) {
		if(s.length()!=0) {
			if(ut!=null)ut.Stop();
			for (int i : atraccions.keySet()) {
				ArrayList<atraccio> a = new ArrayList<atraccio>();
				for (atraccio at : atraccions.get(i)) {
					if (at.nom.contains(s)) a.add(at);
				}
				atraccions.put(i, a);
			}
			updateHash();
		}
		return false;
	}

	@Override
	public boolean onQueryTextChange(String s) {
		if (s.length()==0)ut.Play();
		return false;
	}

	public static class MyPageAdapter extends FragmentPagerAdapter {
		private final HashMap<Integer, ArrayList<atraccio>> atraccions;
		List<atraccioFragment> fragments=new ArrayList<atraccioFragment>();
		public void notifiChanges(){
			for (atraccioFragment f:fragments){
				f.update();
			}
		}
		public ArrayList<atraccio> getAtraccions(int parc) {
			return atraccions.get(parc);
		}

		List<parc> parcs;
		public MyPageAdapter(FragmentManager fm, List<parc> parcs, HashMap<Integer, ArrayList<atraccio>> atrr) {
			super(fm);
			this.parcs=parcs;
			this.atraccions=atrr;
		}

		@Override
		public Fragment getItem(int i) {
			parc p=parcs.get(i);
			atraccioFragment ff=atraccioFragment.newInstance(p,atraccions);
			fragments.add(ff);
			return ff;
		}

		@Override
		public int getCount() {
			return parcs.size();
		}
		public CharSequence getPageTitle(int position) {
			return parcs.get(position).nom;
		}
	}
}
