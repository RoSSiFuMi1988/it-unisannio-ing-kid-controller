package project.univ;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Preferenze
 */
@WebServlet("/Preferenze")
public class Preferenze extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DbManager db;
	private ServletConfig sc;

	public void init(ServletConfig sc){
		this.sc=sc;
	}
	
    public Preferenze() {
        super();
    }
    
    
    
  	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext scxt = sc.getServletContext();
  		db=(DbManager) scxt.getAttribute("DbManager");
  		if(db==null){
  			System.out.println("Crea nuovo DbManager preferenze");
  			try {
				db=new DbManager();
				scxt.setAttribute("DbManager", db);
			} catch (Exception e) {}
  		}
  		HttpSession session=request.getSession(false);
		String email=(String) session.getAttribute("email");
		String preference=request.getParameter("radio");
		String sms_email=request.getParameter("sms_email");
		if(preference.equalsIgnoreCase("sms")){
			int num=Integer.parseInt(sms_email);
			db.changePreferenceSms(email, num);
		}
		else if(preference.equalsIgnoreCase("email")){
			db.changePreferenceEmail(email, sms_email);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
