package com.app.socis;

import android.os.AsyncTask;

import com.app.socis.input_obj.info_atraccions_obj;
import com.app.socis.model.atraccio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UpdateTask extends AsyncTask<HashMap<Integer,ArrayList<atraccio>>,List<ArrayList<atraccio>>,Void> {
	MainActivity ma;
	private boolean stop=false;
	private boolean paused=false;
	private HashMap<Integer, ArrayList<atraccio>> Mhash;

	public UpdateTask(MainActivity ma) {
		this.ma = ma;
	}

	@Override
	protected Void doInBackground(HashMap<Integer,ArrayList<atraccio>>... hash) {
		this.Mhash=hash[0];
		while(!stop){

			try {
				Thread.sleep(5*1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			execute();
		}
		return null;
	}

	private void execute() {
		for(int p: Mhash.keySet()){
			Object[] arr=new Object[1];
			arr[0]=p;
			info_atraccions_obj at = (info_atraccions_obj) Server.doRequest(new Request("getInfoAtraccions",arr), info_atraccions_obj.class,true);
			if(at.estats_atraccions==null)at.estats_atraccions=new ArrayList<atraccio>();
			if(at.estats_atraccions!=null)
				Mhash.put(p, (ArrayList<atraccio>) at.estats_atraccions);
		}
		publishProgress();
	}

	public void Pause(){paused=true;}
	public void Play(){
		paused=false;
		//execute();
	}
	public void Stop(){
		stop=true;
	}
	@Override
	protected void onProgressUpdate(List<ArrayList<atraccio>>... values) {
		super.onProgressUpdate(values);
		if (!paused)ma.updateHash();
	}
}
