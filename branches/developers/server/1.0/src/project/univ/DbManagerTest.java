package project.univ;

import junit.framework.TestCase;

public class DbManagerTest extends TestCase {
private static DbManager db;
private static String email, password, imei;
	protected void setUp() throws Exception {
		db=new DbManager();
		email="pippo@pelo";
		password="pelo";
		imei="patata";
	}

	public void testMailpass() throws Exception {
		//db.creauser("pippo@pelo", "pelo", "contropelo@", "patata");
		boolean a=db.mailpass(email, password);
		assertEquals(true, a);
		password="23ag";
		a=db.mailpass(email, password);
		assertEquals(false, a);
	}

	public void testTrova() {
		boolean b=db.trova(email, password, imei);
		assertEquals(true, b);
		password="patatina";
		b=db.trova(email, password, imei);
		assertEquals(false, b);
	}

	public void testTrovaemail() throws Exception {
		boolean b=db.trovaemail(email);
		assertEquals(true, b);
		email = "giosuecarducci@hotmail.it";
		b=db.trovaemail(email);
		assertEquals(false,b);
	}

	public void testSetLocation() throws Exception {
		db.setLocation(imei, "14.01", "14.01", 1.2);
		Location c=db.getLocation(imei);
		assertEquals(c.getLatitudine(),"14.01");
		assertEquals(c.getLongitudine(), "14.01");
		assertEquals(c.getRaggio(), 1.2);
	}

	public void testChangePreference() throws Exception {
		Notify c=new Notify("email_notifica", "robb@");
		db.changePreference(email, c);
		Notify c1=db.sendNotify(imei);
		assertEquals(c1.getAddr(), c.getAddr());
		assertEquals(c1.getType(), "email");	
	}

	public void testInsertCoordinate() throws Exception {
		db.insertCoordinate("14.01", "14.01", imei);
		Position c=db.getPostion(imei);
		assertEquals("14.01", c.getLatitudine());
		assertEquals("14.01", c.getLongitudine());

	}

	public void testCoordinateImpostate() {
		Location c=db.coordinateImpostate(imei);
		 assertEquals(c.getLatitudine(), "14.01");
		 assertEquals(c.getLongitudine(), "14.01");
		 assertEquals(c.getRaggio(), 1.2);
	}

	public void testSetStato() throws Exception {
		db.setStato(imei, "entrato");
		String s = db.getState(imei);
		assertEquals(s, "entrato");
	}

	public void testGetState() {
		String s = db.getState(imei);
		assertEquals(s, "entrato");
	}

	public void testSendNotify() throws Exception {
		Notify c1=db.sendNotify(imei);
		//System.out.println(c1.getAddr()+" "+c1.getType());
		assertEquals(c1.getAddr(), "robb@");
		assertEquals(c1.getType(), "email");	
	}

}
