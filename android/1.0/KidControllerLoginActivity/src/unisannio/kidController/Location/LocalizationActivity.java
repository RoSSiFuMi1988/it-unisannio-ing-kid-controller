package unisannio.kidController.Location;

import java.sql.Time;
import java.util.ArrayList;

import unisannio.kidController.Location.MyLocation.LocationResult;
import android.app.Activity;
import android.location.Location;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class LocalizationActivity extends Activity {

	public void onCreate(Bundle bundle){
		super.onCreate(bundle);
		setContentView(unisannio.kidController.R.layout.localization_layout);
		
		final ListView listView = (ListView) findViewById(unisannio.kidController.R.id.listViewLocalization);
			
		final ArrayList<String> array = new ArrayList<String>();
		final ArrayAdapter<String> aa = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, array);
		listView.setAdapter(aa);
		
		LocationResult result = new LocationResult() {
			
			@Override
			public void gotLocation(Location location) {
					if(location!=null){
					double lat = location.getLatitude();
					double lon = location.getLongitude();
					long time = location.getTime();
					float accuracy = location.getAccuracy();
					String provider = location.getProvider();
					
					String ret = (lat+" latitudine\n"+
										lon+" longitudine\n"+
										"accuratezza: "+accuracy+"m\n"+
										(new Time(time)).toLocaleString()+
										"\nProvider: "+provider);
					Toast.makeText(getBaseContext(), ret, Toast.LENGTH_SHORT).show();
					
					array.add(ret);
					aa.notifyDataSetChanged();
				}
				
				
			}
		};
		MyLocation myL = new MyLocation();
		myL.getLocation(this, result);
		
					
		
	}
}
