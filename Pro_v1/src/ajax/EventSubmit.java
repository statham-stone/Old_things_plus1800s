package ajax;

import java.awt.print.Printable;

import org.apache.jasper.tagplugins.jstl.core.Out;

import db.Database;

import com.opensymphony.xwork2.ActionSupport;
/**
 * fetch information from new_event page and submit the event into database
 * PARAMETERS: 	uid, date, comment, thingsarray
 * RESULTS:		a string including the result of the insertion
 * 				"success" or other error information
 * USED BY: new_event.html
 * @author Ralph
 *
 */
public class EventSubmit extends ActionSupport {

	private static final long serialVersionUID = 7443363719737618408L;
	private String uid;
	private String date;
	private String comment;
	private String[] thingsarray;
	
	private String result;
	
	@Override
	public String execute() throws Exception {



		Database db1 = new Database();
		String sql_result =db1.connect();


		
		System.out.println(uid);
		System.out.println(date);
		System.out.println(comment);
		for (int i=0;i<thingsarray.length;i++)
			System.out.println(thingsarray[i]);
		
		String bigString=uid+"~"+comment+"~"+date;
		
		for (int i=0;i<thingsarray.length;i++)
		{
			bigString=bigString+"~"+thingsarray[i];			
		}
		System.out.print("--=-=--=-=-=-=-=-=-\n");
		System.out.print(bigString);

		
		result="failed";
		if(Integer.toString(db1.submitEvent(bigString))!="-1")
		{
			result="success";			
		}
		
		//result="fallll!";
		// TODO: set  result value to return to front end
		
		//	 * 输入为用户ID~大事件EName~ETime~小事件全局ID~小事件全局ID
		
		
		return SUCCESS;
	}

	public String getUid(){
		return uid;
	}
	public void setUid(String uid){
		this.uid=uid;
	}

	public String getDate(){
		return date;
	}
	public void setDate(String date)
	{
		this.date=date;
	}
	
	public String getComment(){
		return comment;
	}
	public void setComment(String comment){
		this.comment=comment;
	}

	public String[] getThingsarray(){
		return thingsarray;
	}
	public void setThingsarray(String []thingsarray){
		this.thingsarray=thingsarray;
	}
	
	
	public String getResult() {
		return result;
	}

}