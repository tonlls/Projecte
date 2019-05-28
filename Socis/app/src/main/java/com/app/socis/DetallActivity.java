package com.app.socis;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.socis.model.atraccio;
import com.nostra13.universalimageloader.core.ImageLoader;

public class DetallActivity extends AppCompatActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detall);
		atraccio a= (atraccio) getIntent().getSerializableExtra("ATRACCIO");

		ImageLoader loader = ImageLoader.getInstance();

		ImageView foto=findViewById(R.id.foto);
		WebView web=findViewById(R.id.web);
		TextView capacitat=findViewById(R.id.capacitat);
		TextView alçada_min=findViewById(R.id.alçada_min);
		TextView alçada_min_acomp=findViewById(R.id.alçada_min_acomp);

		capacitat.setText(a.capacitat+"");
		alçada_min.setText(a.alçada_minima+"");
		alçada_min_acomp.setText(a.alçada_minima_acompanyant+"");
		loader.displayImage(a.url_foto,foto);
		web.loadData(a.descripcio_html, "text/html", null);
	}
}
