package utn.dds.g10.Utiles;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class GestorMail {
	
	static public void enviarMail(String destinatario, String asunto, String cuerpo){
		final String username = "dds.grupo10@gmail.com";
		final String password = "utndds2016";
	
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
	
		Session session = Session.getInstance(props, new javax.mail.Authenticator() { protected PasswordAuthentication getPasswordAuthentication() { return new PasswordAuthentication(username, password); } });
	
		try {
	
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("dds.grupo10@gmail.com"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario)); 
			message.setSubject(asunto); 
			message.setText(cuerpo);
	
			Transport.send(message);
	
		} catch (MessagingException e) {
			System.out.println("Hubo un error de autorizacion");
		}
	}
	
}


