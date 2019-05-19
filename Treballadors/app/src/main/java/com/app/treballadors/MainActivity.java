package com.app.treballadors;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.app.treballadors.input_obj.info_parcs_obj;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Server at = new Server(this);
//		pgrLoading.setVisibility(View.VISIBLE);
		at.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Request("getInfoParcs",new Object[0]));
	}

	public void setResult(info_parcs_obj p) {
		//pgrLoading.setVisibility(View.INVISIBLE);
		ParcAdapter adapter = new ParcAdapter(p.parcs);
		((RecyclerView)findViewById(R.id.recicler)).setAdapter(adapter);
	}
}
