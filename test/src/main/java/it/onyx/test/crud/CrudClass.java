package it.onyx.test.crud;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.sql.DataSource;

import it.onyx.test.DataSourceFactory.DataSourceFactory;
import it.onyx.test.util.StmtCreator;
import it.onyx.test.dao.UserDao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CrudClass {
	String firstName, lastName, num, id, email, password;
	static Logger mylogger = LogManager.getLogger(CrudClass.class);
	ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
    UserDao obj = (UserDao) context.getBean("UserDao");
	
	
                           // ---------------------------------- 	READ    -------------------------------- //
	
	public UserDao selectUser (String email, String password) {
		
		Statement stmt = null;
		ResultSet res = null;
		Connection connection = null;
		 UserDao ud = (UserDao) context.getBean("UserDao");
		DataSource ds = DataSourceFactory.getMySQLDataSource("videoteca");
		
		try {
			connection = ds.getConnection();
			stmt = connection.createStatement();
			res = stmt.executeQuery(StmtCreator.getQuery("selectCliente1")+email+StmtCreator.getQuery("andPwd")+password+"'");
			if(res.next()) {
				ud.setNome(res.getString("nome"));
				ud.setCognome(res.getString("cognome"));
				ud.setEmail(res.getString("email"));
				ud.setId_cliente(res.getString("ID_Cliente"));
				ud.setNum_telefono(res.getString("Num_Telefono"));
				ud.setPassword(res.getString("password"));
				
			} else {
				throw new Exception();
			}
			
		} catch (SQLException e) {
			mylogger.debug("sql exception");
		}catch ( Exception e) {
			mylogger.debug("empty resultSet for select user query");
		}finally {
			try {
				stmt.close();
				res.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
		return ud;
	}
	
	
						// ------------------------- MAIL EXIST --------------------------- //
	
	
public Boolean emailExists ( String email) {
		
		// verifico che la mail esista come valore salvato nel db e ritorno true per verifica durante il login
	
		DataSource ds = DataSourceFactory.getMySQLDataSource("videoteca");
		Statement stmt = null;
		ResultSet res = null;
		Boolean b = false;
		Connection con = null;
		
		try {
			con = ds.getConnection();
			stmt = con.createStatement();
			res = stmt.executeQuery(StmtCreator.getQuery("selectCliente1")+email+StmtCreator.getQuery("chiudeSelect"));
			if (res.next()) return true;
			
		} catch ( SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				stmt.close();
				res.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		return b;
		
	}


							// ---------------------------------- 	CREATE    -------------------------------- //


public Boolean createUser(UserDao ud) {
	Statement stmt = null;
	DataSource ds = DataSourceFactory.getMySQLDataSource("videoteca");
	ResultSet res = null;
	Connection connection = null;
	String id = "0";	
	Boolean ex = false;
	
	try {
		connection = ds.getConnection();
		stmt = connection.createStatement();
		stmt.executeUpdate(StmtCreator.getQuery("insertCliente1")+ud.getNome()+"','"+ud.getCognome()+"','"+ud.getNum_telefono()+"','"+id+"','"+ud.getEmail()+"','"+ud.getPassword()+StmtCreator.getQuery("chiudeInsert"));
		res = stmt.executeQuery(StmtCreator.getQuery("selectCliente1")+ud.getEmail()+StmtCreator.getQuery("andPwd")+ud.getPassword()+"'");
		
		if(res.next()) {
			ex= true;
		} else {
			ex = false;
		}
		
	} catch (SQLException e) {
		e.printStackTrace();
	} finally {
		try {
			stmt.close();
			res.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

	
	
	return ex;
	
}

				// ------------------------------- FIND ALL ---------------------------------- //


public ArrayList<UserDao> findAll(){
	   
		DataSource ds = DataSourceFactory.getMySQLDataSource("videoteca");
		Statement stmt = null;
		ResultSet res = null;
		Connection con = null;
		
		ArrayList<UserDao> utenti = new ArrayList<UserDao>();
		
		try {
			con = ds.getConnection();
			stmt = con.createStatement();
			res = stmt.executeQuery(StmtCreator.getQuery("selectCliente2"));
			
			
			while (res.next()) {
			
			String nome = res.getString("Nome");
			String cognome = res.getString("Cognome");
			String num = res.getString("Num_Telefono");
			String id = res.getString("ID_Cliente");
			String mail = res.getString("email");
			String passw = res.getString("password");
			
			UserDao ud = new UserDao();
			
			ud.setCognome(cognome);
			ud.setNome(nome);
			ud.setNum_telefono(num);
			ud.setId_cliente(id);
			ud.setEmail(mail);
			ud.setPassword(passw);
			
			utenti.add(ud);
			
			}
			
		} catch ( SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				stmt.close();
				con.close();
				res.close();
			} catch (SQLException e) {
			
				e.printStackTrace();
			}
			
		}

		
		return utenti;
		
	   
	   
}


}
