package db;

public class DatabaseTest {
	
	public static void main(String[] args) {
		
		String temp = null;
		String result = null;
		
		int intr = 0;
		
		// TODO Auto-generated method stub
		Database db1 = new Database();
		result = db1.connect();
		
		System.out.println(result);
		
		/* 
		
		//不存在的用户名和密码登录
		intr = db1.checkUser("wangye", "1234");
		System.out.println("test1" + intr);
		//有效注册
		intr = db1.signUp("wangye", "1234");
		System.out.println("test2" + intr);
		//无效注册
		intr = db1.signUp("wangye", "5678");
		System.out.println("test3" + intr);
		//有效登陆
		intr = db1.checkUser("wangye", "1234");
		System.out.println("test4" + intr);
		//错误密码登陆
		intr = db1.checkUser("wangye", "5678");
		System.out.println("test5" + intr);
		intr = db1.signUp("sqk", "1234");
		System.out.println("test6" + intr);
		
		//新增用户表
		intr = db1.createUserTable("2~Money~3~Name~10~time~12~product~20");
		System.out.println("test7" + intr);
		//尝试添加同名表
		intr = db1.checkTableName("2~Money");
		System.out.println("test8" + intr);
		//尝试添加与其他用户同名的表
		intr = db1.checkTableName("1~Money");
		System.out.println("test9" + intr);
		//找到一个用户的所有表
		result= db1.findUserTable(2);
		System.out.println("test10" +result);
		//查看一个表的具体信息
		result= db1.findTableColumn(2, "Money");
		System.out.println("test11" +result);
		//在小事件表中加入一条记录
		intr = db1.insertSEvent(2, "Money", "dinner~2016~lunch");
		System.out.println("test12 " + intr);
		intr = db1.insertSEvent(2, "Money", "snacks~2016~6yuan");
		System.out.println("test13 " + intr);
		//查看本表内所有信息
		result = db1.findSEvent(2,"Money");
		System.out.println("test14 " + result);
		//查看本表最新五条
		result = db1.findSEventN5(2,"Money");
		System.out.println("test15 " + result);
		//新增用户表
		intr = db1.createUserTable("2~Eat~3~Name~10~time~12~place~20");
		System.out.println("addition7" + intr);
		//尝试查看用户所有表
		result = db1.tableBrief(2);
		System.out.println("test16 " + result);
		//查看小事件表的内容 包括了小事件唯一id
		result = db1.tableContent("2~Money");
		System.out.println("test17 " + result);
		//尝试添加大事件 输入为用户ID~大事件EName~ETime~小事件全局ID~小事件全局ID
		intr = db1.submitEvent("2~testagain~20161115~5_1~5_2");
		System.out.println("test18 " + intr);
		//尝试查看用户所有大事件
		result = db1.eventBrief(2);
		System.out.println("test19 " + result);
		 */
		
		result = db1.searchRequest(2,"dinner");
		System.out.println("test20 " + result);
	
		
		
		result= db1.findTableColumn(2, "Money");
		System.out.println("test11" +result);
		
		
		System.out.println(db1.close());
		
	}

}
