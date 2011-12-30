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
import javax.servlet.http.HttpSession;

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
  			System.out.println("Crea nuovo DbManager login");
  			try {
				db=new DbManager();
				scxt.setAttribute("DbManager", db);
			} catch (Exception e) {}
  		}
		PrintWriter pw=response.getWriter();
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String imei = request.getParameter("imei");
		boolean trovato=db.trova(email, password, imei);
		if (true){//{if(trovato==true){
			session=request.getSession(true);
			session.setAttribute("email", email);
			session.setAttribute("imei", imei);
			pw.println("<html><head><meta http-equiv=\"refresh\" content=\"0; url=gestore.html\"></head>");
		}
		else{
			pw.println("<html><head><meta http-equiv=\"refresh\" content=\"2; url=login.html\"></head>");
			pw.println("<body><h1>You wrong your email or password</h1></body></html>");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
