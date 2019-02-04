package it.onyx.test.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import it.onyx.test.DataSourceFactory.DataSourceFactory;
import it.onyx.test.crud.CrudClass;
import it.onyx.test.dao.UserDao;

public class Util {
	
	static ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
	
	
	// --------------------- metodo login -----------------------------//
	
	
		public static UserDao login ( String email , String password) {
			
			 
			CrudClass cc = new CrudClass();
			
			UserDao ud = cc.selectUser(email, password);
			
			return ud;
			
		}
		
		
		//---------------------metodo verifica esistenza mail --------------------------//
		
		
		public static Boolean emailExist (String email) {
			
			CrudClass cc = new CrudClass();
			return cc.emailExists(email);
			
		}
		
		
		// --------------------------------- metodo create ----------------------                //
		
		public static Boolean create ( UserDao ud) {
			
			CrudClass cc = new CrudClass();
			
			if (cc.createUser(ud)) 
				return true;
			else return false;
			
		}
		
		
		// --------------------------------- metodo findAll ----------------------                //
		
		public static ArrayList<UserDao> findAll (){
			
			CrudClass cc = new CrudClass();
			
			ArrayList <UserDao> users = cc.findAll();
			
			return users;
			
		}
		
		
		// --------------------------------- metodo sendMail ----------------------                //
			
			public static void sendEmail(String toAddress) throws AddressException {
					
				ClassLoader loader = Thread.currentThread().getContextClassLoader();
				final Properties props = new Properties();
				InputStream fis = null;
				fis = loader.getResourceAsStream("mail.properties");
				
				try {
					props.load(fis);
				} catch (IOException e) {
					// TODO Auto-generated catch block
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
					try {
						msg.setFrom(new InternetAddress((props.getProperty("user"))));
						msg.setRecipients(Message.RecipientType.TO, toAddresses);
						msg.setSubject(props.getProperty("subject"));
						msg.setSentDate(new Date());
						msg.setText("Welcome" + toAddress+" "+props.getProperty("message"));
						
					} catch (MessagingException e1) {
						// TODO Auto-generated catch block
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
			
			
			
			
			public static ArrayList<UserDao> listaUtenti(){
				
				DataSource ds = DataSourceFactory.getMySQLDataSource("videoteca");
				Statement stmt;
				ResultSet res = null;
				ArrayList<UserDao> utenti = new ArrayList<UserDao>();
				
				try {
				Connection con = ds.getConnection();
				stmt = con.createStatement();
				res = stmt.executeQuery(StmtCreator.getQuery("selectCliente2"));
				
				
				
				
				while (res.next()) {
				
				String nome = res.getString("Nome");
				String cognome = res.getString("Cognome");
				String num = res.getString("Num_Telefono");
				String id = res.getString("ID_Cliente");
				String mail = res.getString("email");
				String passw = res.getString("password");
				
				UserDao ud = (UserDao) context.getBean("UserDao");
				
				
				ud.setCognome(cognome);
				ud.setNome(nome);
				ud.setNum_telefono(num);
				ud.setId_cliente(id);
				ud.setEmail(mail);
				ud.setPassword(passw);
				
				
				utenti.add(ud);
				
				}
				}catch (SQLException e) {
					System.out.println("errore nel metodo fai-lista-utenti");
				}

			
			return utenti;
			
			}
		

}

