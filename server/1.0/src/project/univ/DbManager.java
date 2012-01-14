package project.univ;

import java.sql.SQLException;

public class DbManager {
	private Db db;
	public DbManager() throws SQLException, ClassNotFoundException{
		db=new Db();
	}
	
	public boolean mailpass(String email, String password) {
		boolean bool;
		String trovato;
		try {
			trovato = db.mailpass(email, password);
			if(trovato!=null)
				bool=true;
			else
				bool=false;
		} catch (Exception e) {
			bool=false;
		}
		return bool;
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

	public void setLocation(String imei, String latitudine, String longitudine, double raggio) throws Exception {
		db.setLocation(imei, latitudine, longitudine, raggio);	
	}
	
	public void changePreference(String email, Notify c) throws Exception{
		db.setPreference(email, c);
	}

	public void insertCoordinate(String lat, String lng, String imei) throws Exception {
		db.setPosition(imei, lat, lng);
	}

	public Location coordinateImpostate(String imei) {
		Location c = db.getLocation(imei);
		return c;
	}

	public void setStato(String imei, String stato) throws Exception {
		db.setState(imei, stato);
	}

	public String getState(String imei) {
		String state=db.getState(imei);
		return state;
	}

	public Notify sendNotify(String imei) throws Exception{
		Notify c=db.sendNotify(imei);
		return c;
	}
	
	public Location getLocation(String imei){
		Location c=db.getLocation(imei);
		return c;
	}
	
	public Position getPostion(String imei){
		Position c=db.getPosition(imei);
		return c;
	}
}
