package com.app.socis;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.socis.input_obj.getpass_obj;
import com.app.socis.input_obj.info_atraccions_obj;
import com.app.socis.input_obj.login_obj;
import com.app.socis.model.atraccio;
import com.app.socis.model.passi_expres;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.util.List;

public class PassisActivity extends AppCompatActivity implements IActivity {
	private static final String PREFS_NAME = "LOGIN_CONFIG";
	private int session;
	passi_expres actual_pas;

	private void Login(){
		boolean logged=true;
		Credentials cred=null;
		SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
		String usr = settings.getString("user","");
		String pas = settings.getString("password", "");
		if(usr==""||pas=="") logged=false;
		else cred=new Credentials(usr,pas);
		if(!logged){
			getLogin();
		}
		else
			doLogin(cred);
	}
	private void doLogin(Credentials cred){
		SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
		SharedPreferences.Editor editor = settings.edit();
		editor.putString("user",cred.user);
		editor.putString("password", cred.password);
		editor.commit();
		Server at = new Server(this, login_obj.class);
		Object[] arr=new Object[2];
		arr[0]=cred.user;
		arr[1]=cred.password;
		at.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Request("login",arr));
	}
	private void getLogin() {
		Intent intent = new Intent(this, LoginActivity.class);
		startActivityForResult(intent,1);
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		String user= data.getStringExtra("USER");
		String pass= data.getStringExtra("PASS");
		doLogin(new Credentials(user,pass));
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_passis);
		Login();
		((RecyclerView)findViewById(R.id.pasList)).setLayoutManager(new LinearLayoutManager(this,0,false));
		((RecyclerView)findViewById(R.id.atraccions)).setLayoutManager(new LinearLayoutManager(this));
	}

	@Override
	public void setResult(Object o, Class c, int extra) {
		if(c==login_obj.class) {
			this.session = ((login_obj) o).code;
			Server at = new Server(this, getpass_obj.class);
			Object[] arr=new Object[1];
			arr[0]=this.session;
			at.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Request("getPassis",arr));
		}
		else if(c== getpass_obj.class){
			((RecyclerView)findViewById(R.id.pasList)).setAdapter(new PasAdapter(this,((getpass_obj)o).passis));
		}
	}
	private void changePassi(passi_expres pe) {
		actual_pas=pe;
		RecyclerView rv=findViewById(R.id.atraccions);
		Button bt=findViewById(R.id.show_qr);
		bt.setVisibility(View.VISIBLE);
		rv.setAdapter(new AtraccioAdapter(pe));
	}

	public void showQR(View view) {
		BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
		Bitmap bitmap = null;
		try {
			bitmap = barcodeEncoder.encodeBitmap(actual_pas.id+"", BarcodeFormat.QR_CODE, 800, 800);
		} catch (WriterException e) {
			e.printStackTrace();
		}
		ImageView imageViewQrCode = (ImageView) findViewById(R.id.qr);
		imageViewQrCode.setVisibility(View.VISIBLE);
		Button bt=findViewById(R.id.show_qr);
		bt.setVisibility(View.INVISIBLE);
		imageViewQrCode.setImageBitmap(bitmap);
	}

	public class PasAdapter extends RecyclerView.Adapter {
		private final PassisActivity activity;
		List<passi_expres> passis;
		passi_expres pas;
		public PasAdapter(PassisActivity act,List<passi_expres> passis) {
			this.activity=act;
			this.passis=passis;
		}

		@NonNull
		@Override
		public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

			View fila = LayoutInflater.from(viewGroup.getContext()).inflate(
					R.layout.passi_row, viewGroup, false);
			return new PasAdapter.ViewHolder(fila);
		}

		@Override
		public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
			PasAdapter.ViewHolder v= (ViewHolder) viewHolder;
			v.nom.setText(passis.get(i).nom_tipus);
		}

		@Override
		public int getItemCount() {
			return passis.size();
		}

		public class ViewHolder extends RecyclerView.ViewHolder {
			ConstraintLayout row;
			TextView nom;

			public ViewHolder(View fila) {
				super(fila);
				row=fila.findViewById(R.id.row);
				nom=fila.findViewById(R.id.nom);
				row.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v
					) {
						int pos = getAdapterPosition();
						passi_expres pe = PasAdapter.this.passis.get(pos);
						PassisActivity.this.changePassi(pe);
					}
				});
			}
		}
	}


	private class AtraccioAdapter extends RecyclerView.Adapter {
		passi_expres pas;

		public AtraccioAdapter(passi_expres pe) {
			pas=pe;
		}

		@NonNull
		@Override
		public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
			View fila = LayoutInflater.from(viewGroup.getContext()).inflate(
					R.layout.atraccio_row, viewGroup, false);
			return new AtraccioAdapter.ViewHolder(fila);
		}

		@Override
		public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
			AtraccioAdapter.ViewHolder v= (ViewHolder) viewHolder;
			v.nom.setText(pas.atraccions.get(i).nom_atraccio);
		}

		@Override
		public int getItemCount() {
			return pas.atraccions.size();
		}

		public class ViewHolder extends RecyclerView.ViewHolder {
			TextView nom;
			public ViewHolder(View fila) {
				super(fila);
				nom=fila. findViewById(R.id.nom);
			}
		}
	}
	public class Credentials{
		public String user;
		public String password;

		public Credentials(String user, String password) {
			this.user = user;
			this.password = password;
		}
	}
}
