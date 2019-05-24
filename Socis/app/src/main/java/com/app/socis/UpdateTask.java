package com.app.socis;

import android.app.Activity;
import android.os.AsyncTask;
import android.support.v4.view.ViewPager;

import com.app.socis.input_obj.info_atraccions_obj;
import com.app.socis.model.atraccio;
import com.app.socis.model.parc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UpdateTask extends AsyncTask<HashMap<Integer,ArrayList<atraccio>>,List<ArrayList<atraccio>>,Object> {
	MainActivity ma;

	public UpdateTask(MainActivity ma) {
		this.ma = ma;
	}

	@Override
	protected Object doInBackground(HashMap<Integer,ArrayList<atraccio>>... hash) {
		while(true){

			try {
				Thread.sleep(5*1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			for(int p:hash[0].keySet()){
				Object[] arr=new Object[1];
				arr[0]=p;
				info_atraccions_obj at = (info_atraccions_obj) Server.doRequest(new Request("getInfoAtraccions",arr), info_atraccions_obj.class,true);
				hash[0].put(p, (ArrayList<atraccio>) at.estats_atraccions);
			}
		}
		//return null;
	}

	@Override
	protected void onPostExecute(Object aVoid) {
		super.onPostExecute(aVoid);
	}

	@Override
	protected void onProgressUpdate(List<ArrayList<atraccio>>... values) {
		super.onProgressUpdate(values);
		ma.updateHash();

	}
}
