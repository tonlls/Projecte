package com.app.treballadors;

import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.app.treballadors.input_obj.info_atraccions_obj;
import com.app.treballadors.input_obj.info_parcs_obj;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this).build();
		ImageLoader.getInstance().init(config);
//		pgrLoading.setVisibility(View.VISIBLE);
		((RecyclerView)findViewById(R.id.recicler)).setLayoutManager(new LinearLayoutManager(this));
		requestParcs();
		SwipeRefreshLayout r=((SwipeRefreshLayout)findViewById(R.id.refresh));
		r.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
			@Override
			public void onRefresh() {
				requestParcs();
				((SwipeRefreshLayout)findViewById(R.id.refresh)).setRefreshing(false);
			}
		});
	}
	protected void requestParcs(){
		Server at = new Server(this,info_parcs_obj.class);
		at.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Request("getInfoParcs",new Object[0]));
	}
	public void setResult(Object p,Class c) {
		//pgrLoading.setVisibility(View.INVISIBLE);
		((ProgressBar)findViewById(R.id.progres)).setVisibility(View.INVISIBLE);
		RecyclerView.Adapter adapter=null;
		if(c==info_parcs_obj.class) {
			adapter = new ParcAdapter((((info_parcs_obj) p).parcs),this);
		}else if(c == info_atraccions_obj.class){
			adapter = new AtraccioAdapter((((info_parcs_obj) p).parcs),this);
		}
		((RecyclerView)findViewById(R.id.recicler)).setAdapter(adapter);
	}

	public void parcSelected(int id) {
		((SwipeRefreshLayout)findViewById(R.id.refresh)).setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
			@Override
			public void onRefresh() {
				requestParcs();
				((SwipeRefreshLayout)findViewById(R.id.refresh)).setRefreshing(false);
			}
		});
	}
}
