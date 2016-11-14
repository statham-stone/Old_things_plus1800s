package ajax;

import com.opensymphony.xwork2.ActionSupport;
/**
 * return a content of a given table
 * PARAMETERS: 	uid,tablename
 * RESULTS:		a list containing this table's information
 * 		FORMAT: id1~name1^id2~name2
 * USED BY: new_event.html
 * @author Ralph
 *
 */
public class TableContent extends ActionSupport {

	private static final long serialVersionUID = 7443363719737618408L;
	private String uid;
	private String tablename;
	private String result;
	
	@Override
	public String execute() throws Exception {
		result="uid:"+uid+"~"+tablename+"^id2~name2^id3~name3";
		// TODO: set  result value to return to front end
		
		return SUCCESS;
	}

	public String getUid(){
		return uid;
	}
	public void setUid(String uid){
		this.uid=uid;
	}

	public String getTablename(){
		return tablename;
	}
	public void setTablename(String tablename){
		this.tablename=tablename;
	}
	
	public String getResult() {
		return result;
	}

}