package com.app.socis;

import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

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
import java.util.List;

public class MainActivity extends AppCompatActivity implements atraccioFragment.OnListFragmentInteractionListener,IActivity {
	public List<parc> parcs;
	public List<List<atraccio>> atraccions=new ArrayList<List<atraccio>>();
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
		Server at = new Server(this, parc.class);
		at.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Request("getInfoParcs",new Object[0]));
		//pgrLoading.setVisibility(View.VISIBLE);
		//((RecyclerView)findViewById(R.id.recicler)).setLayoutManager(new LinearLayoutManager(this));
		//requestParcs();

	}

	@Override
	public void setResult(Object o, Class c) {
		if(c== info_parcs_obj.class){
			parcs=((info_parcs_obj)o).parcs;
			Server at = new Server(this, info_atraccions_obj.class);
			Object[] arr=new Object[1];
			for(parc p:parcs){
				arr[0]=p.id;
				at.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Request("getInfoAtraccions",arr));
			}
		}
		else if(c==info_atraccions_obj.class){
			atraccions.add(((info_atraccions_obj)o).estats_atraccions);
			if(atraccions.size()==parcs.size()){
				((ViewPager)findViewById(R.id.pager)).setAdapter(new PageAdapter(getSupportFragmentManager()));
			}
		}
	}

	private static class PageAdapter extends FragmentPagerAdapter{
		private final List<List<atraccio>> atraccions;
		List<parc> parcs;
		public PageAdapter(FragmentManager fm,List<parc> parcs,List<List<atraccio>> atrr) {
			super(fm);
			this.parcs=parcs;
			this.atraccions=atrr;
		}

		@Override
		public Fragment getItem(int i) {
			return atraccioFragment.newInstance(parcs.get(i),atraccions.get(i));
		}

		@Override
		public int getCount() {
			return parcs.size();
		}
	}
}
