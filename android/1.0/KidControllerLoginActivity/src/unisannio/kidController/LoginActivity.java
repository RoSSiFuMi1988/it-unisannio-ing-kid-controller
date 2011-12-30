/**
 * 
 */
package unisannio.kidController;



import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

/**
 * @author roberto
 *
 */
public class LoginActivity extends Activity {

	private static final String LAST_INSERT = "LAST_INSERT";
	private static final String LAST_USERNAME = "LAST_USERNAME";
	private static final String LAST_PASSWORD = "LAST_PASSWORD";
	private static final String LAST_CHECK_BOX_STATUS = "LAST_CHECK_BOX_STATUS";
	private EditText username,password;
	private CheckBox rememberPassword;
	private Button login;
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_layout);
		
		username = (EditText) findViewById(R.id.usernameEditText);
		password = (EditText) findViewById(R.id.PasswordEditText);
		rememberPassword=(CheckBox) findViewById(R.id.rememberPasswordCheckBox);
		login=(Button) findViewById(R.id.loginButton);
	
		//rispristino i valori esistenti prima che l'attivita venisse killata
		this.onRestoreInstanceState(savedInstanceState);
		
		/*
		 * verifico se l'attivita è stata chiamata da un intent proveniente dal servizio LoginService
		 * e faccio stamapare a video il relativo messaggio di errore
		 */
		Intent i = this.getIntent();
		if(i.hasExtra(LoginService.LOGIN_RESPONSE)){
			boolean notLogged=i.getBooleanExtra(LoginService.LOGIN_RESPONSE, true);
			if(!notLogged)
				Toast.makeText(getApplicationContext(), "username e/o password errati", Toast.LENGTH_LONG).show();
		}
	}
	
	/*
	 * Metodo che viene richiamato dalla pressione dei tasto Login e Register cosi come è dichiarato nel file login_layout.xml
	 */
	public void callIntent(View view) throws ClassNotFoundException{
		if(view.getId()==login.getId()){//qui si dovrà fare il login verso il server
			Class<WebLoginService> loginService;
			loginService =  WebLoginService.class;
			Intent i = new Intent(LoginActivity.this, loginService);
			i.putExtra("USERNAME", username.getText().toString());
			i.putExtra("PASSWORD", password.getText().toString());
			TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
			i.putExtra("IMEI", tm.getDeviceId());
			this.startService(i);
			
			
		}
		else if(view.getId()==R.id.register){//qui si dovra connettere ad internet verso il server per la registrazione
			Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.it"));
			this.startActivity(i);
		}
	}

	public void finish(){
		this.onPause();
		super.finish();
	}
	
	/*
	 * (non-Javadoc)
	 * @see android.app.Activity#onSaveInstanceState(android.os.Bundle)
	 */
	public void onSaveInstanceState(Bundle outstate){
		outstate.putString(LAST_USERNAME, password.getText().toString());
		outstate.putString(LAST_USERNAME, username.getText().toString());
		outstate.putBoolean(LAST_CHECK_BOX_STATUS, rememberPassword.isChecked());
		
	}
	
	/*
	 * (non-Javadoc)
	 * @see android.app.Activity#onRestoreInstanceState(android.os.Bundle)
	 */
	public void onRestoreInstanceState(Bundle b){
		if(b!=null)
			if(b.containsKey(LAST_CHECK_BOX_STATUS) && b.containsKey(LAST_PASSWORD) && b.containsKey(LAST_USERNAME)){
				username.setText(b.getString(LAST_USERNAME));
				rememberPassword.setChecked(b.getBoolean(LAST_CHECK_BOX_STATUS));
				if(rememberPassword.isChecked())
					password.setText(b.getString(LAST_PASSWORD));
			}
	}
	
	/*
	 * (non-Javadoc)
	 * @see android.app.Activity#onPause()
	 */
	protected void onPause(){
		super.onPause();
		SharedPreferences sp = this.getSharedPreferences(LAST_INSERT, MODE_PRIVATE);
		Editor edit = sp.edit();
		
		
		edit.putString(LAST_USERNAME, username.getText().toString());
		edit.putBoolean(LAST_CHECK_BOX_STATUS, rememberPassword.isChecked());
		
		if(rememberPassword.isChecked())
			edit.putString(LAST_PASSWORD, password.getText().toString());
		edit.commit();
	}
	
	/*
	 * (non-Javadoc)
	 * @see android.app.Activity#onResume()
	 */
	protected void onResume(){
		super.onResume();
		this.restoreUIState();
	}
	
	/*
	 * Metodo che ripristina i valori delle variabili di instanza con quelli presenti in un'istanza di SharedPreferences privata della classe
	 */
	private void restoreUIState(){
		SharedPreferences sp = getSharedPreferences(LAST_INSERT, MODE_PRIVATE);
		String username = sp.getString(LAST_USERNAME, "");
		String password = sp.getString(LAST_PASSWORD,"");
		boolean isChecked = sp.getBoolean(LAST_CHECK_BOX_STATUS, false);
		
		if(!username.equalsIgnoreCase(""))
			this.username.setText(username);
		if(isChecked){
			this.rememberPassword.setChecked(isChecked);
			this.password.setText(password);			
		}
		else{
			this.rememberPassword.setChecked(isChecked);
			this.password.setText("Password");
		}
		
		
		
	}
}
