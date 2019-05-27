package com.app.socis;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.app.socis.model.atraccio;
import com.nostra13.universalimageloader.core.ImageLoader;

public class DetallActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		atraccio a= (atraccio) getIntent().getSerializableExtra("ATRACCIO");
		setContentView(R.layout.activity_detall);
		ImageLoader loader = ImageLoader.getInstance();
		loader.displayImage(a.url_foto, (ImageView) findViewById(R.id.foto));
	}
}
