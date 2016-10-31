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
	
	public int findID(String username)
	{
		int ID = -1;
		String temp = "select ID from user where name='" + username + "'" ;
		ResultSet rs;
		try {
			rs = stmt.executeQuery(temp);
			while(rs.next())
	        {
	        	ID = rs.getInt(1);
	        }
		} 
		catch (SQLException e){e.printStackTrace();}
		
		return ID;
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
	
	public void newuser(int UID)
	{
		int tid = count("usertable") + 1;
		String temp = "insert usertable value(" + tid + "," + UID + ",'event" + UID + "','event',3)";            //创建个人event表的记录
		try
		{
			stmt.execute(temp);
			temp = "create table event" + UID + "(EID int primary key not null,ETime Date,EName varchar(50))";   //创建该用户event表
			stmt.execute(temp);
		}
		catch (SQLException e){e.printStackTrace();}
	}
	
	/*public void newtable(int UID, String uname, ArrayList<Table> request)    //UID为用户ID，uname为用户自己备注表明，request为新建要求
	{
		int cnt = count("usertable") + 1;                             //计算新建表在usertable里的ID
		String temp_create = "create table t" + cnt + "(";            //创建新表的语句，新表名为tID,如usertable中的10号表，命名为t10
		 
		int co_count = 0;                                             //新表列数
		for (Table a: request)                                        //根据要求完成create语句
		{
			co_count ++;
			temp_create = temp_create + a.cname + "  " + a.ctype + ", ";
		}
		temp_create = temp_create + "TID int primary key, EID int, foreign key(EID) references event" + UID + "(EID) ) ";  //添加外键关联该用户个人event表

		String temp_insert = "insert usertable values(" + cnt + "," + UID + ",'t" + cnt + "','" + uname + "'," + co_count + ")" ; //在usertable中增加本表记录
		try{
			stmt.execute(temp_insert);   //新增新表记录至usertable
			stmt.execute(temp_create);   //创建新表
		}
		catch (SQLException e){e.printStackTrace();}
	}*/
	
	public int checkTableName(String a)
	{
		
/*		String[] mes = a.split("~");
		String uid = mes[0];
		String tname = mes[1];
		
		ResultSet cnt = null;
		int n = 0;
	
		String temp = "select count(*) from usertable where UID = " + uid + " and uname='" + tname + "'" ;
    	try
        {
            cnt = stmt.executeQuery(temp);
            while(cnt.next())
            {
            	n = cnt.getInt(1);
            }
        } 
    	catch (SQLException e){e.printStackTrace();}

		return n;     //n为0代表成功
			*/	
		return 0;
	}
	
	public int createUserTable(String a)
	{
		String[] mes = a.split("~");
		
		String uid = mes[0];
		String uname = mes[1];
		int column_num = Integer.parseInt(mes[2]) + 2;
		
		//添加至usertable表
		int cnt = count("usertable") + 1; 
		String temp_insert = "insert usertable values(" + cnt + "," + uid + ",'t" + cnt + "','" + uname + "'," + (column_num+2) + ")" ;
		
		
		String temp_create = "create table t" + cnt + "(";            //创建新表的语句，新表名为tID,如usertable中的10号表，命名为t10
		 
		for(int i=0;i<column_num;i++)                                 //根据要求完成create语句
		{
			temp_create = temp_create + mes[3+2*i] + "varchar(" + mes[4+2*i] + "',";
		}
		temp_create = temp_create + "TID int primary key, EID int, foreign key(EID) references event" + uid + "(EID) ) ";  //添加外键关联该用户个人event表
		
		try{
			stmt.execute(temp_insert);   //新增新表记录至usertable
			stmt.execute(temp_create);   //创建新表
			return 0;
		}
		catch (SQLException e){e.printStackTrace();return 1;}
	}
}
