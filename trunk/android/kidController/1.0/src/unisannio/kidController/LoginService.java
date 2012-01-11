package unisannio.kidController;


import unisannio.kidController.Location.LocalizationActivity;
import android.app.IntentService;
import android.content.Intent;




/**
 * Classe astratta atta ad essere superclasse per i servizi che forniscono la funzionalita di
 * login. In questo modo si rende il servizio di login modulare e non dipendente dal metodo
 * che viene utilizzato per effettuare il login perche ne viene creata un'astrazione.
 * @author roberto
 *
 */
public abstract class LoginService extends IntentService{

	


	public static final int LOGIN_FAIL=0;
	public static final int LOGIN_OK=1;
	public static final String LOGIN_RESPONSE="LOGIN_RESPONSE";
	private int isLogged;

	/*
	 * Questo costruttore serve per identificare il servizio tra quelli attivi
	 */
	public LoginService(String name) {
		super(name);
	}

	//non fa altro che richiamare il metodo service operation implementato nella sotto classe
	//e far partire la schermata per la geolocalizzazione o meno
	protected void onHandleIntent(Intent intent){
		isLogged=this.serviceOperation(intent);
		if(isLogged == LOGIN_OK){
			
			//chiama l'attivita seguente a quella di login
			
			Intent i = new Intent(LoginService.this, LocalizationActivity.class);
			i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			this.startActivity(i);
		}
		else if(isLogged == LOGIN_FAIL){//ritorna all'attivita di login
			Intent i = new Intent(LoginService.this, LoginActivity.class);
			i.putExtra(LOGIN_RESPONSE, isLogged);
			i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			this.startActivity(i);
		}
		
	}

	
	/*
	 * Questo è il metodo che deve essere implementato dalle sotto classi
	 * @param intent è l'intent che viene passato al metodo onHandleIntent
	 * @ return LOGIN_OK se il login ha avuto successo, LOGIN_FAIL se il login non ha avuto successo
	 */
	abstract int serviceOperation(Intent intent);
}
