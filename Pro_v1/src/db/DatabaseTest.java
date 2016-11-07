package db;

public class DatabaseTest {
	
	public static void main(String[] args) {
		
		String temp = null;
		String result = null;
		
		// TODO Auto-generated method stub
		Database db1 = new Database();
		result = db1.connect();
		
		System.out.println(result);
		
		int count = db1.checkUser("sqk","123");
		System.out.println(count);
		
		count = db1.signUp("wangye","1234");
		System.out.println(count);
		
		int ID = db1.checkUser("wangye","1234");
		System.out.println(ID);
		
		System.out.println(db1.checkTableName(ID+"~MyEvent"));
		
		System.out.println(db1.checkTableName(ID+"~Money"));
		
		db1.createUserTable(ID + "~Money~2~time~12~product~20");
		
		String mes = db1.findUserTable(ID);
		System.out.println(mes);
		
		temp = "Money";
		mes = db1.findTableColumn(ID,temp);
		System.out.println(mes);
		
		System.out.println(db1.close());
		
	}

}
