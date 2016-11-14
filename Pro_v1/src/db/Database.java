package db;

import java.sql.Connection;        //数据库连接实例
import java.sql.DriverManager;     //数据库驱动管理类，调用其静态方法getConnection并传入数据库的URL获得数据库连接实例
import java.sql.Statement;         //操作数据库要用到的类，主要用于执行SQL语句
import java.util.ArrayList;
import java.sql.ResultSet;         //数据库查询结果集
import java.sql.SQLException;

@SuppressWarnings("unused")

public class Database {
	
	private static String DRIVER_MYSQL = "com.mysql.jdbc.Driver";    //MySQL JDBC驱动字符串
    private static String URL = "jdbc:mysql://localhost:3306/USER";
    private static Statement stmt;
    private Connection connection = null;
    
    private int co_count = 0;
    
    /* 
     * 连接数据库，每次使用各种函数之前，都需要调用本函数
     */
	public String connect()                         
	{
		 try{
	            Class.forName(DRIVER_MYSQL);     //加载JDBC驱动
	            connection = DriverManager.getConnection(URL,"root","1234");   //创建数据库连接对象
	            stmt = connection.createStatement();       //创建Statement对象
	            return "connect!";
	     }
		 catch (Exception e){
			 e.printStackTrace();
		 }
		 return "Success";
	}
	
	/*
	 * 断开数据库连接 同样需要自行调用
	 */
	public String close()
	{
		try{
			connection.close();
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return "Success close";
	}
	
	
	/*
	 * 登陆 传入用户名和密码
	 * 若成功则返回用户ID
	 * 失败则返回-1
	 */
	public int checkUser(String username,String password)
	{
		String temp = username + "' and password = '" + password;
		System.out.println(temp);
		int ID = findID(temp);
		return ID;
	}
	
	/*
	 * 尝试注册 如果注册失败则返回-1，否则返回新用户的ID
	 */
	public int signUp(String username,String password)
	{
		int count = findID(username);
		if(count != -1)
		{
			return -1;
		}
		int uid = count("user") + 1;    //查询现有的user的总个数，计算得到新用户的ID
		String insert_user = "insert user values(" + uid + ",'" + username + "','" + password + "')";   //插入新用户
		
		int tableid = count("usertable") + 1 ; //计算新的大事件表的ID
		String insert_table1 = "insert usertable values(" + tableid + "," + uid + ",'event" + uid + "','MyEvent',3)";
		String insert_table2 = "insert usertable values(" + (tableid+1) + "," + uid + ",'assoc" + uid + "','MyAssoc',3)"; 
		String create_table1 = "create table event" + uid+ "(EID int primary key not null,ETime Date,EName varchar(50))";
		String create_table2 = "create table assoc" + uid+ "(EID int not null, TID int not null,tname varchar(10))";
		
		try{
			stmt.execute(insert_user);
			stmt.execute(insert_table1);
			stmt.execute(create_table1);
			stmt.execute(create_table2);
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		
		return uid;
	}
	
	
	/* 11/7 测试通过
	 * 检查当前用户试图新建的表的命名是否和数据库中他个人的其他表重名 
	 * 没有重名时，结果为0
	 * 1号用户尝试新建一个为money的表，输入格式为  
	 * 		1~Money
	 */
	public int checkTableName(String a)
	{
		String[] mes = a.split("~");
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
    	catch (SQLException e)
    	{
    		e.printStackTrace();
    	}		
		
		return n;     //n为0代表成功
	}
	
	
	/* 11/7 测试通过
	 * 不存在同名表时，使用本函数，创建一个表
	 * 传入用户ID，用户给这个表起的名字，列数，每列的名字，长度
	 * 如1号用户Money表，存储2列，第一列为长度为12的time，第二个为长度为20的product
	 * 		1~Money~2~time~12~product~20
	 */
	public int createUserTable(String a)
	{
		String[] mes = a.split("~");
		
		String uid = mes[0];
		String uname = mes[1];
		int column_num = Integer.parseInt(mes[2]);
		
		//添加至usertable表
		int cnt = count("usertable") + 1; 
		String temp_insert = "insert usertable values(" + cnt + "," + uid + ",'t" + cnt + "','" + uname + "'," + (column_num+1) + ")" ;
		
		
		String temp_create = "create table t" + cnt + "(";            //创建新表的语句，新表名为tID,如usertable中的10号表，命名为t10
		 
		for(int i=0;i<column_num;i++)                                 //根据要求完成create语句
		{
			temp_create = temp_create + mes[3+2*i] + " varchar(" + mes[4+2*i] + "),";
		}
		temp_create = temp_create + "TID int primary key) ";  //添加外键关联该用户个人event表
		
		System.out.println(temp_create);
		try{
			stmt.execute(temp_insert);   //新增新表记录至usertable
			stmt.execute(temp_create);   //创建新表
			return 0;
		}
		catch (SQLException e){e.printStackTrace();return 1;}
	}
	
	/* 11/7 测试通过
	 * 输入用户ID
	 * 返回值是该用户的table个数~每个table的名字
	 */
	public String findUserTable(int uid)
	{
		/* sqk's requirement 2016/10/30
		 * input userid
		 * return table_count ~ tablename(user)1 ~ tablename(user)2 ~ ……………………………… 
		 * use ~(temporary) 
		 */
		ResultSet res = null;
		String temp = "select * from usertable where UID =" + uid;
		String ret = "";
		
		int count = 0;
		try {
			res = stmt.executeQuery(temp);
			while(res.next())
			{
				if(count!=0)
				{
					ret = ret + "~" +res.getString(4);
				}
				count++;
			}
			ret = count + ret ;
		}
		catch (SQLException e){e.printStackTrace();}
		
		return ret;
	}
	
	/* 11/7 测试通过
	 * 11/14修改待测试
	 * 输入用户id和表名 返回表的属性
	 */
	public String findTableColumn(int uid, String tablename)
	{
		/* sqk's requirement 2016/10/31
		 * in userid && tablename(user)
		 * return columncount ~ column1 ~ length1 ~ column2 ~ length2……………………………… 
		 * use ~(temporary) 
		 */
		ResultSet res = null;
	
		String ret = "";
		
		try {
			
			String dbtablename = getDBName(uid,tablename);
			ret = ret + co_count ;
			String temp = "desc " + dbtablename;
			
			res = null;
			
			res = stmt.executeQuery(temp);
			
			while(res.next())
			{
				ret = ret + "~" + res.getString(1) + "~" + res.getString(2);
			}
			ret = ret.replace("int(","");
			ret = ret.replace("varchar(","");
			ret = ret.replace(")","");
			
			return ret;
		}
		catch (SQLException e){e.printStackTrace();}
		return "";
	}
	
	/* 11/14 待测试
	 * 传入用户ID 表名 插入信息（栏间使用~分割）
	 * 插入成功返回1 否则返回-1
	 */
	public int insertSEvent(int uid,String tablename,String mes)
	{
		ResultSet res = null;

		int eid = 0;
		try {
			String dbtablename = getDBName(uid,tablename);//加入新记录的表的实际名字

			String temp = "select max(EID) from " + dbtablename ;
			res = stmt.executeQuery(temp);
			while(res.next())
			{
				eid = res.getInt(1);
			}
			mes = mes.replace("~","','");
			temp = "insert " + dbtablename + " values('" + mes +"'," + eid + ")" ;
			stmt.execute(temp);
			return 1;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			return -1;
		}
	}
	
	/* 11/14 待测试
	 * 获取一个用户ID及一个表名，将返回该表的所有内容
	 * 返回格式为 项数~第一项第一列~第一项第二列~……~第二项第一列~……
	 */
	public String findSEvent(int uid, String tablename)
	{
		ResultSet res = null; 
		String dbtablename = getDBName(uid,tablename);
		
		String mes= "";
		
		String temp_desc = "desc " + dbtablename;
		
		String temp_find = "select * from " + dbtablename ;
		
		try
		{
			int count = 0;
			res = stmt.executeQuery(temp_find);
			while(res.next())
			{
				count++;
				for(int i=1;i<=co_count;i++)
				{
					mes= mes + "~" + res.getString(i); 
				}
			}
			mes = count + mes;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return mes;
	}
	
	/*
	 * 输入数据为一个用户ID和一个usertablename
	 * 返回表内最新增加的五条数据
	 * （即ID值最大的五条数据）
	 * 由于仍需考虑该表内容不足五条的情况 返回值同函数findSEvent相同
	 */
	public String findSEventN5(int uid, String tablename)
	{
		ResultSet res = null; 
		String dbtablename = getDBName(uid,tablename);
		
		String mes= "";
		
		String temp_desc = "desc " + dbtablename;
		
		String temp_find = "select * from " + dbtablename + "order by TID limit 5";
		
		try
		{
			int count = 0;
			res = stmt.executeQuery(temp_find);
			while(res.next())
			{
				count++;
				for(int i=1;i<=co_count;i++)
				{
					mes= mes + "~" + res.getString(i); 
				}
			}
			mes = count + mes;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return mes;
	}
	
	
	/* private函数 请勿直接使用或修改！
	 * 下面函数用于转换 属于user的tablename 和 实际存于DB中的tablename
	 * 同时查询到该表的列数
	 * private 的 成员变量值 co_count 将被修改
	 * tablename则作为返回值 
	 */
	private String getDBName(int uid, String user_name)
	{
		ResultSet res = null;
		String temp = "select * from usertable where UID = " + uid + " and uname = '"  + user_name + "'";
		String db_name = "";
		
		try {
			res = stmt.executeQuery(temp);
			while(res.next())
			{
				db_name = res.getString(3);
				co_count = res.getInt(5);
			}
		}
		catch (SQLException e){e.printStackTrace();}
		
		return db_name;
	}

	/* 11/7 测试通过
	 * private函数 请勿直接使用或修改！！！
	 * 下面函数作用是在user表中找到符合条件的用户个数
	 * 当输入用户名和密码时，输入参数应该被拼接为 username + "' and passeword='" + password 
	 * 		如验证root，1234是否存在 root' and password = '1234 注意两边各缺少一个引号
	 * 当输入用户名检测是否有同名用户存在，只需要传入用户名 
	 * 		如验证root是否存在，传入root
	 * 用户名密码不匹配或者不存在同名用户时 均返回-1，其他时候都为正整数
	 */
	private int findID(String username)
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
	
	/* private函数 请勿直接使用或者修改！！！
	 * 用来查询个数 已有部分语句
	 * 传入参数直接为表名 则可用于查询当前已有元组个数
	 * 传入参数如果包含when，则可以完成筛选
	 */
	private int count(String tname) {
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
