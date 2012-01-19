package project.univ;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpStatus;

@WebServlet("/Coordinate")
public class Coordinate extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServletConfig sc;
	private DbManager db;
	private Notify notifica;
	
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
  		response.setStatus(HttpStatus.SC_OK);
    	Double lat = Double.parseDouble(request.getParameter("lat"));
    	Double lng = Double.parseDouble(request.getParameter("lon"));
    	String imei=request.getParameter("imei");
    	Location pos = db.coordinateImpostate(imei);
    	Double latitudine = Double.parseDouble(pos.getLatitudine());
    	Double longitudine = Double.parseDouble(pos.getLongitudine());
    	Double pi=180/Math.PI;
    	Double dist =  Math.acos(Math.sin(latitudine/pi)*Math.sin(lat/pi) + Math.cos(latitudine/pi)*Math.cos(lat/pi) * Math.cos((longitudine/pi)-(lng/pi)));
		Double dist1 = dist*6371;				//distanza calcolata tra punto impostato e posizione attuale
		Double distanza = pos.getRaggio();		//distanza max impostata
		String stato = db.getState(imei);
		try {
			notifica = db.sendNotify(imei);
		} catch (Exception e1) {	e1.printStackTrace();		}

		//System.out.println(notifica.getType()+" "+notifica.getAddr()+" "+stato);
		
		switch (stato){
			case ("disattivato"):
				break;
			case ("fuori"):
				if(dist1<distanza){
					if(notifica.getType().equalsIgnoreCase("email")){	// EMAIL
						request.setAttribute("to", notifica.getAddr());
						request.setAttribute("state", "Alert");
						request.setAttribute("text", "Il dispositovo è entrato nell'area impostata.");
						String address="SendMail";
						RequestDispatcher dispatcher = request.getRequestDispatcher(address);
						dispatcher.forward(request, response);
						//System.out.println("DISPATCHER "+notifica.getAddr()+" "+stato);
					}					
					try {
						db.setStato(imei, "Entrato");
						//System.out.println("stato attuale: "+db.getState(imei));
					} catch (Exception e) { e.printStackTrace(); }
				}
			break;
			case("entrato"):
				if(distanza<dist1){
					if(notifica.getType().equalsIgnoreCase("email")){	// EMAIL
						request.setAttribute("to", notifica.getAddr());
						request.setAttribute("state", "Alert");
						request.setAttribute("text", "Il dispositovo è uscito dall'area impostata.");
						String address="SendMail";
						RequestDispatcher dispatcher = request.getRequestDispatcher(address);
						dispatcher.forward(request, response);
						//System.out.println("DISPATCHER "+notifica.getAddr()+" "+stato);
					}
					try {
						db.setStato(imei, "Fuggitivo");
						//System.out.println("stato attuale: "+db.getState(imei));
					} catch (Exception e) {	e.printStackTrace(); }
				}
			break;
			case("fuggitivo"):
				if(dist1<distanza){
					if(notifica.getType().equalsIgnoreCase("email")){	// EMAIL
						request.setAttribute("to", notifica.getAddr());
						request.setAttribute("state", "Alert");
						request.setAttribute("text", "Il dispositivo è rientrato nell'area impostata.");
						String address="SendMail";
						RequestDispatcher dispatcher = request.getRequestDispatcher(address);
						dispatcher.forward(request, response);
						//System.out.println("DISPATCHER "+notifica.getAddr()+" "+stato);
					}
					try {
						db.setStato(imei, "Entrato");
						//System.out.println("stato attuale: "+db.getState(imei));
					} catch (Exception e) { e.printStackTrace(); }
				}
		}
    	try {
			db.insertCoordinate(lat.toString(), lng.toString(), imei);
		} catch (Exception e) { e.printStackTrace(); }
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
