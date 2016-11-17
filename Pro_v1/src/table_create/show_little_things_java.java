package table_create;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import db.Database;
import freemarker.template.utility.Execute;

public class show_little_things_java {
	
	private String user_id;
	private String table_name;
	private String infor;
	private String infor2;

	public String execute()
	{	
		
		Database db1 = new Database();
		String sql_result =db1.connect();
		
		infor=db1.findSEvent(Integer.parseInt(user_id), table_name);
		infor2=db1.statham_column(Integer.parseInt(user_id), table_name);
		
	//	infor="1~2~3~4~5~6";
		ServletRequest request=ServletActionContext.getRequest();
		HttpServletRequest req =(HttpServletRequest) request;
		HttpSession sesssion=req.getSession();
		sesssion.setAttribute("infor",infor);
		sesssion.setAttribute("infor2",infor2);		
		
		
		
		return "SUCCESS";
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
	public String getInfor() {
		return infor;
	}
	public void setInfor(String infor) {
		this.infor = infor;
	}
	public String getInfor2() {
		return infor2;
	}
	public void setInfor2(String infor2) {
		this.infor2 = infor2;
	}
}
