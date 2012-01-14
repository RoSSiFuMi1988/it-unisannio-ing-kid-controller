package project.univ;

import java.io.IOException;
import java.io.InputStream;
import java.util.StringTokenizer;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

@WebServlet("/Impostazione")
public class Impostazione extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private int accuratezza;
	@SuppressWarnings("unused")
	private int l;
	private double raggio;
	private ServletConfig sc;
	private DbManager db;
	
	public void init(ServletConfig sc){
		this.sc=sc;
	}
       
    public Impostazione() {
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
		byte[] tmps=null;
		String localita=request.getParameter("localita");
		String localita1=localita.replaceAll(" ", "+");
		System.out.println("-"+localita+"-");
		if(localita.equalsIgnoreCase("")){
			System.out.println("Sto nell'if");
  			String message="I dati inseriti risultano mancanti o errati";
			request.setAttribute("messaggio", message);
	  		RequestDispatcher dispatcher=request.getRequestDispatcher("/error2.jsp") ;
	  		dispatcher.forward(request, response);
		}
		else{
			try{
			raggio = Double.parseDouble(request.getParameter("raggio"));
			String url="https://maps.google.com/maps/geo?output=csv&q="+localita1;
			HttpClient httpclient = new DefaultHttpClient();
			HttpGet httpget = new HttpGet(url);
			HttpResponse response1 = httpclient.execute(httpget);
			HttpEntity entity = response1.getEntity();
			if (entity != null) {
			    InputStream instream = entity.getContent();
			    tmps = new byte[2048];
			    while ((l = instream.read(tmps)) != -1) {
			    }
			}
			String entity1=new String(tmps);
			StringTokenizer tk=new StringTokenizer(entity1, ",");
			int status=Integer.parseInt(tk.nextToken());
			accuratezza = Integer.parseInt(tk.nextToken());
			String latitudine=tk.nextToken();
			String longitudine=(String) tk.nextToken().subSequence(0, 10);
			if(status != 200){
				String message="L'indirizzo inserito risulta essere errato.";
				request.setAttribute("messaggio", message);
		  		RequestDispatcher dispatcher=request.getRequestDispatcher("/error2.jsp") ;
		  		dispatcher.forward(request, response);
			}
			else{
				HttpSession session=request.getSession(false);
				String imei=(String) session.getAttribute("imei");
				try {
					db.setLocation(imei, latitudine, longitudine, raggio);
				} catch (Exception e) {	e.printStackTrace();  }
				String message="Le coordinate relative all'indirizzo inserito sono:</br>Latitudine: "+latitudine+"</br>Longitudine"+longitudine;
				request.setAttribute("messaggio", message);
		  		RequestDispatcher dispatcher=request.getRequestDispatcher("/error2.jsp") ;
		  		dispatcher.forward(request, response);
			}
			}catch(Exception e){
	  			String message="I dati inseriti risultano mancanti o errati";
				request.setAttribute("messaggio", message);
		  		RequestDispatcher dispatcher=request.getRequestDispatcher("/error2.jsp") ;
		  		dispatcher.forward(request, response);
	  		}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
