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
  			try {
				db=new DbManager();
				scxt.setAttribute("DbManager", db);
			} catch (Exception e) {}
  		}

		String email=request.getParameter("email");
		String password=request.getParameter("password");
		String imei=request.getParameter("imei");
		String preferenze=request.getParameter("radio");
		String campo=request.getParameter("campo");
		if(campo.equalsIgnoreCase("")) campo=email;
		if(email.equalsIgnoreCase("") || password.equalsIgnoreCase("") || imei.equalsIgnoreCase("") || preferenze.equalsIgnoreCase("")){
  			String message="I dati inseriti risultano mancanti o errati";
			request.setAttribute("messaggio", message);
	  		RequestDispatcher dispatcher=request.getRequestDispatcher("/error.jsp") ;
	  		dispatcher.forward(request, response);
		}
		else{
			boolean trovato = false, utentEsistente=false;
			try {
				trovato = db.trovaemail(email);
			} catch (Exception e) {	}
			if(trovato==true){
				utentEsistente=db.mailpass(email, password);
				if(utentEsistente==true){
					this.insertUsert(campo, preferenze, email, password, imei, request, response);
				}
				else{
					String message="L'email risulta già presente nel database";
					request.setAttribute("messaggio", message);
			  		RequestDispatcher dispatcher=request.getRequestDispatcher("/error.jsp") ;
			  		dispatcher.forward(request, response);
				}
			}
			else{
					this.insertUsert(campo, preferenze, email, password, imei, request, response);	
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
    public boolean isInteger( String input ){  
       try {  
          Long.parseLong(input); 
          return true;  
       }  
       catch( Exception e){ return false; }  
    }  
    
    public void insertUsert(String campo, String preferenze, String email, String password, String imei, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    	boolean c=this.isInteger(campo);
		System.out.println("Preferenze: "+preferenze+" campo: "+campo);
		if((preferenze.contains("email") && campo.contains("@")) || (preferenze.contains("sms") && c==true) ){
			try {
				db.creauser(email, password, campo, imei);
			} catch (Exception e) {System.out.println("ERRORE CREAUSER");	}
			String message="Registrazione Effettuata con successo</br>Adesso puoi effettuare il login, grazie</br>          Lo staff";
			request.setAttribute("messaggio", message);
	  		RequestDispatcher dispatcher=request.getRequestDispatcher("/error.jsp") ;
	  		dispatcher.forward(request, response);
		}
		else{
			String message="Registrazione non riuscita</br>Controlla le credenziali e ritenta";
			request.setAttribute("messaggio", message);
	  		RequestDispatcher dispatcher=request.getRequestDispatcher("/error.jsp") ;
	  		dispatcher.forward(request, response);
		}	
    }
    
    
}