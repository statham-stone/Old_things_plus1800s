package statistics;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import db.Database;

public class sta_whole_java
{
	private String user_id;
	private String days;
	private String result;
	private String result2;
	private String table_list;
	private String number_list;
	public String execute() throws Exception {


		Database db1 = new Database();
		String sql_result =db1.connect();
		System.out.print("kkkkkkkkkkkkkkk");
		System.out.print(user_id);
		result="[[1,12],[2, 11], [3, 18], [4, 16], [5, 19], [6, 17]]";
		result2="[81,82,83,84,85,86,87,88,89,810,811]";
		table_list=db1.tableBrief( (user_id));
		
		String test_string1=db1.Bar(user_id);
		System.out.print(test_string1);
		String[] test_arr1=test_string1.split("~");
		
		int day_number=test_arr1.length/2;
		
		result="[["+Integer.toString(1)+","+test_arr1[1]+"]";
		result2="["+test_arr1[0];
		for(int i=2;i<test_arr1.length;i=i+2)
		{
			result=result+",["+Integer.toString(i/2+1)+","+test_arr1[i+1]+"]";
			result2=result2+","+test_arr1[i];
		}
		
		result=result+"]";
		result2=result2+"]";
		
		System.out.print("kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk");
		System.out.print(result2);
		System.out.print("          ");
		System.out.print(result);
		
		System.out.print(test_string1);		
	
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
	
	public String execute2() throws Exception {


		Database db1 = new Database();
		String sql_result =db1.connect();

		result="[[1,12],[2, 11], [3, 18], [4, 16], [5, 19], [6, 17]]";
		result2="[81,82,83,84,85,86,87,88,89,810,811]";
		table_list=db1.tableBrief( (user_id));
		
		
		String test_string1=db1.Bar(user_id,days);
		System.out.print(test_string1);
		String[] test_arr1=test_string1.split("~");
		
		int day_number=test_arr1.length/2;
		
		result="[["+Integer.toString(1)+","+test_arr1[1]+"]";
		result2="["+test_arr1[0];
		for(int i=2;i<test_arr1.length;i=i+2)
		{
			result=result+",["+Integer.toString(i/2+1)+","+test_arr1[i+1]+"]";
			result2=result2+","+test_arr1[i];
		}
		result=result+"]";
		result2=result2+"]";
		
		System.out.print("jjjjjjjjjjjjjjjjjjjjjjjjjj");
		System.out.print(result2);
		System.out.print("          ");
		System.out.print(result);
		
		System.out.print(test_string1);		
	
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
	
	public String getDays() {
		return days;
	}

	public void setResult2(String result2) {
		this.result = result2;
	}
	
	public void setDays(String days) {
		this.days = days;
	}
}








