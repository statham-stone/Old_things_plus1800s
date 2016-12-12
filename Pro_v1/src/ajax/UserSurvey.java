package ajax;

import db.Database;
import com.opensymphony.xwork2.ActionSupport;
/**
 * send user survey information string to database
 * USAGE:
 * 		already formated.
 * 
 * @author Ralph
 *
 */
public class UserSurvey extends ActionSupport {

	private static final long serialVersionUID = 7443363719737618408L;
	private String uid;
	private String survey;
	private String result;

	@Override
	public String execute() throws Exception {
		
		Database db1 = new Database();
		String sql_result =db1.connect();
		
		db1.comment(uid, survey);
		
		result="Information Received, 感谢您的参与！";
		return SUCCESS;
	}


	public String getUid(){
		return uid;
	}
	public void setUid(String uid){
		this.uid=uid;
	}
	
	public String getSurvey(){
		return survey;
	}
	public void setSurvey(String survey){
		this.survey=survey;
	}
	
	public String getResult() {
		return result;
	}

}