package unisannio.kidController.Location;

import java.util.Date;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;

public class MyLocation {
   
    private LocationManager lm;
    private LocationResult locationResult;
    private boolean gps_enabled=false;
    private boolean network_enabled=false;
    private final static int SECOND = 1000;
    private static final int TIME_FOR_DETECT= 70*SECOND; 
    public static final int DELAY_BETWEEN_DETECT = 60*SECOND*5+TIME_FOR_DETECT;
    public static final int MIN_ACCURACY = 100;
    private int time_for_detect;
    
    
    
    /*
     * Check what are the active provider
     * Link active provider with listener
     * Schedule the position detection
     * @param context the Context of Activity that call this class
     * @param result an Object of LocationResult
     * @return true if at least one listener is able otherwise false
     */
   public boolean getLocation(Context context, LocationResult result)
    {
        // use LocationResult callback class to pass location value from MyLocation to user code.
        locationResult=result;
        if(lm==null)
            lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
       
        //exceptions will be thrown if provider is not permitted.
        try{gps_enabled=lm.isProviderEnabled(LocationManager.GPS_PROVIDER);}catch(Exception ex){}
        try{network_enabled=lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);}catch(Exception ex){}

        //don't start listeners if no provider is enabled
        if(!gps_enabled && !network_enabled){
        	Toast.makeText(context,"Gps deve essere abilitato e/o si deve avere accesso alla rete cellulare," +
        			"abilitare una delle due componenti e riavviare l'applicazione" , Toast.LENGTH_LONG).show();
            return false;
        }
        else if(!gps_enabled){
        	Toast.makeText(context,"Abilitare il GPS per maggiore precisione,"+
        			"dopo l'abilitazione riavviare l'applicazione." , Toast.LENGTH_LONG).show();
        }
        else if(!network_enabled)
        	Toast.makeText(context,"La rete cellulare per il rilevamento della posizione non è attiva." +
        			"Se si desidera usarla bisognera' riavviare l'applicazione dopo la sua attivazione" , Toast.LENGTH_LONG).show();
        time_for_detect=TIME_FOR_DETECT;	
        this.findLocation();
       
        return true;
    }

    LocationListener locationListener = new LocationListener() {
        public void onLocationChanged(Location location) {
        	/*
        	 * if change location, timer2 will be cancelled and the location from gps will become actual location
        	 */
        	if(location.getAccuracy()<MIN_ACCURACY){
	            locationResult.gotLocation(location);
	            lm.removeUpdates(this);
	            if(time_for_detect!=TIME_FOR_DETECT) time_for_detect=TIME_FOR_DETECT;
        	}
        	else time_for_detect=time_for_detect*2;//in caso l'accuratezza sia bassa 
        	//alla prossima esecuzione del task findLocation si avrà più tempo a disposizione per far si che venga rilevata una posizione
        }
        public void onProviderDisabled(String provider) { }
        public void onProviderEnabled(String provider) {}
        public void onStatusChanged(String provider, int status, Bundle extras) {}
    };

    //ferma il rilevamento della posizione
    public void stopLocation(){
    	try{
    		lm.removeUpdates(locationListener);
    	} catch (Exception e){
    		//non fare nulla nel caso il listener non sia associato
    	}    	
    }
    
    /*
     * link the listener with provider
     */
    public void findLocation(){
    	
			if(gps_enabled)
				lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);				
	        if(network_enabled)
	        	lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
	        try {
				Thread.sleep(time_for_detect);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        getLastLocation();
    }
    

    /*
     * Task that return lastKnowLocation
     */    
    public void getLastLocation(){
    	Date time = new Date();//creo una nuova variabile data
    	
        lm.removeUpdates(locationListener);

        Location net_loc=null, gps_loc=null;
        if(gps_enabled)
            gps_loc=lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if(network_enabled)
            net_loc=lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

        //if there are both values use the latest one
        if(gps_loc!=null && net_loc!=null){
            if(gps_loc.getTime()>net_loc.getTime())
           	 locationResult.gotLocation(gps_loc);
            else
           	 if(net_loc.getTime()<time.getTime() )// the value of net_loc.getTime() must be less than time.getTime
           		 locationResult.gotLocation(net_loc);
            return;
        }

        if(gps_loc!=null){
            locationResult.gotLocation(gps_loc);
            return;
        }
        if(net_loc!=null){
            locationResult.gotLocation(net_loc);
            return;
        }
        locationResult.gotLocation(null);

    }

    public int getTimeForDetect(){
    	return time_for_detect;
    }
    
    /*
     *this is an abstract class to do something whit location 
     */
    public static abstract class LocationResult{
        public abstract void gotLocation(Location location);
    }
}
