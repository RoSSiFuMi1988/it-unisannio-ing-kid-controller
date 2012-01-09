package unisannio.kidController.Location;

import java.util.ArrayList;

import unisannio.kidController.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class LocalizationActivity extends Activity {

	private ArrayList<String> array;
	private ArrayAdapter<String> aa;
	
	
	public void onCreate(Bundle bundle){
		super.onCreate(bundle);
		setContentView(unisannio.kidController.R.layout.localization_layout);
		
		final ListView listView = (ListView) findViewById(unisannio.kidController.R.id.listViewLocalization);
			
		array = new ArrayList<String>();
		aa = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, array);
		listView.setAdapter(aa);
					
		
	}
	
	public void onClickButton(View view){
		Intent localizationService = new Intent(this, LocalizationService.class);
		
		switch (view.getId()) {
		case R.id.buttonStart:{
			
			Button stop = (Button) findViewById(R.id.buttonStop);
			stop.setEnabled(true);
			Button start = (Button) findViewById(R.id.buttonStart);
			start.setEnabled(false);
			localizationService.putExtra("startstop", LocalizationService.START);
			this.startService(localizationService);
		}break;
		case R.id.buttonStop:{
			Button stop = (Button) findViewById(R.id.buttonStop);
			stop.setEnabled(false);
			Button start = (Button) findViewById(R.id.buttonStart);
			start.setEnabled(true);
			localizationService.putExtra("startstop", LocalizationService.STOP);
			stopService(localizationService);			
		}break;
		}
	}
}
