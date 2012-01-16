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
import javax.servlet.http.HttpSession;

@WebServlet("/Impostazione2")
public class Impostazione2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private int accuratezza;
	@SuppressWarnings("unused")
	private int l;
	private String lat, lon, imei;
	private HttpSession session;
	private double raggio;
	private ServletConfig sc;
	private DbManager db;
	
	public void init(ServletConfig sc){
		this.sc=sc;
	}
       
    public Impostazione2() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext scxt = sc.getServletContext();
  		db=(DbManager) scxt.getAttribute("DbManager");
  		if(db==null){
  			try {
				db=new DbManager();
			} catch (Exception e) {}
  		}
  		session=request.getSession(false);
		imei=(String) session.getAttribute("imei");
			try{
				lat=request.getParameter("lat");
				lon=request.getParameter("lon");
				raggio= Double.parseDouble(request.getParameter("raggio"));
				raggio=raggio/1000;
				try {
					db.setLocation(imei, lat, lon, raggio);
				} catch (Exception e) {	e.printStackTrace();  }
				String message="Le coordinate relative all'indirizzo inserito sono:</br>Latitudine: "+lat+"</br>Longitudine"+lon+"</br>Raggio"+raggio;
				request.setAttribute("messaggio", message);
			  	RequestDispatcher dispatcher=request.getRequestDispatcher("/error2.jsp") ;
			  	dispatcher.forward(request, response);
			}catch(Exception e){
		  			String message="I dati inseriti risultano mancanti o errati";
					request.setAttribute("messaggio", message);
			  		RequestDispatcher dispatcher=request.getRequestDispatcher("/error2.jsp") ;
			  		dispatcher.forward(request, response);
		  	}
  	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
