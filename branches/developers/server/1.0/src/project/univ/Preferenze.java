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
  			try {
				db=new DbManager();
				scxt.setAttribute("DbManager", db);
			} catch (Exception e) {}
  		}
  		PrintWriter pw=response.getWriter();
  		HttpSession session=request.getSession(false);
		String email=(String) session.getAttribute("email");
		String preference=request.getParameter("radio");
		String sms_email=request.getParameter("sms_email");
		String preference1 = "sms";
		if(preference.equalsIgnoreCase("email"))
			preference1="email_notifica";
		Notify c=new Notify(preference1, sms_email);
		if( (preference.equalsIgnoreCase("email")&&sms_email.contains("@")) || (preference.equalsIgnoreCase("sms")&&this.isInteger(sms_email)) ){
			try {
				db.changePreference(email, c);
				pw.println("<html><head><meta http-equiv=\"refresh\" content=\"5; url=gestore.html\"></head>");
				pw.println("<body>Preferenze delle notifiche modificate con successo</body>");
			} catch (Exception e) {	e.printStackTrace(); }
		}
		else{
			pw.println("<html><head><meta http-equiv=\"refresh\" content=\"5; url=gestore.html\"></head>");
			pw.println("<body>Impossibile cambiare le preferenze per le notifiche.<br> La preghiamo di riprovare, Grazie</body>");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	public boolean isInteger( String input )  
    {  
       try {  
          Long.parseLong(input); 
          return true;  
       }  
       catch( Exception e){ return false; }  
    }  
}