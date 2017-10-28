package QQ;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConn 
{
	private static String driver="com.mysql.jdbc.Driver"; 
	private static String url="jdbc:mysql://localhost:3306/qq?useUnicode=true&amp;characterEncoding=utf-8&amp;useSSL=false";
	private static String username="root";
	private static String password="a1234";
	private static Connection conn=null;
	static{
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url,username,password);
		} catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static Connection openDB()
	{
		try 
		{
			if(conn.isClosed())
			{
				conn = DriverManager.getConnection(url,username,password);
			}
		}catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return conn;
	}
	public static void main(String[] args)
	{
		
	}
}
