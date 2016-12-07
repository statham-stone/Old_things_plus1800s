package ajax;
import db.Database;

import com.opensymphony.xwork2.ActionSupport;
/**
 * load detailed info of certain event of certain user
 * PARAMETERS: 	uid,eid
 * RESULTS:		all info for this event
 * FORMAT:	EventName~Date~Count~
 * 			^GlobalId1~id1~Tname1~name1
 * 			^.....
 * 
 * @author Ralph
 *
 */
public class EventContent extends ActionSupport {

	private static final long serialVersionUID = 7443363719737618408L;
	private String uid;
	private String eid;
	private String result;
	
	@Override
	public String execute() throws Exception {


		Database db1 = new Database();
		String sql_result =db1.connect();


		
		result="EventName~Date~Count~^Gid1~id1~tname1~name1^Gid2~id2~tname2~name2";
		
		//result=db1.eventBrief(Integer.parseInt(uid));
		// TODO: set  result value to return to front end
		
		return SUCCESS;
	}

	public String getUid(){
		return uid;
	}
	public void setUid(String uid){
		this.uid=uid;
	}

	public String getEid(){
		return eid;
	}
	public void setEid(String eid){
		this.eid=eid;
	}
	
	public String getResult() {
		return result;
	}

}