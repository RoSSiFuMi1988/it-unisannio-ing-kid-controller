package unisannio.kidController;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.content.Intent;

public class WebLoginService extends LoginService {



	private static String  URI;//l'indirizzo dove è hostato il server
	private static final String EMAIL="email",
												PASSWORD="password",
												IMEI="imei";
	private final static String ACTION="login";//la servlet tramite la quale viene effettuato il login
	
	public WebLoginService() {
		super("WebLoginService");
		
	}
	
	
	
	//controlla se il login è andato a buon fine
	public int serviceOperation(Intent intent) {
	
		String completeUri = this.completeURI(intent);
		
		StatusLine statusline = this.sendRequest(completeUri);
		
		if(statusline.getStatusCode()== HttpStatus.SC_OK) return LOGIN_OK;
		else return LOGIN_FAIL;
	}

	//invio la richiesta al server e ne catturo il messaggio di risposta
	private StatusLine sendRequest(String completeUri){

		HttpClient httpclient = new DefaultHttpClient();
		HttpResponse response;
		try {
			response = httpclient.execute(new HttpGet(completeUri));
			return response.getStatusLine();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	//recupero dei dati dall'intent e costruzione dell'indirizzo per la richiesta	
	private String completeURI(Intent intent){

	
		URI=this.getText(R.string.connection_uri).toString();

		
		URI=this.getText(R.string.connection_uri).toString();

		String username=intent.getStringExtra("USERNAME");
		String password=intent.getStringExtra("PASSWORD");
		String imei = intent.getStringExtra("IMEI");
		return URI+"/"+ACTION+"?"+
											EMAIL+"="+username+"&"+
											PASSWORD+"="+password+"&"+
											IMEI+"="+imei;
	}
}
