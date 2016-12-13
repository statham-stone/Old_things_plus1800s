package db;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;        //数据库连接实例
import java.sql.DriverManager;     //数据库驱动管理类，调用其静态方法getConnection并传入数据库的URL获得数据库连接实例
import java.sql.Statement;         //操作数据库要用到的类，主要用于执行SQL语句
import java.util.ArrayList;
import java.util.Calendar;
import java.sql.ResultSet;         //数据库查询结果集
import java.sql.SQLException;

import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.text.SimpleDateFormat;

@SuppressWarnings("unused")

//12/01 整体改动：UID不再是int，改为String
public class Database {
	
	private static String DRIVER_MYSQL = "com.mysql.jdbc.Driver";    //MySQL JDBC驱动字符串
    private static String URL = "jdbc:mysql://localhost:3306/USER";
    private static Statement stmt;
    private Connection connection = null;
    
    private int tableID  = 0;
    private int co_count = 0;
    
    /* 12/07新测试通过
     * 连接数据库，每次使用各种函数之前，都需要调用本函数
     */
	public String connect()                         
	{
		 try{
	            Class.forName(DRIVER_MYSQL);     //加载JDBC驱动
	            connection = DriverManager.getConnection(URL,"root","statham+1s");   //创建数据库连接对象
	            stmt = connection.createStatement();       //创建Statement对象
	            return "connect!";
	     }
		 catch (Exception e){
			 e.printStackTrace();
		 }
		 return "Success";
	}
	
	/* 12/07新测试通过
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
	
	//++++++++++注册 登陆 部分
	//12/7 新测试完整通过
	
	/* 12/1 增加新功能 内部完成统计数值的更新
	 * 登陆 传入用户名和密码
	 * 若成功则返回用户ID
	 * 失败则返回-1
	 */
	public int checkUser(String username,String password)
	{
		String date = getTime();
		String temp = username + "' and password = '" + password;
		//System.out.println(temp);
		int ID = findID(temp);

		return ID;
	}
	/* 12/1 待测试 
	 * 新改动，注册时内部自动生成4位日期
	 * 测试通过
	 * 尝试注册 如果注册失败则返回-1，否则返回新用户的ID
	 */
	public int signUp(String username,String password)
	{
		String date = getTime();
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
		String insert_table3 = "insert usertable values(" + (tableid+2) + "," + uid + ",'stati" + uid + "','MyStati',2)"; 
		
		String create_table1 = "create table event" + uid+ "(EID int primary key not null,ETime Date,EName varchar(50))";
		String create_table2 = "create table assoc" + uid+ "(EID int not null, TID int not null,tableid int not null)";
		String create_table3 = "create table stati" + uid+ "(Time varchar(9) not null, Count int not null)";
		String insert_count = "insert stati" + uid + " values(" + date + ",0)";
		try{
			stmt.execute(insert_user);
			stmt.execute(insert_table1);
			stmt.execute(insert_table2);
			stmt.execute(insert_table3);
			
			stmt.execute(create_table1);
			stmt.execute(create_table2);
			stmt.execute(create_table3);
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		String uID = uid + "";
		template(uID);
		return uid;
	}
	//++++++++++注册 登陆 部分
	
	/* 12/07 新测试通过
	 * 11/7 测试通过
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
	
	
	/* 12/07 测试通过
	 * 11/7 测试通过
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
	
	
	/* 12/07 新测试通过
	 * 11/7 测试通过
	 * 输入用户ID
	 * 返回值是该用户的table个数~每个table的名字
	 */
	public String findUserTable(String uid)
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
				if(count!=0 && count!=1 && count != 2)//不返回关联表和大事件表 ,不返回统计表
				{
					ret = ret + "~" +res.getString(4);
					
				}
				count++;
			}
			ret = (count-3) + ret ;
		}
		catch (SQLException e){e.printStackTrace();}
		
		return ret;
	}
	
	/* 12/07 新测试通过
	 * 11/7 测试通过
	 * 11/14修改待测试 通过
	 * 输入用户id和表名 返回表的属性（包括ID）
	 */
	public String findTableColumn(String uid, String tablename)
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
	
	/* 12/07 新测试通过
	 * 12/1 新修改
	 * 11/14 测试通过
	 * 加入一条小事件
	 * 传入用户ID 表名 插入信息（栏间使用~分割）
	 * 插入成功返回1 否则返回-1
	 * 
	 */
	public int insertSEvent(String uid,String tablename,String mes)
	{
		ResultSet res = null;

		int eid = 0;
		try {
			String dbtablename = getDBName(uid,tablename);//加入新记录的表的实际名字

			String temp = "select max(TID) from " + dbtablename ;
			res = stmt.executeQuery(temp);
			while(res.next())
			{
				eid = res.getInt(1) + 1;
			}
			mes = mes.replace("~","','");
			temp = "insert " + dbtablename + " values('" + mes +"'," + eid + ")" ;
			stmt.execute(temp);
			
			updateStati(uid);
			return 1;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			return -1;
		}
	}
	
	/* 12/07 新测试通过
	 * 11/14 测试通过
	 * 获取一个用户ID及一个表名，将返回该表的所有内容,默认返回ID
	 * 返回格式为 项数~第一项第一列~第一项第二列~……~第二项第一列~……
	 */
	public String findSEvent(String uid, String tablename)
	{
		ResultSet res = null; 
		String dbtablename = getDBName(uid,tablename);
		
		String mes= "";
		
		String temp_desc = "desc " + dbtablename;
		
		String temp_find = "select * from " + dbtablename ;
		
		//System.out.println("co_count = " + co_count);
		//System.out.println(temp_find);
		try
		{
			int cnt = 0;
			res = stmt.executeQuery(temp_find);
			while(res.next())
			{
				cnt++;
				//System.out.println("count = " + cnt);
				for(int i=1;i<=co_count;i++)
				{
					mes= mes + "~" + res.getString(i); 
					//System.out.println(i+mes);
				}
			}
			mes = cnt + mes;
			//System.out.println(mes);
			return mes;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			return "";
		}
	}
	
	/* 12/07 新测试通过
	 * 11/15测试通过
	 * 输入数据为一个用户ID和一个usertablename
	 * 返回表内最新增加的五条数据
	 * （即ID值最大的五条数据）
	 * 由于仍需考虑该表内容不足五条的情况 返回值同函数findSEvent相同
	 */
	public String findSEventN5(String uid, String tablename)
	{
		ResultSet res = null; 
		String dbtablename = getDBName(uid,tablename);
		
		String mes= "";
		
		String temp_desc = "desc " + dbtablename;
		
		String temp_find = "select * from " + dbtablename + " order by TID desc limit 5";
		
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
	
	/* 12/07测试通过
	 * 涂神需求1st
	 * 11/15 待测试
	 * 输入为用户ID
	 * 输出为该用户所有表名（大事件表关联表除外）
	 */
	public String tableBrief(String uid)
	{
		ResultSet res = null;
		String temp = "select uname from usertable where UID= " + uid;
		String result = ""; 
		try{
			res = stmt.executeQuery(temp);
			while(res.next())
			{
				result = result + "~" + res.getString(1);
			}
			result = result.replace("~MyEvent~MyAssoc~MyStati",""); //去掉事件表和关联表,统计表
			if('~'==result.charAt(0))
			{
				result =result.substring(1);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return result;
	}
	
	/* 12/07 测试通过
	 * 涂神需求第二个
	 * 11/15 待测试 超复杂DB语句
	 * 测试结果表明我好像一次就写对了我对此表示怀疑
	 * 输入为用户ID
	 * 返回为事件名字~关联小事件个数^……
	 */
	public String eventBrief(String uid)
	{
		ResultSet res = null;
		String result = "" ;

		String temp = "select EName,count(*) from event" + uid + " e join assoc" + uid +" a where a.EID = e.EID group by EName";
		
		try{
			res = stmt.executeQuery(temp);
			while(res.next())
			{
				result = result + "^" + res.getString(1) + "~" + res.getString(2);
			}
			result = result.substring(1); //去掉事件表和关联表
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return result;
	}
	
	/* 12/07测试通过
	 * 11/15 涂神需求第三个
	 * 测试通过
	 * 输入为用户id~表名
	 * 输出为小事件名~小事件全局ID^……
	 * 小事件全局ID由本表ID拼接小事件内TID拼成
	 */
	public String tableContent(String uidPLUStname)
	{
		ResultSet res = null;
		String result = "";
		String[] mes = uidPLUStname.split("~");
		
		String uid = mes[0];
		String uname = mes[1];
		
		String dbtablename = getDBName(uid,uname);
		int tid = tableID;
		
		String temp = "select * from " + dbtablename ;
		try
		{
			res = stmt.executeQuery(temp);
			while(res.next())
			{
				result = result + "^" + res.getString(1) + "~" + tid + "_"  +res.getString("TID");
			}
			result = result.substring(1);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return result ;
	}
	
	/* 涂神需求四 old version
	 * 11/15待填充
	 * 输入为用户id和key
	 * 输出为表名~唯一ID~小事件名字
	 */
	public String searchRequest(String uid, String key)
	{
		ResultSet res = null;
		String result = "";
		String[] tables = tableBrief(uid).split("~");
		int cnt_tables = tables.length;
		
		for(int i=0;i<cnt_tables;i++)
		{
			String thistable = searchInTable(uid,tables[i],key);
			if(!("".equals(thistable)))
			{
				result += "^" + thistable;
			}
		}
		if(!("".equals(result)))
		{
			result = result.substring(1);
		}
		return result;
	}
	
	
	
	
	/* 12/07 测试通过
	 * 11/15 涂神需求五
	 * 测试通过
	 * 输入为用户ID~大事件EName~ETime~小事件全局ID~小事件全局ID
	 * 输出为大事件ID
	 */
	public int submitEvent(String mes)
	{
		String[] mesin   = mes.split("~");
		String 	 uid     = mesin[0];
		String 	 EName   = mesin[1];
		String 	 ETime   = mesin[2];
		int 	 t_count = mesin.length;
		
		for(int i=3;i<t_count;i++)
		{
			if (!checkTableID(mesin[i],uid))
				return -1;
		}
		
		ResultSet res = null;
		String find_eid = "select max(EID) from event" + uid ;
		int eid = 0;
		try{
			res = stmt.executeQuery(find_eid);
			while(res.next())
			{
				eid = res.getInt(1) + 1;
			}
			String insert_e = "insert event" + uid + " values(" + eid + "," + ETime + ",'" + EName + "')";
			stmt.execute(insert_e);
			
			for(int j=3;j<t_count;j++)
			{
				String[] mestemp = mesin[j].split("_");
				String insert = "insert assoc" + uid + " values(" + eid + "," + mestemp[1] + "," + mestemp[0] + ")";
				stmt.execute(insert);
			}
			updateStati(uid);
			return eid;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			return -1;
		}
		
	}
	
	/* 12/07 新测试通过
	 * 12/1 待测试
	 * 获得用户ID，返还柱状图相关讯息，讯息为用户日期~修改条数
	 * 
	 */
	public String Bar(String uid)
	{
		complete(uid);
		String mes = "";
		String check = "select * from stati" + uid + " order by Time";
		try{
			ResultSet res = stmt.executeQuery(check);
			while (res.next())
			{
				mes = mes + "~" + res.getString(1) + "~" + res.getString(2);
			}
			if(mes.length()>1)
			{
				mes = mes.substring(1);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return mes;
	}
	
	/* 12/11 新测试通过 
	 * 厉害了我的姐  Bar图可以指定某一时间段你知道了吗
	 * 
	 * 获得用户ID及天数,天数是完全任意的，正整数就可以
	 * 返还柱状图在对应天数时间段内相关讯息，讯息为用户日期~修改条数
	 */
	public String Bar(String uid,String days)
	{
		String mes = "";
		
		//嵌套order的本质是为了反序再反序得到正序
		String check = "select * from (select * from stati" + uid + " order by Time desc limit " + days + " ) a order by Time";
		
		try{
			ResultSet res = stmt.executeQuery(check);
			while (res.next())
			{
				mes = mes + "~" + res.getString(1) + "~" + res.getString(2);
			}
			if(mes.length()>1)
			{
				mes = mes.substring(1);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return mes;
	}
	
	/* 12/07 新测试通过
	 * 12/1 待测试
	 * 获得用户ID，返还饼状图相关讯息，讯息为用户表~拥有的信息数，不包括关联表、大事件表、统计表
	 * 
	 */
	public String Pie(String uid)
	{
		String mes = "";
		String[] tables = findUserTable(uid).split("~");
		if(tables.length>1)
		{
			for(int i = 1; i< tables.length; i++)
			{
				String dbtname_i = getDBName(uid,tables[i]);
				int cn = count(dbtname_i);
				mes = mes + "~" + tables[i] + "~" + cn ; 
			}
			mes = mes.substring(1);
		}
		return mes;
	}
	
	
	//use by statham.
    public String statham_column(String uid,String tablename)
    {
    	String big=findTableColumn(uid, tablename);
    	String[] arr=big.split("~");
    	String littleString="";
    	littleString=arr[1];
    	for(int i=3;i<arr.length-2;i=i+2)
    	{
    		littleString=littleString+"~"+arr[i];
    	}
    	return littleString;
    }
    
    /* 12/07 新测试通过
     * sqk需求
     * 删除小事件
     * 传入正确的用户ID,表名及小事件ID
     * 对统计数据产生影响
     */
    public boolean deleteSEvent(String uid,String tablename,String TID)
    {
    	String dbtablename = getDBName(uid,tablename);
    	String delete = "delete from " + dbtablename + " where TID=" + TID;
    	String delete_event = "delete from assoc" + uid + " where TID=" + TID;
    	try
    	{
    		stmt.execute(delete);
    		stmt.execute(delete_event);
        	updateStati(uid);
    		return true;
    	}
    	catch(SQLException e)
		{
			e.printStackTrace();
			return false;
		}
    }
    
    /* 12/07 新测试通过
     * sqk需求
     * 修改小事件
     * 为了方便处理，此处对数据库操作的本质是删除，插入
     * 传入正确的用户ID，表名，小事件ID，及小事件的新信息(以~连接)
     * 传回true代表成功
     * 
     * 对统计数据产生影响
     */
    public boolean updateSEvent(String uid,String tablename,String TID,String mesin)
    {
    	String[] mes = mesin.split("~");
    	
    	String dbtablename = getDBName(uid,tablename);
    	String delete = "delete from " + dbtablename + " where TID = " + TID ;
    	String insert = "insert "+ dbtablename + " values(" ;
    	for(int i=0;i<mes.length;i++)
    	{
    		insert = insert + "'" + mes[i] + "',"; 
    	}
    	insert = insert + TID + ")";
    	
    	try
    	{
    		stmt.execute(delete);
    		stmt.execute(insert);
    		updateStati(uid);
        	return true;
    	}
    	catch(SQLException e)
		{
			e.printStackTrace();
			return false;
		}
    }
    
    /* 12/07 新增待测试
     * 涂神需求
     * 删除一个大事件
     * 传入用户ID，大事件ID
     * 返回true代表成功
     * 
     * 对统计数据产生影响
     */
    public boolean deleteBEvent(String uid,String EID)
    {
    	String delete = "delete from event" + uid + " where EID=" + EID;
    	String delete2 = "delete from assoc" + uid + " where EID=" + EID;
    	try
    	{
    		stmt.execute(delete);
    		stmt.execute(delete2);
    		updateStati(uid);
    		return true;
    	}
    	catch(SQLException e)
		{
			e.printStackTrace();
			return false;
		}
    }
    
    /* 12/07 新增待测试
     * 涂神需求 
     * eventBrief进阶版
     * 返回内容增加EID
     * 输入为用户ID
     * 输出为大事件名~大事件关联小事件个数~大事件ID^……
     */
    public String eventBriefN(String uid)
    {    	
    	ResultSet res ;
		String result = "" ;

		String temp = "select EName,count(*),a.EID from event" + uid + " e join assoc" + uid +" a where a.EID = e.EID group by EName";
		
		try{
			res = stmt.executeQuery(temp);
			while(res.next())
			{
				result = result + "^" + res.getString(1) + "~" + res.getString(2) + "~" + res.getString(3);
			}
			result = result.substring(1); //第一个^
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return result;
    }
    
    /* 12/07 新增待测试
     * 涂神需求
     * 输入用户ID，大事件ID
     * 返回大事件名~时间~关联小事件个数^小事件1全局ID~小事件1ID~小事件1所属表名~小事件1name^小事件2全局ID~小事件2ID~小事件2所属表名~小事件2name……
     * 其中涂神使用全局ID，显示小事件所属表名及小事件name，后续传小事件ID及所属表名给SQK
     */
    public String showBEvent(String uid,String EID)
    {
    	String result = "";
    	String getmes = "select EName,ETime,count(*) from event" + uid + " e join assoc" + uid +" a where a.EID = e.EID and a.EID = "+ EID;
    	String select = "select TID,tableid,uname from assoc" + uid + " a join usertable u where a.tableid = u.ID and a.EID = " + EID;

    	try
    	{
    		ResultSet res = stmt.executeQuery(getmes);
    		while(res.next())
    		{
    			result = res.getString(1) + "~" + res.getString(2) + "~" + res.getString(3);
    		}
    	}
    	catch(SQLException e)
    	{
    		e.printStackTrace();
    	}
    	
    	try
    	{
    		ResultSet res = stmt.executeQuery(select);
    		while(res.next())
    		{
    			String tid = res.getString(1);
    			String tableid = res.getString(2);
    			String uname = res.getString(3);
    			String thingname = getSEName(tableid,tid);
    			result = result + "^" + tableid + "_" + tid + "~" + tid + "~" + uname + "~" + thingname;
    		}
    	}
    	catch(SQLException e)
		{
			e.printStackTrace();
		}
    	
    	return result;
    }
    
    /* 12/07 新测试通过
     * sqk需求
     * 输入用户ID，表名，小事件ID
     * 返回该小事件每一列，包含小事件ID
     */
	public String editRetOldInf(String uid,String tablename,String TID)
	{
		String dbtablename = getDBName(uid,tablename);
		String temp = "select * from " + dbtablename + " where TID =" + TID;
		
		ResultSet res = null;
		String mes = "";
		
		try
		{
			res = stmt.executeQuery(temp);
			while(res.next())
			{
				for (int i=1;i<=co_count;i++)
				{
					mes = mes + "~" + res.getString(i);
				}
			}
			if(mes.length()>1)
			{
				mes = mes.substring(1);
			}
			return mes;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return mes;
		
	}
	
	/* 12/12 涂神整合测试通过
	 * 12/08 新增待测试
	 * 用户评价
	 * 输入为用户ID及评价字符串
	 * 返回一个布尔值表示是否成功
	 */
	public boolean comment(String uid,String com)
	{
		boolean suc = false;
		com = com.replace("~","','");
		com = "'" + com + "'" ;
		
		String insert = "insert comment values(" + uid + "," + com + ")";
		
		try
		{
			stmt.execute(insert);
			suc = true;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return suc;
	}
	
	/* 12/08 新测试通过
	 * 生成csv文件内容
	 * 传入值为用户ID及他个人的表名
	 * 传出值为字符串 格式为 ： 用户表名^csv文件内容
	 */
	public String download(String uid,String uname)
	{
		ResultSet res = null;
		String dbtablename = getDBName(uid,uname);
		
		String result = "";
		String detail = "desc " + dbtablename;
		String select = "select * from "+ dbtablename ;
		
		try
		{
			res = stmt.executeQuery(detail);
			while(res.next())
			{
					result = result + "," + res.getString(1);
			}
			result = result.substring(1);
			result = result.replace(",TID", "");
			res = stmt.executeQuery(select);
			while(res.next())
			{
				result = result + "\n" ;
				for(int i=1;i<co_count;i++)
				{
					result = result + "," + res.getString(i);
				}
			}
			result = result.replace("\n,", "\n");
			result = uname + "^" + result;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return result;
	}
	
	/* 12/12 新版本测试通过
	 * 更新：列数不够的数据将被补全后插入，不会直接丢失
	 * 
	 * 12/08 新测试通过
	 * 将上传的csv文件处理过后 添加进数据库已有表中
	 * 成功时返回该表表名，请自行调用函数使用表名和用户ID查看表的内容
	 * 12/08版本 注意：如果 表实际列数是3，多于3列的行将只保留前三列数据，少于3的则不保留数据
	 * 
	 * 失败返回"false"
	 */
	public String upload(String uid, String uname, File csv_file)
	{
		String dbtablename = getDBName(uid,uname);
		
		String str = "";
		try 
        {
            BufferedReader in_br = new BufferedReader(new FileReader(csv_file));
            
            while ((str = in_br.readLine()) != null) 
            {
                  String[] columns = str.split(",");
                  String insert = "";
                  if(columns.length<co_count-1)
                  {
                	  for(int x = columns.length ; x < co_count ; x++)
                	  {
                		  str = str + ", ";
                	  }
                	  columns = str.split(",");
                  }

               	  for(int i=0;i<co_count-1;i++)
                  {
                	  if("".equals(columns[i]))
                	  {
                		  columns[i] = " ";
                	  }
                	  insert = insert + "~" + columns[i];
                  }
                  insert = insert.substring(1);
                                  
                  insertSEvent(uid,uname,insert);
            }
            in_br.close();
        } 
        catch(IOException e) 
        {
            e.getStackTrace();
            return "false";
        }
		return uname;
	}
	
	
	/* 12/12 上传文件的进阶版 待测试
	 * 进阶：根据文件第一行确定表的列数
	 * 其余行数，列数不够的补全空格，超出的只取新表列数的列数，加入表中
	 * 传入用户id，期望的新表名（可能是文件名？），文件
	 * 返回新表名 或 false
	 * 请自行根据用户id及表名使用函数查看该表内容
	 */
	public String uploadNewTable(String uid,String newTableName,File myfile)
	{
		String str = "";
		try 
        {
			BufferedReader in_br = new BufferedReader(new FileReader(myfile));
            
            int l_count = 0;      //记录正在读的行数
            int lines = 0;        //记录新表的列数
            while ((str = in_br.readLine()) != null) 
            {
            	l_count++;
            	String[] columns = str.split(",");
            
            	//表头信息
            	if(l_count ==1) 
                {
                	lines = columns.length;
                	str = uid + "~" + newTableName + "~" + lines + "~" + str.replace(",", "~50~")+ "~50";
                	createUserTable(str);
                	continue;
                }
                
                //除去表头的其他行
                String insert = "";
                if(columns.length<lines)   //对于列数不全的数据 补全
                {
                	for(int x = columns.length + 1; x <= lines ; x++)
                	{
                		str = str + ", ";
                	}
                	columns = str.split(",");
                	System.out.println(str);
                }
                
                for(int i=0;i<lines;i++)   //列数过多的自动忽略多余的列数
                {
                	if("".equals(columns[i]))
	               	{
                		columns[i] = " ";
	               	}
	               	insert = insert + "~" + columns[i];
	            }
	            insert = insert.substring(1);
                                  
                insertSEvent(uid,newTableName,insert);
            }
            in_br.close();
        } 
        catch(IOException e) 
        {
            e.getStackTrace();
            return "false";
        }
		return newTableName;
	}
    
	/* private函数 请勿直接使用或修改！
	 * 检查提供的小事件唯一ID是否存在
	 */
	private boolean checkTableID(String teid ,String uid)
	{		
		ResultSet res = null;
		String[] mes = teid.split("_");//tableid _ eventid
		String table_exist = " usertable where UID=" + uid + " and ID= " + mes[0] ; 
		
		int exist = count(table_exist);
		if(exist==-1)
		{
			return false;
		}
		exist = count(" t"+mes[0] + " where TID=" + mes[1]) ;
		if(exist == -1)
		{
			return false;
		}
		return true;
	}
	
	/* private函数 请勿直接使用或修改！
	 * 下面函数用于转换 属于user的tablename 和 实际存于DB中的tablename
	 * 同时查询到该表的列数
	 * private 的 成员变量值 co_count 将被修改
	 * tablename则作为返回值 
	 */
	private String getDBName(String uid, String user_name)
	{
		ResultSet res = null;
		String temp = "select * from usertable where UID = " + uid + " and uname = '"  + user_name + "'";
		String db_name = "";
		
		try {
			res = stmt.executeQuery(temp);
			while(res.next())
			{
				tableID  = res.getInt(1);
				db_name  = res.getString(3);
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
	
	/* private函数 请勿直接使用或者修改！！！
	 * 12/07  待测试
	 * 获取时间 为八位 yyyyMMdd
	 */
	private boolean updateStati(String uid)
	{
		String date = getTime();
		String update_ck = " stati" + uid+ " where Time = '" + date + "'";
		try{
			String update = null;
			int exist = count(update_ck);
			if (exist==0)
			{
				update = "insert stati" + uid + " values('" + date + "' ,1)";
			}
			else
			{
				update = "update stati" + uid + " set Count = Count+1 where Time ='" + date  + "'";
			}
			stmt.execute(update);
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return true;
	}

	/* private函数 请勿直接使用或者修改！！！
	 * 12/07 测试通过
	 * 获取时间 为八位 yyyyMMdd
	 */
	private String getTime() 
	{
		String date = new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime());
		return date;
	}
	
	private String getSEName(String tableid,String tid)
	{
		String result = "";
		try {
			Statement st = connection.createStatement();
			
			ResultSet rest = null;
			String sel = "select * from t" + tableid + " where TID=" + tid;
			rest = st.executeQuery(sel);
			while(rest.next())
			{
				result = rest.getString(1);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return result;
	}
	
	
	/* 12/11 测试通过
	 * 依旧private函数 循环到自己都一脸茫然 请勿改动或直接调用
	 * 补全所有统计0数据项
	 */
	private boolean complete(String uid)
	{
		int[] mday = {0,31,28,31,30,31,30,31,31,30,31,30,31};
		String temp = "select min(Time) from stati" + uid ;
		ResultSet res = null;
		String min = "";
		String max = getTime();
		try
		{
			res = stmt.executeQuery(temp);
			while(res.next())
			{
				min = res.getString(1);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		int maxy = Integer.parseInt(max.substring(0,4));
		int miny = Integer.parseInt(min.substring(0,4));
		int maxm = Integer.parseInt(max.substring(4, 6));
		int minm = Integer.parseInt(min.substring(4, 6));
		int maxd = Integer.parseInt(max.substring(6));
		int mind = Integer.parseInt(min.substring(6));
		
		if(maxy==miny)//同年日期 补全
		{
			if(maxm==minm)//同月 只补全当月
			{
				for(int i = mind+1 ; i <= maxd ; i++)
				{
					String d = "";
					if(i<10)
					{
						d = min.substring(0,6) + "0" + i;
					}
					else{
						d = min.substring(0,6) + i;
					}
					dateInsert(uid,d);
				}
			}
			else//不同月 补全两个月的所有信息
			{
				for(int i = minm ; i <= maxm ; i++)
				{
					int days = mday[i];
					for(int j = 1 ; j <= days ; j++ )
					{
						String d = "";
						if(i<10 && j<10) 
						{
							d = min.substring(0, 4) + "0" + i + "0" + j; 
						}
						else if(i<10 && j>9)
						{
							d = min.substring(0, 4) + "0" + i + "" + j; 
						}
						else if(i>9 && j<10)
						{
							d = min.substring(0, 4) + "" + i + "0" + j; 
						}
						else
						{
							d = min.substring(0, 4) + "" + i + "" + j; 
						}
						
						dateInsert(uid,d);
					}
				}
			}
		}
		else//不同年份 补全两年所有内容 没什么好办法 心好累
		{
			for(int i = miny ; i <= maxy ;i++)
			{
				for(int j = 1 ; j < 12 ; j++ ) 
				{
					for(int k = 1; k <= mday[j] ;k++)
					{
						String d = "";
						if(j<10 && k<10) 
						{
							d = i + "0" + j + "0" + k; 
						}
						else if(j<10 && k>9)
						{
							d = i + "0" + j + "" + k; 
						}
						else if(i>9 && j<10)
						{
							d = i + "" + j + "0" + k; 
						}
						else
						{
							d = i + "" + j + "" + k; 
						}
						dateInsert(uid,d);
					}
				}
			}
		}
		
		//填充完毕开始删除
		String delete = "delete from stati" + uid + " where Time not between " +  min + " and " + max;
		try
		{
			stmt.execute(delete);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return true;
	}
	
	/* 12/11 新测试通过
	 * 测试一个日期的统计数据是否存在，不存在则添加并设置为0
	 * 存在就不管了……
	 * 还是private函数 不能直接调用 也请不要修改
	 */
	private boolean dateInsert(String uid, String tdate)
	{
		try {
			Statement stmt1 = connection.createStatement();

			String update_ck = " stati" + uid+ " where Time = '" + tdate + "'";
		
			String update = null;
			int exist = count(update_ck);
			if (exist==0)
			{
				update = "insert stati" + uid + " values('" + tdate + "' ,0)";
				stmt1.execute(update);
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return true;
	}
	
	/* 12/13 进阶版
	 * private函数 请勿改动或直接使用
	 * 搜索一个表内 某个关键字的所有信息
	 * 返回内容为表名~表独立ID~表第一列内容
	 */
	private String searchInTable(String uid, String tname,String key)
	{
		Set<String> things = new HashSet<String>();
		ResultSet res = null;
		String dbtablename = getDBName(uid,tname);
		int lines = co_count;
		
		String tableid = dbtablename.replace("t", "");
		
		String line_names = "";
		
		String find_lines = "desc " + dbtablename;
		
		String result = "";
		
		try{
			res = stmt.executeQuery(find_lines);
			
			while(res.next())
			{
				line_names += "~" + res.getString(1);
			}
			line_names = line_names.substring(1);
			
			String[] lnames = line_names.split("~");
			
			for(int i = 1 ; i < lines ; i++)
			{
				String small_se = "select * from " + dbtablename + " where " + lnames[i-1] + " like '%" + key + "%'";
				res = stmt.executeQuery(small_se);
				if(res.wasNull())
				{
					continue;
				}
				while(res.next())
				{
					String ttemp = "^" + tname + "~" + tableid + "_" + res.getString(lines) + "~" + res.getString(1);
					things.add(ttemp);
				}
			}
			if(things.isEmpty())
			{
				return "";
			}
			Iterator<String> it=things.iterator();
			result = "";
		    while(it.hasNext())
	       {
	           result = result + it.next();
	       }
		   result =result.substring(1);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return result;
	}
	
	private void template(String uid)
	{
		String outlays = uid + "~Outlays~4~Name~50~Date~50~Amount~50~Comment~50";
		String income = uid + "~Income~4~Name~50~Date~50~Amount~50~Comment~50";
		String meals = uid + "~Meals~4~Date~50~Amount~50~Place~50~Food~50";
		String books = uid + "~Books~5~Name~50~Author~50~Date~50~TimeLimit~50~Place~50";
		String friends = uid + "~Friends~3~Name~50~Mobile~50~Birthday~50";
		
		createUserTable(outlays);
		createUserTable(income);
		createUserTable(meals);
		createUserTable(books);
		createUserTable(friends);
		
		String insert1 = "Dinner~2016-12-01~89~had dinner with Sam";
		String insert2 = "2016-12-01~KFC~89~Chips";
		String insert3 = "Sam~13xxxxxxxxx~19960101";
		
		insertSEvent(uid,"Outlays",insert1);
		insertSEvent(uid,"Meals",insert2);
		insertSEvent(uid,"Friends",insert3);
		
		String[] ids1 = searchRequest(uid,"KFC").split("~");
		String[] ids2 = searchRequest(uid,"Chips").split("~");
		String[] ids3 = searchRequest(uid,"1996").split("~");
		
		String id1 = ids1[1];
		String id2 = ids2[1];
		String id3 = ids3[1];
		
		String bigEvent = uid + "~KFC~20161201~"+id1+"~"+id2+"~"+id3;
		System.out.println(bigEvent);
		
		submitEvent(bigEvent);
	}
}
