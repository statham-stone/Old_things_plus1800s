package little_thing_create;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

public class create_little_thing_detail_java {
	private String user_id;
	private String table_name;
	private String column_names;
	
	
	
	public String execute()
	{
		//调用函数完成create thing
		
		
		column_names="column1~column2~column3~column5";
		user_id="007";
		table_name="tableName1";
		ServletRequest request=ServletActionContext.getRequest();
		HttpServletRequest req =(HttpServletRequest) request;
		HttpSession sesssion=req.getSession();
		sesssion.setAttribute(column_names,column_names);
		sesssion.setAttribute(user_id,user_id);
		sesssion.setAttribute(table_name,table_name);
		int done_success=0;
		if(done_success==0)
		{
			return "SUCCESS";
		}
		else 
		{
			return "FAILED";
		}
		
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getTable_name() {
		return table_name;
	}
	public void setTable_name(String table_name) {
		this.table_name = table_name;
	}
	public String getColumn_names() {
		return column_names;
	}
	public void setColumn_names(String column_names) {
		this.column_names = column_names;
	}
}
