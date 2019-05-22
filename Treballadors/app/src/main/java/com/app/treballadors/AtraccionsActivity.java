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
import com.app.treballadors.model.Request;

public class AtraccionsActivity extends AppCompatActivity implements IActivity {
	int parc;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		parc=getIntent().getIntExtra("PARC",-1);
		setContentView(R.layout.activity_atraccions);
		requestAtraccions(parc);
		((RecyclerView)findViewById(R.id.recicler)).setLayoutManager(new LinearLayoutManager(this));
		((SwipeRefreshLayout)findViewById(R.id.refresh)).setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
			@Override
			public void onRefresh() {
				//((SwipeRefreshLayout)findViewById(R.id.refresh)).setRefreshing(false);
				//((SwipeRefreshLayout)findViewById(R.id.refresh)).setVisibility(View.VISIBLE);
				requestAtraccions(parc);
			}
		});
	}
	protected void requestAtraccions(final int i){
		Server at = new Server(this, info_atraccions_obj.class);
		Object[] arr=new Object[1];
		arr[0]=i;
		at.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Request("getInfoAtraccions",arr));
	}


	@Override
	public void setResult(Object o, Class c) {
		((ProgressBar)findViewById(R.id.progres)).setVisibility(View.INVISIBLE);
		RecyclerView.Adapter adapter=null;
		adapter = new AtraccioAdapter((((info_atraccions_obj) o).estats_atraccions),this);
		((RecyclerView)findViewById(R.id.recicler)).setAdapter(adapter);
	}
}
