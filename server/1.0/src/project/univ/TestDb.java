package project.univ;

public class TestDb {

	public static void main(String[] args) throws Exception {
		Db database=new Db();

// getLocation
		Location c=database.getLocation("bb0011");
		System.out.println("lat: "+c.getLatitudine()+" long: "+c.getLongitudine()+" raggio: "+c.getRaggio());

// getPosition
		Position c1=database.getPosition("bb0011");
		System.out.println("lat: "+c1.getLatitudine()+" long: "+c1.getLongitudine()+" data: "+c1.getdata());

// getState
		String c2=database.getState("bb0011");
		System.out.println("Stato: "+c2);			//Funziona con le descrizioni dello stato. Modificare lo script su svn
// login		
		String c3=database.login("erny@live.it", "kkk", "bbcf011");
		System.out.println("Id utente: "+c3);
		
// insertUser -Email/Sms	
		database.insertUser("mgiovy.russo@libero.it", "Ernesto","lola@lola.it", null ,"abcdh");
			
// sendNotify
		Notify n=database.sendNotify("paviliondv5");
		System.out.println(n.getType()+" "+n.getAddr());

// setLocation
		database.setLocation("paviliondv5", "20.202020", "20.202020", 20);

// setPosition
		database.setPosition("paviliondv5", "40.1234", "40.1234");
 
// setState
		String c4=database.getState("rb0011");
		System.out.println("Stato: "+c4);
		database.setState("rb0011", "entrato");
		c4=database.getState("rb0011");
		System.out.println("Stato: "+c4);
	}
}
