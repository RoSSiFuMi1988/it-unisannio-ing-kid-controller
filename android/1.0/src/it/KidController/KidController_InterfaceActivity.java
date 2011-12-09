package it.KidController;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class KidController_InterfaceActivity extends Activity {
	private EditText userName;
	private EditText password;


	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		userName = (EditText) findViewById(R.id.user_name);
		password = (EditText) findViewById(R.id.password);
	}

	public void invia(View v) {//metodo di invio delle credenziali al server(non sviluppato)
		if (isLoginCorretto(userName.getText(), password.getText()))
		{
			Toast.makeText(this, "Login effettuato", Toast.LENGTH_LONG).show();
		}
		else
		{
			Toast.makeText(this, "Login errato", Toast.LENGTH_LONG).show();
		}
	}

	public void reset(View v)//metodo di reset dei campi
	{
		userName.setText("");
		password.setText("");
		Toast.makeText(this,"Pulizia Credenziali!",Toast.LENGTH_LONG).show();
	}
//metodo di controllo dei parametri di login inseriti in caso di parametri uguali restituisce true altrimenti false
	private boolean isLoginCorretto(CharSequence nome, CharSequence password)	{
		if(nome.toString().equalsIgnoreCase(password.toString())) return false;
		else return true;		
	}
}