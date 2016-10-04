package db;

import java.sql.Connection;        //数据库连接实例
import java.sql.DriverManager;     //数据库驱动管理类，调用其静态方法getConnection并传入数据库的URL获得数据库连接实例
import java.sql.Statement;         //操作数据库要用到的类，主要用于执行SQL语句
import java.util.ArrayList;
import java.sql.ResultSet;         //数据库查询结果集
import java.sql.SQLException;
public class Database {
	
	private static String DRIVER_MYSQL = "com.mysql.jdbc.Driver";    //MySQL JDBC驱动字符串
    private static String URL = "jdbc:mysql://localhost:3306/USER";
    private static Statement stmt;
    private Connection connection = null;
    
    public static void main()
    {
    	//new
    }
    
	public void connect()                           //链接数据库
	{
		 try
	        {
	            Class.forName(DRIVER_MYSQL);     //加载JDBC驱动
	            connection = DriverManager.getConnection(URL,"root","1234");   //创建数据库连接对象
	            stmt = connection.createStatement();       //创建Statement对象
	        } 
		 catch (Exception e){e.printStackTrace();}
	}
	
    public ResultSet query(String sql)                     //sql query
    {
        ResultSet result = null;
        try
        {
            result = stmt.executeQuery(sql);
        } 
        catch (SQLException e){e.printStackTrace();}
        return result;
    }
    
    public void executeSql(String sql) {
    	try
        {
            stmt.execute(sql);
        } 
    	catch (SQLException e){e.printStackTrace();}
    }
    
	public void Update(String up)
	{
		try {
			stmt.executeUpdate(up);
		} 
		catch (SQLException e) {e.printStackTrace();}
	}
	
	public void close()
	{
		try {
			connection.close();
		}
		catch (Exception e){e.printStackTrace();}
		return ;
	}
	
	public int count(String tname) {
		ResultSet cnt = null;
		int n = 0;
		String temp = "select count(*) from " + tname ;
    	try
        {
            cnt = stmt.executeQuery(temp);
            while(cnt.next())
            {
            	n = cnt.getInt(1);
            }
        } 
    	catch (SQLException e){e.printStackTrace();}
    	
    	return n;
    }
}
