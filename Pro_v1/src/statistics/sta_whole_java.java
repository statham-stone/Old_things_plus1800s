package statistics;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import db.Database;

public class sta_whole_java
{
	private String user_id;
	private String result;
	private String result2;
	private String table_list;
	private String number_list;
	public String execute() throws Exception {


		Database db1 = new Database();
		String sql_result =db1.connect();

		result="[[1, 2], [2, 11], [3, 18], [4, 16], [5, 19], [6, 17], [7, 15], [8, 16], [9, 20], [10, 16], [11, 18]]";
		result2="[81,82,83,84,85,86,87,88,89,810,811]";
		table_list=db1.tableBrief( (user_id));
		
		
	
		number_list=db1.Pie(user_id);
		
		
		
		ServletRequest request=ServletActionContext.getRequest();
		HttpServletRequest req =(HttpServletRequest) request;
		HttpSession sesssion=req.getSession();
		sesssion.setAttribute("result",result);
		sesssion.setAttribute("result2",result2);
		sesssion.setAttribute("table_list",table_list);

		sesssion.setAttribute("number_list",number_list);
		//	result=db1.showBEvent(sql_result, EID)
		//result=db1.eventBriefN(user_id);
		
		return "SUCCESS";
	}
	
	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
	
	public String getTable_list() {
		return table_list;
	}

	public void setTable_list(String table_list) {
		this.table_list = table_list;
	}
	
	public String getNumber_list() {
		return number_list;
	}

	public void setNumber_list(String number_list) {
		this.number_list = number_list;
	}
	
	public String getResult2() {
		return result2;
	}

	public void setResult2(String result2) {
		this.result = result2;
	}
}







