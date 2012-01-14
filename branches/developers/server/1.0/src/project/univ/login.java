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

import org.apache.http.HttpStatus;

@WebServlet("/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HttpSession session;
	private ServletConfig sc;
	private DbManager db;
	
	public void init(ServletConfig sc){
		this.sc=sc;
	}
	
    public login() {
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
  		String email=null, password=null, imei=null;
		email = request.getParameter("email");
		password = request.getParameter("password");
		imei = request.getParameter("imei");
		boolean trovato=db.trova(email, password, imei);
		if(trovato==true){
			session=request.getSession(true);
			session.setAttribute("email", email);
			session.setAttribute("imei", imei);
			response.setStatus(HttpStatus.SC_OK);
			RequestDispatcher dispatcher=request.getRequestDispatcher("/gestore.html") ;
	  		dispatcher.forward(request, response);
		}
		else{
			response.setStatus(HttpStatus.SC_ACCEPTED);
			String message="You wrong your email, password or imei";
			request.setAttribute("messaggio", message);
	  		RequestDispatcher dispatcher=request.getRequestDispatcher("/error.jsp") ;
	  		dispatcher.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
