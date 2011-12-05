package project.univ;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/registrazione")
public class registrazione extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServletConfig sc;
	private DbManager db;
	
	public void init(ServletConfig sc){
		this.sc=sc;
	}
	
    public registrazione() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext scxt = sc.getServletContext();
  		db=(DbManager) scxt.getAttribute("DbManager");
  		if(db==null){
  			System.out.println("Crea nuovo DbManager registrazione");
  			try {
				db=new DbManager();
				scxt.setAttribute("DbManager", db);
			} catch (SQLException e) {}
  		}
		PrintWriter pw = response.getWriter();
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		String imei=request.getParameter("imei");
		String preferenze=request.getParameter("preferenze");
		String campo=request.getParameter("campo");
		boolean trovato=db.trovaemail(email);
		if(trovato==true)
			pw.println("Email già esistente");
		else{
			db.creauser(email, password, imei, preferenze, campo);
			pw.println("<html><head><meta http-equiv=\"refresh\" content=\"2; url=login.html\"></head>");
			pw.println("<body>Registrazione Effettuata con successo, attendere il reindirizzamento</body></html>");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
