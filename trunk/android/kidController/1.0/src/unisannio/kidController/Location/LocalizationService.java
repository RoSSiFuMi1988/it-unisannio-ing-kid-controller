package unisannio.kidController.Location;

import java.io.IOException;
import java.sql.Time;

import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import unisannio.kidController.Location.MyLocation.LocationResult;

import android.R;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.os.IBinder;
import android.os.PowerManager;
import android.telephony.TelephonyManager;

public class LocalizationService extends Service {

	
	private static final String  URI ="http://erny1790.no-ip.biz/KidC",//l'indirizzo dove è hostato il server
			ACTION="Coordinate",
			LAT="lat",
			LON="lon",
			IMEI="imei";

	public final static int LOCATION_ID=12345;
	public final static int START=0,
		STOP=1;
	private MyLocation myL;
	NotificationManager nm;
	private int icon;
	private CharSequence tickerText;
	private Context context;
	private CharSequence contentTitle;
	private PowerManager.WakeLock wl;

	public int onStartCommand(Intent intent, int flags, int startId){
		myL = new MyLocation();
		PowerManager pm = (PowerManager) getSystemService(POWER_SERVICE);
		wl = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "Location service lock");
		wl.acquire();
		
		nm = (NotificationManager) this.getSystemService(NOTIFICATION_SERVICE);
		
		icon =R.drawable.ic_menu_info_details;
		tickerText = "Localization";

				
		context = getApplicationContext();
		contentTitle = "Location notify";
	
		myL.getLocation(this, result);
		
		
		return Service.START_STICKY;
	}
	@Override
	public IBinder onBind(Intent arg0) {
		
		return null;
	}

	
	public void onDestroy(){
		wl.release();
		myL.stopLocation();
		super.onDestroy();
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
		private String completeURI(double lat, double lon){

			TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
			String imei=tm.getDeviceId();
			return URI+"/"+ACTION+"?"+
												LAT+"="+lat+"&"+
												LON+"="+lon+"&"+
												IMEI+"="+imei;
		}

		LocationResult result = new LocationResult() {
			
			@Override
			public void gotLocation(Location location) {
					if(location!=null){
					double lat = location.getLatitude();
					double lon = location.getLongitude();
					long time = location.getTime();
					float accuracy = location.getAccuracy();
					String provider = location.getProvider();
					
					sendRequest(completeURI(lat,lon)); //invio posizione al server
					
					String ret = (lat+" latitudine\n"+
										lon+" longitudine\n"+
										"accuratezza: "+accuracy+"m\n"+
										(new Time(time)).toLocaleString()+
										"\nProvider: "+provider);
					//Toast.makeText(getBaseContext(), ret, Toast.LENGTH_SHORT).show();
					
					
					CharSequence contentText = ret;
					Intent notificationIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("www.google.it"));
					PendingIntent contentIntent = PendingIntent.getActivity(getBaseContext(), 0, notificationIntent, 0);
					
					Notification notification =  new Notification(icon, tickerText, System.currentTimeMillis());
					notification.setLatestEventInfo(context, contentTitle, contentText, contentIntent);
					
					startForeground(LOCATION_ID, notification);
				}
				
				
			}						
		};
}
