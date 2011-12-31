package project.univ;

import java.sql.SQLException;

public class DbManager {
	private Db db;
	public DbManager() throws SQLException, ClassNotFoundException{
		db=new Db();
	}
	
	public boolean trova(String email, String password, String imei) {
		boolean bool;
		String trovato;
		try {
			trovato = db.login(email, password, imei);
			if(trovato!=null)
				bool=true;
			else
				bool=false;
		} catch (Exception e) {
			bool=false;
		}
		return bool;
	}

	public boolean trovaemail(String email) throws Exception {
		boolean trov=db.trovaEmail(email);
		return trov;
	}

	public void creauser(String email, String password, String campo, String imei) throws Exception {
		db.insertUser(email, password, campo, imei);
	}

	public void insertPreference(String imei, String latitudine, String longitudine, int raggio) throws Exception {
		db.setLocation(imei, latitudine, longitudine, raggio);	
	}
	
	public void changePreferenceSms(String email, int num){
		
	}

	public void changePreferenceEmail(String email, String sms_email) {
		
	}

	public void insertCoordinate(String lat, String lng, String imei) {
		
	}

	public Location coordinateImpostate(String imei) {
		return null;
	}

	public void setStato(String imei, String string) {
		
	}
	
	
	


}
