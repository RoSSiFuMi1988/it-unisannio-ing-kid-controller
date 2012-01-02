package project.univ;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Coordinate")
public class Coordinate extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServletConfig sc;
	private DbManager db;
	
	public void init(ServletConfig sc){
		this.sc=sc;
	}
	
    public Coordinate() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	ServletContext scxt = sc.getServletContext();
  		db=(DbManager) scxt.getAttribute("DbManager");
  		if(db==null){
  			try {
				db=new DbManager();
				scxt.setAttribute("DbManager", db);
			} catch (Exception e) {}
  		}
  		PrintWriter pw = response.getWriter();
    	Double lat = Double.parseDouble(request.getParameter("lat"));
    	Double lng = Double.parseDouble(request.getParameter("lng"));
    	String imei=request.getParameter("imei");
    	Location pos = db.coordinateImpostate(imei);
    	Double latitudine = Double.parseDouble(pos.getLatitudine());
    	Double longitudine = Double.parseDouble(pos.getLongitudine());
    	Double dist =  Math.acos(Math.sin(latitudine)*Math.sin(lat) + Math.cos(latitudine)*Math.cos(lat) * Math.cos(longitudine-lng));
		Double dist1 = dist*6371;
		Double distanza = Double.parseDouble(pos.getRaggio());
		String stato = db.getState(imei);		// Preleva stato
		switch (stato){
			case ("Disattivato"):
				break;
			case ("Fuori"):
				if(dist1<distanza){
					// Invia SMs entrato
					try {
						db.setStato(imei, "Entrato");
					} catch (Exception e) { e.printStackTrace(); }
				}
			break;
			case("Entrato"):
				if(distanza<dist1){
					// Invia Sms uscito
					try {
						db.setStato(imei, "Fuggitivo");
					} catch (Exception e) {	e.printStackTrace(); }
				}
			break;
			case("Fuggitivo"):
				if(dist1<distanza){
					// Invia SMs tornato
					try {
						db.setStato(imei, "Entrato");
					} catch (Exception e) { e.printStackTrace(); }
				}
		}
    	try {
			db.insertCoordinate(lat.toString(), lng.toString(), imei);
		} catch (Exception e) { e.printStackTrace(); }
    	pw.println("<HTML><HEAD><TITLE>Coordinate</TITLE></HEAD><BODY>La distanza tra i due punti è di km: "+dist1+"</BODY></HTML>");
    	
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
