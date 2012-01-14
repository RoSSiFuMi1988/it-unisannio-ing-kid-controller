package project.univ;

import java.io.IOException;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SendMail")
public class SendMail extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 private static String HOST = "smtp.gmail.com";
	 private static String USER = "erny1790@gmail.com";
	 private static String PASSWORD = "Emanuela";
	 private static String PORT = "465";
	 private static String STARTTLS = "true";
	 private static String AUTH = "true";
	 private static String DEBUG = "true";
	 private static String SOCKET_FACTORY = "javax.net.ssl.SSLSocketFactory";
   
    public SendMail() {
        super();
    }    
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response){
    	try {
			doGet(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
        
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    		String FROM="erny1790@gmail.com";   		
    		String TO = (String) request.getAttribute("to");
        	String SUBJECT = (String) request.getAttribute("state");
        	String TEXT = (String) request.getAttribute("text");
    		/* EMAIL HTML
 			String TO = (String) request.getParameter("to");
        	String SUBJECT = (String) request.getParameter("state");
        	String TEXT = (String) request.getParameter("text");
    		 */
        	 
        				Properties props = new Properties();
        	 	        props.put("mail.smtp.host", HOST);
        	 	        props.put("mail.smtp.port", PORT);
        	 	        props.put("mail.smtp.user", USER);
        	 	        props.put("mail.smtp.auth", AUTH);
        	 	        props.put("mail.smtp.starttls.enable", STARTTLS);
        	 	        props.put("mail.smtp.debug", DEBUG);
        	 	        props.put("mail.smtp.socketFactory.port", PORT);
        	 	        props.put("mail.smtp.socketFactory.class", SOCKET_FACTORY);
        	 	        props.put("mail.smtp.socketFactory.fallback", "false");
        	 	 
        	 	        try {
        	 	            //Obtain the default mail session
        	 	            Session session = Session.getDefaultInstance(props, null);
        	 	            session.setDebug(true);
        	 	            //Construct the mail message
        	 	            MimeMessage message = new MimeMessage(session);
        	 	            message.setText(TEXT);
        	 	            message.setSubject(SUBJECT);
        	 	            message.setFrom(new InternetAddress(FROM));
        	 	            message.addRecipient(RecipientType.TO, new InternetAddress(TO));
        	 	            message.saveChanges();
        	 	            //Use Transport to deliver the message
        	 	            Transport transport = session.getTransport("smtp");
        	 	            transport.connect(HOST, USER, PASSWORD);
        	 	            transport.sendMessage(message, message.getAllRecipients());
        	 	            transport.close();
        	 	        } catch (Exception e) {
        	 	            e.printStackTrace();
        	 	        }
       	}
 }