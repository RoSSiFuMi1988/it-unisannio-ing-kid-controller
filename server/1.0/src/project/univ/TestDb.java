package project.univ;

public class TestDb {

	public static void main(String[] args) throws Exception {
		Db database=new Db();
/*
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
//		database.insertUser("mgiovk.russo@libero.it", "Ernesto","3208716992" ,"abcdl");
			
// sendNotify
		Notify n=database.sendNotify("paviliondv5");
		System.out.println(n.getType()+" "+n.getAddr());
		
// setLocation
		DbManager c=new DbManager();
		c.setLocation("ernestorusso@libero.it", "41.0222190", "14.6168570", 2);


//setPosition
//		database.setPosition("356652040376772", "45.468799", "9.186516");

// setState
		String c4=database.getState("rb0011");
		System.out.println("Stato: "+c4);
		database.setState("rb0011", "entrato");
		c4=database.getState("rb0011");
		System.out.println("Stato: "+c4);

// trovaEmail		
		boolean trov=database.trovaEmail("maur@live.it");
		System.out.println("Trovato: "+trov);


//SetPreference Sms-Email_notifica
		Notify c=new Notify("email_notifica", "ernestorusso@libero.it");
		database.setPreference("ernestorusso@libero.it", c);	
	
		Location l = database.trovaPosizione("paviliondv5");
		System.out.println("Latitudine: "+l.getLatitudine()+" Longitudine: "+l.getLongitudine());

*/
		String id=database.mailpass("maur@live.it", "23agf");
		System.out.println("Id utente: "+id);
		
	}
	
}
