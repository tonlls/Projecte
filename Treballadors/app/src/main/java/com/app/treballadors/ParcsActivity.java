package com.app.treballadors;

import android.content.Intent;
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
import com.app.treballadors.model.Request;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class ParcsActivity extends AppCompatActivity implements IActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_parcs);
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

	public void setResult(Object o,Class c) {
		((ProgressBar)findViewById(R.id.progres)).setVisibility(View.INVISIBLE);
		RecyclerView.Adapter adapter=null;
		adapter = new ParcAdapter((((info_parcs_obj) o).parcs),this);
		((RecyclerView)findViewById(R.id.recicler)).setAdapter(adapter);
	}

	public void parcSelected(int id) {
		((ProgressBar)findViewById(R.id.progres)).setVisibility(View.VISIBLE);
		Intent myIntent = new Intent(this.getApplicationContext(), AtraccionsActivity.class);
		myIntent.putExtra("PARC",id);
		myIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		this.getBaseContext().startActivity(myIntent);

	}

	public void atraccioSelected(int id) {
	}
}