package ajax;

import com.opensymphony.xwork2.ActionSupport;
/**
 * fetch information from new_event page and submit the event into database
 * PARAMETERS: 	uid, date, comment, thingsarray
 * RESULTS:		a string including the result of the insertion
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
		System.out.println(uid);
		System.out.println(date);
		System.out.println(comment);
		for (int i=0;i<thingsarray.length;i++)
			System.out.println(thingsarray[i]);
		
		result="success inserted:"+uid+date+comment+thingsarray.length;
		// TODO: set  result value to return to front end
		
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