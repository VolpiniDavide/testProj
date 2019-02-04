package it.onyx.test.mail;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import it.onyx.test.dao.UserDao;
import it.onyx.test.util.Util;



public class MailSender {

	
public static void sendEmail(String toAddress) throws AddressException,MessagingException {
		
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		final Properties props = new Properties(); 
		InputStream fis;
		
		try {
			fis = loader.getResourceAsStream("/mail.properties");
			props.load(fis);
		} catch (FileNotFoundException e1) {
			
			e1.printStackTrace();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		
		
		
		
		// sets SMTP server properties
		Properties properties = new Properties();
		properties.put("mail.smtp.host", props.getProperty("host"));
		properties.put("mail.smtp.port", props.getProperty("port"));
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
 
		// creates a new session with an authenticator
		Authenticator auth = new Authenticator() {
		public PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(props.getProperty("user"),props.getProperty("password"));
		}
		};
 
        Session session = Session.getInstance(properties, auth);
 
     // creates a new e-mail message
		Message msg = new MimeMessage(session);
		InternetAddress[] toAddresses = { new InternetAddress(toAddress) };
		
		ArrayList<UserDao> utenti = Util.listaUtenti();
		
		String mess = "here's the list of users that got registered to your system: ";
		for( UserDao ud : utenti) {
			mess = mess.concat(ud.getNome()).concat(", ");
		}
		
		
		try {
			msg.setFrom(new InternetAddress((props.getProperty("user"))));
			msg.setRecipients(Message.RecipientType.TO, toAddresses);
			msg.setSubject(props.getProperty("subject"));
			msg.setSentDate(new Date());
			msg.setText(mess);
		
			
		} catch (MessagingException e1) {
			
			e1.printStackTrace();
		}	
		
		// sends the e-mail
		try{
			Transport.send(msg);
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("troubles sending message");
		}
    }
	
	
}
