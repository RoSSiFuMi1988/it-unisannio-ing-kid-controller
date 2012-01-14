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

@WebServlet("/AttNotifiche")
public class AttNotifiche extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HttpSession session;
	private ServletConfig sc;
	private DbManager db;
	
	public void init(ServletConfig sc){
		this.sc=sc;
	}
	
    public AttNotifiche() {
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
  		session = request.getSession();
  		String imei = (String) session.getAttribute("imei");
  		try {
			db.setStato(imei, "disattivato");
		} catch (Exception e) {	e.printStackTrace(); }
  		String message="Le notifiche legate al dispositovo, sono state disattivate";
  		request.setAttribute("messaggio", message);
  		RequestDispatcher dispatcher=request.getRequestDispatcher("/error2.jsp") ;
  		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
