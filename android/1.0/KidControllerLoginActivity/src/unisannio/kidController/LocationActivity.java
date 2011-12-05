package unisannio.kidController;

import java.sql.Time;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class LocationActivity extends Activity {

	
	public void onCreate(Bundle bundle){
		super.onCreate(bundle);
		setContentView(R.layout.location_activity_layout);
		
		final TextView textView = (TextView) findViewById(R.id.textView1);
		
		//acquisizione del riferimento per il location manager
		LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
		
		//creazione di un listener che risponde ai cambiamenti di posizione
		LocationListener locationListener = new LocationListener() {
			
			@Override
			public void onStatusChanged(String provider, int status, Bundle extras) {
				
			}
			
			@Override
			public void onProviderEnabled(String provider) {
				
			}
			
			@Override
			public void onProviderDisabled(String provider) {
				
			}
			
			@Override
			public void onLocationChanged(Location location) {
				double lat = location.getLatitude();
				double lon = location.getLongitude();
				long time = location.getTime();
				float accuracy = location.getAccuracy();
				
				String ret = (lat+" latitudine\n"+
									lon+" longitudine\n"+
									accuracy+"accuratezza\n"+
									(new Time(time)).toGMTString());
				Toast.makeText(getBaseContext(), ret, Toast.LENGTH_SHORT).show();
				textView.setText(ret);
				
				
			}
		};
	
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 60000, 0, locationListener);
		locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
	}
	

}
