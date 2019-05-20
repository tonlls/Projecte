package com.app.treballadors;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class AtraccionsActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_atraccions);
		int parc_id=getIntent().getIntExtra("PARC_ID",-1);
		if(parc_id==-1){
			//ERROR
		}
		//Server
	}
}
