package project.univ;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Db {
	private Statement st;
	
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
		String latitudine=null, longitudine=null, raggio = null;
		try{
			String procedure = "{ call getLocation('"+imei+"') }";
			st.execute(procedure);
			ResultSet rset = st.getResultSet();
			while(rset.next()){
				latitudine = rset.getString("latitudine");
				longitudine=rset.getString("longitudine");
				raggio=rset.getString("raggio");
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
	
//	Non funziona -- RIvedere
	public void insertUser(String email ,String pass ,String email_notify, String sms ,String imei) throws Exception{
		String procedure;
		if(email_notify==null)	
			procedure = "{ call insertUser('"+ email+"','"+pass+"','"+null +"','"+ sms+"', '"+ imei+"') }";
		else
			procedure = "{ call insertUser('"+ email+"','"+pass+"','"+email_notify+"','"+ null+"', '"+ imei+"') }";
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
	
	public void sendNotify(String imei){
		
	}
	
	public void setLocation(String imei, String latitudine, String longitudine, int raggio){
		
	}
	
	public void setPosition(String imeid , String latitudine ,String longitudine ){
		
	}
	
	public void setState(String imei, int stato){
		
	}
	
}