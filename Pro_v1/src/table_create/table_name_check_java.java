package table_create;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

public class table_name_check_java 
{
	public String table_name;
	public int user_id;
	public int column_number;
	
	public String execute()
	{
		ServletRequest request=ServletActionContext.getRequest();
		HttpServletRequest req =(HttpServletRequest) request;
		HttpSession sesssion=req.getSession();
		sesssion.setAttribute("table_name",table_name);
		sesssion.setAttribute("user_id",user_id);
		sesssion.setAttribute("column_number",column_number);	
		return "SUCCESS";
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
