//中文UTF-8 NO BOM
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.IOException;
import java.io.InputStream;

public class DataSourceConnection {

    public DataSourceConnection() throws ClassNotFoundException {

    }

    public Connection getConnection() throws SQLException {
        Properties prop=new Properties();
        InputStream in = getClass().getResourceAsStream("dbConnection.properties");
        try {
        	prop.load(in);
        	in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String driver = prop.getProperty("jdbc.driver");
        String connectionURL = prop.getProperty("jdbc.url");
        String username = prop.getProperty("jdbc.username");
        String password = prop.getProperty("jdbc.password");
        //System.out.println(driver); 
        //System.out.println(connectionURL); 
        //System.out.println(username); 
        //System.out.println(password);   
        System.out.println("Java Version is: "+System.getProperty("java.version")); 
        System.out.println(System.getProperty("sun.arch.data.model") + "-bit JVM");
 

        try {
            Class.forName(driver);
        } catch (ClassNotFoundException  e) {
            e.printStackTrace();
        }
        
        return DriverManager.getConnection(connectionURL, username, password);
    }
}