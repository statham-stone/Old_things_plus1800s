package table_create;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import db.Database;

public class table_name_check_java 
{
	public String table_name;
	public int user_id;
	public int column_number;

	

	public String execute() throws UnsupportedEncodingException
	{
		
		
		Database db1 = new Database();
		String sql_result =db1.connect();
		
		
		ServletRequest request=ServletActionContext.getRequest();
		HttpServletRequest req =(HttpServletRequest) request;
		HttpSession sesssion=req.getSession();
		
//		user_id=1926;//only for debugging
		
		
		System.out.print("\n====================================================\n");
		System.out.print("You got the table name and the user_id and column number:\n");
		

		
		table_name=URLDecoder.decode(table_name,"UTF-8");
		
		System.out.print(table_name);
		
		
		
		
		
		System.out.print("=======bingo \n");
		
		System.out.print("\n"+user_id);
		System.out.print("\n"+column_number);		
		System.out.print("\n==================DDUCK==================================\n");	
		
		
		
		sesssion.setAttribute("table_name",table_name);
		sesssion.setAttribute("user_id",user_id);
		sesssion.setAttribute("column_number",column_number);	
		String test1=user_id+"~"+table_name;
		
		int check_status=db1.checkTableName(test1);
	//	Database check_db=new Database();
	//	check_db.connect();
	//	check_status=check_db.checkTableName(test);
		
		System.out.print("==========================================\n");
		System.out.println(test1);
		System.out.print("==========================================\n");		
		if(check_status==0)
		{
			return "SUCCESS";			
		}
		else
		{
			return "FAILED";
		}

	}
	public String getTable_name() {
		return table_name;
	}
	public void setTable_name(String table_name) {
		this.table_name = table_name;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getColumn_number() {
		return column_number;
	}
	public void setColumn_number(int column_number) {
		this.column_number = column_number;
	}
}
