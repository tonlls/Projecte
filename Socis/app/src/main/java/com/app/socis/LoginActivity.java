package com.app.socis;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
	}

	public void loginClick(View view) {
		Intent resultIntent = new Intent();
		resultIntent.putExtra("USER",((TextView)findViewById(R.id.usr)).getText().toString());
		resultIntent.putExtra("PASS",((TextView)findViewById(R.id.pass)).getText().toString());
		setResult(Activity.RESULT_OK, resultIntent);
		finish();
	}
}
