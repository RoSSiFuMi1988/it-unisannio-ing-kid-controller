package project.univ;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Db {
	private Statement st;
	private String notify;
	
	public Db() throws ClassNotFoundException, SQLException{
		Class.forName("com.mysql.jdbc.Driver");
		String url = new String("jdbc:mysql://localhost:3306/kidcontrol");
		String username=new String("root");
		String password = new String("Emanuela");
		Connection conn = DriverManager.getConnection(url, username, password);
		st = conn.createStatement();	
	}
	
//	Provato - Funziona
	public Location getLocation(String imei){
		String latitudine=null, longitudine=null;
		double raggio = 0;
		try{
			String procedure = "{ call getLocation('"+imei+"') }";
			st.execute(procedure);
			ResultSet rset = st.getResultSet();
			while(rset.next()){
				latitudine = rset.getString("latitudine");
				longitudine=rset.getString("longitudine");
				raggio=rset.getDouble("raggio");
			}
		}catch( SQLException e ){System.out.println("Error");}
		return new Location(latitudine, longitudine, raggio);
	}
	
//	Provato - Funziona
	public Position getPosition(String imei){
		String latitudine=null, longitudine=null;
		java.sql.Timestamp data = null;
		try{
			String procedure = "{ call getPosition('"+imei+"') }";
			st.execute(procedure);
			ResultSet rset = st.getResultSet();
			while(rset.next()){
				latitudine = rset.getString("latitudine");
				longitudine=rset.getString("longitudine");
				String d1=(rset.getString("dataPosizione"));
				data = java.sql.Timestamp.valueOf(d1);
			}
		}catch( SQLException e ){System.out.println("Error");}
		return new Position(latitudine, longitudine, data);
	}
	
	//Funziona con le descrizioni dello stato. Modificare lo script su svn
	public String getState(String imei){
		String stato = null;
		try{
			String procedure = "{ call getState('"+imei+"') }";
			st.execute(procedure);
			ResultSet rset = st.getResultSet();
			while(rset.next()){
				stato = rset.getString("descrizione");
			}
		}catch( SQLException e ){System.out.println("Error");}
		return stato;
	}
	
//	Funziona, ma nel caso in cui si voglia registrare un utente già esistente, esso non ti avverte 
//	bensì inserisce solo il nuovo dispositivo usando il tipo di notifica registrato in precedenza
//	MODIFICATO IN 2 PROCEDURE DIVERSE
	public void insertUser(String email ,String pass ,String email_notifySms ,String imei) throws Exception{
		String procedure;
		if(email_notifySms.contains("@"))	
			procedure = "{ call insertUserEmail('"+ email+"','"+pass+"','"+email_notifySms+"','"+ imei+"') }";
		else
			procedure = "{ call insertUserSms('"+ email+"','"+pass+"','"+ email_notifySms+"', '"+ imei+"') }";
		st.executeUpdate(procedure);
	}
	
//	Funziona
	public String login(String email, String pass ,String imeid) throws Exception{
		String idUtente = null;
		String procedure = "{ call login('"+email+"','"+pass+"','"+imeid +"') }";
		st.execute(procedure);
		ResultSet rset = st.getResultSet();
		while(rset.next()){
			idUtente = rset.getString("UTENTE_idUtente");
		}
		return idUtente;
	}
	
//	Funziona
	public Notify sendNotify(String imei) throws Exception{
		String addr=null;
		String procedure = "{ call sendNotify('"+imei+"') }";
		st.execute(procedure);
		ResultSet rset = st.getResultSet();
		while(rset.next()){
			try{
				addr = rset.getString("email_notifica");
			}catch(Exception e){
				addr = rset.getString("sms");
			}
		}
		if(addr.contains("@"))
			notify="email";
		else
			notify="sms";
		return new Notify(notify, addr);
	}

//	Funziona	
	public void setLocation(String imei, String latitudine, String longitudine, double raggio) throws Exception{
		String procedure = "{ call setLocation('"+ imei+"','"+latitudine+"','"+longitudine+"','"+ raggio+"') }";
		st.executeUpdate(procedure);
	}
	
//	Funziona
	public void setPosition(String imeid , String latitudine ,String longitudine ) throws Exception{
		String procedure = "{ call setPosition('"+ imeid+"','"+latitudine+"','"+longitudine+"') }";
		st.executeUpdate(procedure);
	}
	
//	Funziona	modifcato script svn
	public void setState(String imei, String stato) throws Exception{
		String procedure = "{ call setState('"+ imei+"','"+stato+"') }";
		st.executeUpdate(procedure);
	}
	
//	Funziona
	public boolean trovaEmail(String email) throws Exception{
		String mail = null;
		boolean trov = false;
		String procedure = "{ call trovaEmail('"+email+"') }";
		st.execute(procedure);
		ResultSet rset = st.getResultSet();
		while(rset.next()){
			mail = rset.getString("email");
		}
		if(mail!=null)
			trov=true;
		return trov;
	}

	public void setPreference(String email, Notify c) throws Exception {
		String procedure = "{ call setPreference('"+email+"','"+c.getType()+"','"+c.getAddr()+"') }";
		st.executeUpdate(procedure);		
	}
	
	public Location trovaPosizione(String imei) throws Exception{
		String latitudine=null, longitudine=null;
		double raggio=0;
		String procedure = "{ call trovaPosizione('"+imei+"')}";
		st.execute(procedure);
		ResultSet rset = st.getResultSet();
		while(rset.next()){
			latitudine=rset.getString("latitudine");
			longitudine=rset.getString("longitudine");
		}
		Location l=new Location(latitudine, longitudine, raggio);
		return l;
	}
}