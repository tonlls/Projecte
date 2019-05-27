package com.app.socis;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TableLayout;

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

public class MainActivity extends AppCompatActivity implements atraccioFragment.OnListFragmentInteractionListener,IActivity {
	public boolean updating=false;
	public List<parc> parcs;
	public HashMap<Integer,ArrayList<atraccio>> atraccions=new HashMap<Integer,ArrayList<atraccio>>();
	private UpdateTask ut;
	private MyFuckingPageAdapter pa;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
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
		//pgrLoading.setVisibility(View.VISIBLE);
		//((RecyclerView)findViewById(R.id.recicler)).setLayoutManager(new LinearLayoutManager(this));
		//requestParcs();
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
				pa=new MyFuckingPageAdapter(getSupportFragmentManager(),parcs,atraccions);
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

	public static class MyFuckingPageAdapter extends FragmentPagerAdapter{
		private final HashMap<Integer, ArrayList<atraccio>> atraccions;
		public void notifiChanges(){
			for (int i=0;i<getCount();i++){
				((atraccioFragment)getItem(i)).update();
			}
		}
		public ArrayList<atraccio> getAtraccions(int parc) {
			return atraccions.get(parc);
		}

		List<parc> parcs;
		public MyFuckingPageAdapter(FragmentManager fm, List<parc> parcs, HashMap<Integer, ArrayList<atraccio>> atrr) {
			super(fm);
			this.parcs=parcs;
			this.atraccions=atrr;
		}

		@Override
		public Fragment getItem(int i) {
			return atraccioFragment.newInstance(parcs.get(i),atraccions.get(parcs.get(i).id));
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
