package ajax;

import db.Database;
import com.opensymphony.xwork2.ActionSupport;
/**
 * load the Brief information of all tables of a user
 * PARAMETERS: 	uid
 * RESULTS:		a list containing the user's all tables
 * USED BY: index.jsp,  new_event.html
 * @author Ralph
 *
 */
public class TableBrief extends ActionSupport {

	private static final long serialVersionUID = 7443363719737618408L;
	private String uid;
	private String result;
	
	@Override
	public String execute() throws Exception {

		Database db1 = new Database();
		String sql_result =db1.connect();

		
		result="uid:"+uid+"~tablename2~tablename3";
		// TODO: set  result value to return to front end
		
		result=db1.tableBrief(Integer.parseInt(uid));
		
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