package com.app.treballadors;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import com.app.treballadors.input_obj.canacces_obj;
import com.app.treballadors.model.Request;
import com.google.gson.Gson;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;


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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_atraccio);
		id=getIntent().getIntExtra("ATRACCIO_ID",-1);
		nom=getIntent().getStringExtra("ATRACCIO_NOM");
		url=getIntent().getStringExtra("ATRACCIO_URL");

	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
		if(result != null) {
			if(result.getContents() == null) {
				Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
			} else {
				Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
				checkQR(result.getContents());
			}
		} else {
			super.onActivityResult(requestCode, resultCode, data);
		}
	}

	private void checkQR(String contents) {
		Object[] arr=new Object[2];
		arr[0]= Integer.parseInt(contents);
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
		Log.v("RES=",o.toString());
		if(((canacces_obj)new Gson().fromJson(o.toString(),canacces_obj.class)).can){
			o=((canacces_obj.acces_permes)new Gson().fromJson(o.toString(),canacces_obj.acces_permes.class));
		}
		else{
			o=((canacces_obj.acces_denegat)new Gson().fromJson(o.toString(),canacces_obj.acces_denegat.class));
		}
	}
}
