package unisannio.kidController.Register;

import unisannio.kidController.R;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.EditText;

public class RegisterActivity extends Activity {

	private static final String LAST_INSERT = "LAST_INSERT_REGISTER";
	private static final String LAST_USERNAME = "LAST_USERNAME";
	private static final String LAST_PASSWORD = "LAST_PASSWORD";
	private static final String LAST_IMEI = "LAST_IMEI";
	private static final String URI="http://erny1790.no-ip.biz/KidC/",
												ACTION="registrazione",
												EMAIL="email",
												PASS="password",
												RADIO="radio",
												CAMPO="campo",
												IMEI="imei";
	private EditText username,password,imei;
	private String simei;
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.register_layout);
		
		username = (EditText) findViewById(R.id.RegisterUsernameEditText);
		password = (EditText) findViewById(R.id.RegisterPasswordEditText);
		imei = (EditText) findViewById(R.id.RegisterIMEIEditText);
		
		TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		simei = tm.getDeviceId();
		
		imei.setText(simei);
		
		this.onRestoreInstanceState(savedInstanceState);
	}
	
	public void onSaveInstanceState(Bundle outstate){
		outstate.putString(LAST_PASSWORD, password.getText().toString());
		outstate.putString(LAST_USERNAME, username.getText().toString());
		outstate.putString(LAST_IMEI, imei.getText().toString());
		
	}
	
	public void onRestoreInstanceState(Bundle b){
		if(b!=null)
			if(b.containsKey(LAST_IMEI) && b.containsKey(LAST_PASSWORD) && b.containsKey(LAST_USERNAME)){
				username.setText(b.getString(LAST_USERNAME));
				imei.setText(b.getString(LAST_IMEI));
				password.setText(b.getString(LAST_PASSWORD));
			}
	}
	
	protected void onPause(){
		super.onPause();
		SharedPreferences sp = this.getSharedPreferences(LAST_INSERT, MODE_PRIVATE);
		Editor edit = sp.edit();
		
		
		edit.putString(LAST_USERNAME, username.getText().toString());
		edit.putString(LAST_IMEI, imei.getText().toString());
		edit.putString(LAST_PASSWORD, password.getText().toString());
		edit.commit();
	}
	
	protected void onResume(){
		super.onResume();
		this.restoreUIState();
	}
	
	private void restoreUIState(){
		SharedPreferences sp = getSharedPreferences(LAST_INSERT, MODE_PRIVATE);
		String username = sp.getString(LAST_USERNAME, "");
		String password = sp.getString(LAST_PASSWORD,"");
		String imei = sp.getString(LAST_IMEI, "");
		
		if(!username.equalsIgnoreCase(""))
			this.username.setText(username);
		if(!imei.equalsIgnoreCase(""))
			this.imei.setText(imei);			
		if(!password.equalsIgnoreCase(""))
			this.password.setText(password);
	}
	
	public void clickButton(View view){
		if(view.getId()==R.id.RegisterButton){
			String email = username.getText().toString();
			String pass = password.getText().toString();
			String imei = this.imei.getText().toString();
			Intent register = new Intent(Intent.ACTION_VIEW, Uri.parse(URI+ACTION+"?"+
					EMAIL+"="+email+"&"+
					PASS+"="+pass+"&"+
					IMEI+"="+imei+"&"+
					RADIO+"=email"+"&"+
					CAMPO+"="+email));
			this.startActivity(register);
		}
	}
	
}
