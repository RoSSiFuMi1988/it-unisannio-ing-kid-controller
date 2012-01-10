package unisannio.kidController.Location;

import unisannio.kidController.LoginActivity;
import unisannio.kidController.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LocalizationActivity extends Activity {

	private static boolean service_started = false;
	
	public void onCreate(Bundle bundle){
		super.onCreate(bundle);
		setContentView(unisannio.kidController.R.layout.localization_layout);
		
		Button stop = (Button) findViewById(R.id.buttonStop);
		Button start = (Button) findViewById(R.id.buttonStart);
		if(service_started){
			stop.setEnabled(true);
			start.setEnabled(false);
		}
		else{
			stop.setEnabled(false);
			start.setEnabled(true);
		}
					
		
	}
	
	public void onClickButton(View view){
		Intent localizationService = new Intent(this, LocalizationService.class);
		
		switch (view.getId()) {
		case R.id.buttonStart:{
			
			Button stop = (Button) findViewById(R.id.buttonStop);
			stop.setEnabled(true);
			Button start = (Button) findViewById(R.id.buttonStart);
			start.setEnabled(false);
			this.startService(localizationService);
			service_started = true;
			
			/*
			 * qui si ritorna all'attivita di login in modo tale 
			 * che se si volesse bloccare  la geolocalizzazione bisognerebbe
			 * rifare il login
			 */
			Intent returnBack = new Intent(this, LoginActivity.class);
			this.startActivity(returnBack);
		}break;
		case R.id.buttonStop:{
			Button stop = (Button) findViewById(R.id.buttonStop);
			stop.setEnabled(false);
			Button start = (Button) findViewById(R.id.buttonStart);
			start.setEnabled(true);
			localizationService.putExtra("startstop", LocalizationService.STOP);
			stopService(localizationService);
			service_started = true;
		}break;
		}
	}
}
