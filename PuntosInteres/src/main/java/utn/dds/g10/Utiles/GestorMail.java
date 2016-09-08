package utn.dds.g10.Utiles;

import java.util.*;

import javax.mail.Message;
import javax.mail.MessagingException;
//import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class GestorMail {
	
	static public void enviarMail(String destinatario, String asunto, String cuerpo){
		
		  // Recipient's email ID needs to be mentioned.
	      String to = destinatario;

	      // Sender's email ID needs to be mentioned
	      String from = "rbautista@est.frba.utn.edu.ar";

	      // Assuming you are sending email from localhost
	      String host = "smtp.frba.utn.edu.ar";

	      // Get system properties
	      Properties properties = System.getProperties();

	      // Setup mail server
	      properties.setProperty("mail.smtp.host", host);
	      
	      properties.put("mail.smtp.port", "25");
	      properties.put("mail.smtp.auth", "true");
	      properties.put("mail.smtp.starttls.enable", "true");
	      properties.put("mail.smtp.starttls.enable", "true");

	      // Get the default Session object.
	      Session session = Session.getDefaultInstance(properties);

	      try{
	         // Create a default MimeMessage object.
	         MimeMessage message = new MimeMessage(session);
	         
	         // Set From: header field of the header.
	         message.setFrom(new InternetAddress(from));

	         // Set To: header field of the header.
	         message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

	         // Set Subject: header field
	         message.setSubject(asunto);

	         // Send the actual HTML message, as big as you like
	         message.setContent("<h1>"+ cuerpo +"</h1>", "text/html" );

	         
	         //transport.connect("smtp.gmail.com", "<----- Your GMAIL ID ----->", "<----- Your GMAIL PASSWORD ----->");

	         // Send message
	         Transport.send(message, "rbautista", "Lola2013");
	         System.out.println("Sent message successfully....");
	      }catch (MessagingException mex) {
	         mex.printStackTrace();
	      }
	      
	      
	}
}


