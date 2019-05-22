package com.app.treballadors;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.app.treballadors.input_obj.canacces_obj;
import com.app.treballadors.input_obj.confirm_obj;
import com.app.treballadors.model.Request;
import com.google.gson.Gson;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.nostra13.universalimageloader.core.ImageLoader;


//GENERATE QR
/*try {
			BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
			Bitmap bitmap = barcodeEncoder.encodeBitmap("1", BarcodeFormat.QR_CODE, 400, 400);
			ImageView imageViewQrCode = (ImageView) findViewById(R.id.foto);
			imageViewQrCode.setImageBitmap(bitmap);
		} catch(Exception e) {

		}*/

public class ActivityAtraccio extends AppCompatActivity implements IActivity {
	int id;
	String nom;
	String url;
	private int passi;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_atraccio);
		id=getIntent().getIntExtra("ATRACCIO_ID",-1);
		nom=getIntent().getStringExtra("ATRACCIO_NOM");
		url=getIntent().getStringExtra("ATRACCIO_URL");
		ImageLoader loader = ImageLoader.getInstance();
		loader.displayImage(url, (ImageView) findViewById(R.id.foto));
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
		if(result != null) {
			if(result.getContents() == null) {
				Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
			} else {
				//Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
				checkQR(result.getContents());
			}
		} else {
			super.onActivityResult(requestCode, resultCode, data);
		}
	}

	private void checkQR(String contents) {
		Object[] arr=new Object[2];
		passi=Integer.parseInt(contents);
		arr[0]=passi;
		arr[1]= id;
		Server at = new Server(this, canacces_obj.class,false);
		at.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Request("canAcces",arr));
	}

	public void scan(View view) {
		IntentIntegrator ii=new IntentIntegrator(this);
		ii.setPrompt("Scan The Client QR Code");
		ii.setOrientationLocked(false);
		ii.initiateScan();
	}

	@Override
	public void setResult(Object o, Class c) {
		if(c!=confirm_obj.class) {
			TextView t_acces = findViewById(R.id.t_acces);
			TextView acces = findViewById(R.id.acces);
			Button confirm = findViewById(R.id.confirm);
			canacces_obj obj = (canacces_obj) new Gson().fromJson(o.toString(), canacces_obj.class);
			if (obj.can) {
				canacces_obj.acces_permes objP = (new Gson().fromJson(o.toString(), canacces_obj.acces_permes.class));
				acces.setText("Acces Permes");
				t_acces.setText("Acces " + (objP.es_ilimitat ? "Ilimitat" : "") + (objP.primera ? "En Primera Fila" : "") + (!objP.es_ilimitat && !objP.primera ? "Un Sol Us" : ""));
				confirm.setVisibility(View.VISIBLE);
			} else {
				canacces_obj.acces_denegat objD = (new Gson().fromJson(o.toString(), canacces_obj.acces_denegat.class));
				acces.setText("Acces Denegat");
				t_acces.setText(objD.motiu);
				confirm.setVisibility(View.INVISIBLE);
				//Toast.makeText(this, "Acces Denegat: " + objD.motiu, Toast.LENGTH_LONG).show();
			}
		}
		else{

			Toast.makeText(this.getApplicationContext(),((confirm_obj)o).codi+"",Toast.LENGTH_LONG);
		}
	}

	public void confirm(View view) {
		Object[] arr=new Object[2];
		arr[0]=passi;
		arr[1]=id;
		Server at = new Server(this, canacces_obj.class,false);
		at.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Request("confirmAcces",arr));
	}
}
