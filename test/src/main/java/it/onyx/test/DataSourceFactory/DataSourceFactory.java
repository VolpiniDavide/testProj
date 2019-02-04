package it.onyx.test.DataSourceFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.sql.DataSource;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class DataSourceFactory {
	
	
	public static DataSource getMySQLDataSource(String dbName) {
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		Properties props = new Properties();
		InputStream fis = null;
		MysqlDataSource mysqlDS = null;
		
		try {
			fis = loader.getResourceAsStream("/data_source.properties");
			props.load(fis);
			mysqlDS = new MysqlDataSource();
			mysqlDS.setURL(props.getProperty("MYSQL_DB_URL")+dbName);
			mysqlDS.setUser(props.getProperty("MYSQL_DB_USERNAME"));
			mysqlDS.setPassword(props.getProperty("MYSQL_DB_PASSWORD"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return mysqlDS;
	}
	
	

}
