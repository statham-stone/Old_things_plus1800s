package ajax;
import db.Database;

import com.opensymphony.xwork2.ActionSupport;
/**
 * load the Brief information of all event of a user
 * PARAMETERS: 	uid
 * RESULTS:		a list containing the user's all tables
 * 		FORMAT:eventname1~count1^eventname2^count2
 * 
 * @author Ralph
 *
 */
public class EventBrief extends ActionSupport {

	private static final long serialVersionUID = 7443363719737618408L;
	private String uid;
	private String result;
	
	@Override
	public String execute() throws Exception {


		Database db1 = new Database();
		String sql_result =db1.connect();


		
		result="uid:"+uid+"~count1^eventname2~count2^eventname3~count3";
		
		result=db1.eventBriefN(uid);
		// TODO: set  result value to return to front end
		
		return SUCCESS;
	}

	public String getUid(){
		return uid;
	}
	public void setUid(String uid){
		this.uid=uid;
	}

	public String getResult() {
		return result;
	}

}