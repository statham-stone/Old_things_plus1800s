package db;
import java.util.ArrayList;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
@SuppressWarnings({ "unused", "serial" })
public class Response extends ActionSupport{
	private String Prompt;
	
	private String Username;
	private String Password;
	private int ID = 0;
	
	private String temp;
	
	private String table_name="test wy";     //用户给新建表起的名字
	
	private ArrayList<Table> request = new ArrayList<Table>();

	public String SIGNUP()
	{
		Database db1 = new Database();
		db1.connect();
		
		temp = "user where name='" + Username + "'";
		int exist = db1.count(temp);
		if(exist==1)
		{
			Prompt = "Already exists a username:" + Username ;
			return "fail";
		}
		
		temp = "user";
		ID = db1.count(temp) + 1;
		String temp = "insert user values(" + ID + "," + "'" + Username + "','" + Password + "'" + ")";
		db1.executeSql(temp);
		
		db1.newuser(ID);
		Prompt = "Welcome! " + Username;
		return SUCCESS;
	}
	
	public String LOGIN()
	{
		Database db1 = new Database();
		db1.connect();
		temp= "user where name='" + Username + "' and password='" + Password + "'";
		int exist = db1.count(temp);
		
		if (exist==1)
		{
			ID = db1.findID(Username);
			Prompt = "Welcome Back! " + Username;
			return SUCCESS;
		}
		else
		{
			Prompt = "Wrong username or password! ";
			return "fail";
		}
	}
	
	/*public String newtable()
	{
		Table newre = new Table() ;
		newre.cname="spending";
		newre.ctype="Date";
		request.add(newre);
		
		Database db1 = new Database();
		
		Prompt = "You have a new table "+ table_name +" now!";
		return SUCCESS;
	}*/

	
	
	public int getID() {
		return ID;
	}
	public void setID(int ID) {
		this.ID = ID;
	}
	public String getPrompt() {
		return Prompt;
	}
	public String getUsername() {
		return Username;
	}
	public void setUsername(String Username) {
		this.Username = Username;
	}
	public void setPassword(String Password) {
		this.Password = Password;
	}	
	
	
}
