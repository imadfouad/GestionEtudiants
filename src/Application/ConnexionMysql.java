package Application;


import javax.swing.*;
import java.sql.*;


public class ConnexionMysql {
	
	Connection conn=null;
	
	public static Connection ConnexionDb()
	{
		try
		{	
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3308/gestionetud?autoReconnect=true&useSSL=false","root","");
			return conn;
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
	}
}
