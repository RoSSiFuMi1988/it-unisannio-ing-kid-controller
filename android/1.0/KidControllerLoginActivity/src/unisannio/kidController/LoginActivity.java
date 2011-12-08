/**
 * 
 */
package unisannio.kidController;



import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

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
	
		restoreUIState();	
	}
	
	public void callIntent(View view){
		if(view.getId()==login.getId()){//qui si dovrà fare il login verso il server
			Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.it"));
			this.startActivity(i);
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
	
	public void onSaveInstanceState(Bundle outstate){
		outstate.putString(LAST_USERNAME, password.getText().toString());
		outstate.putString(LAST_USERNAME, username.getText().toString());
		outstate.putBoolean(LAST_CHECK_BOX_STATUS, rememberPassword.isChecked());
		
	}
	
	public void onRestoreInstanceState(Bundle b){
		if(b!=null)
			if(b.containsKey(LAST_CHECK_BOX_STATUS) && b.containsKey(LAST_PASSWORD) && b.containsKey(LAST_USERNAME)){
				username.setText(b.getString(LAST_USERNAME));
				rememberPassword.setChecked(b.getBoolean(LAST_CHECK_BOX_STATUS));
				if(rememberPassword.isChecked())
					password.setText(b.getString(LAST_PASSWORD));
			}
	}
	
	protected void onPause(){
		super.onPause();
		SharedPreferences sp = this.getSharedPreferences(LAST_INSERT, MODE_PRIVATE);
		sp.getString(LAST_USERNAME, username.getText().toString());
		sp.getBoolean(LAST_CHECK_BOX_STATUS, rememberPassword.isChecked());
		
		if(rememberPassword.isChecked())
			sp.getString(LAST_PASSWORD, password.getText().toString());
	}
	
	protected void onResume(){
		super.onResume();
		this.restoreUIState();
	}
	
	private void restoreUIState(){
		SharedPreferences sp = getSharedPreferences(LAST_INSERT, MODE_PRIVATE);
		String username = sp.getString(LAST_USERNAME, "coglione");
		String password = sp.getString(LAST_PASSWORD,"coglione");
		boolean isChecked = sp.getBoolean(LAST_CHECK_BOX_STATUS, false);
		
		if(!username.equalsIgnoreCase("coglione")){
			this.username.setText(username);
			this.rememberPassword.setChecked(isChecked);
			if(rememberPassword.isChecked())
				this.password.setText(password);
			
		}
		
		
	}
}
