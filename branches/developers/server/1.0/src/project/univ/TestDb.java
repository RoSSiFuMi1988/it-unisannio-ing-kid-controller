package project.univ;

public class TestDb {

	public static void main(String[] args) throws Exception {
		Db database=new Db();
/*		
		Location c=database.getLocation("bb0011");
		System.out.println("lat: "+c.getLatitudine()+" long: "+c.getLongitudine()+" raggio: "+c.getRaggio());

		Position c1=database.getPosition("bb0011");
		System.out.println("lat: "+c1.getLatitudine()+" long: "+c1.getLongitudine()+" data: "+c1.getdata());

		String c2=database.getState("bb0011");
		System.out.println("Stato: "+c2);			//Funziona con le descrizioni dello stato. Modificare lo script su svn
		
		String c3=database.login("erny@live.it", "kkk", "bbcf011");
		System.out.println("Id utente: "+c3);
*/
		database.insertUser("mgiovi.russo@libero.it", "Ernesto", null, "3208716992" ,"abcde");
		
	}
}
