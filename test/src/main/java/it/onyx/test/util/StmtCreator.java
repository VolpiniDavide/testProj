package it.onyx.test.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class StmtCreator {
	
	
	//static Logger logger = Logger.getLogger(Factory_connection.class);
		
		
		
		public static String getQuery (String query) {
			
			String outQuery;
			
			Properties prop = new Properties();
			ClassLoader loader = Thread.currentThread().getContextClassLoader();
			
			try {
				InputStream fis = loader.getResourceAsStream("query.properties");
				prop.load(fis);
			}catch (FileNotFoundException e){
				System.out.println("can't load input-stream");
				e.printStackTrace();
			}catch (IOException e) {
				System.out.println("troubles with properties variable");
			}
			
			outQuery = prop.getProperty(query);
			
			return outQuery;
		}

		
		
		

	}
