package com.accenture.cdi.widen.station;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.accenture.cdi.widen.station.model.User;

public class LoginActivity extends Activity {
	
	private EditText mLoginText;
	private EditText mPasswordText;
	private Button mLoginButton;
	
	private String vLoginText;
	private String vPasswordText;
	
	private User mUser;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		mLoginText = (EditText)findViewById(R.id.login_edit);
		mPasswordText = (EditText)findViewById(R.id.password_edit);
		mPasswordText.setTypeface(Typeface.DEFAULT);
		mLoginButton = (Button)findViewById(R.id.login_button);
		
		mLoginButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				vLoginText = mLoginText.getText().toString();
				vPasswordText = mPasswordText.getText().toString();
				
				if(vLoginText.equalsIgnoreCase("") || vPasswordText.equalsIgnoreCase("")){
				    Toast.makeText(LoginActivity.this, "All Fields Required.", Toast.LENGTH_SHORT).show();
				}else{
					mUser = User.get(getApplicationContext());
					mUser.setLogin(vLoginText);
					Intent i = new Intent(LoginActivity.this, MainActivity.class);
					startActivityForResult(i, 0);
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		finish();
		super.onActivityResult(requestCode, resultCode, data);
	}
}
