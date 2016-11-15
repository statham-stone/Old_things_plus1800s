package ajax;

import com.opensymphony.xwork2.ActionSupport;
/**
 * return search results of certain keyword and uid
 * PARAMETERS: 	uid, keyword
 * RESULTS:		a list containing search result
 * 		FORMAT: internalid1~name1^internslid2~name2
 * USED BY: new_event.html search.html
 * @author Ralph
 *
 */
public class SearchRequest extends ActionSupport {

	private static final long serialVersionUID = 7443363719737618408L;
	private String uid;
	private String keyword;
	private String result;
	
	@Override
	public String execute() throws Exception {
		result="uid:"+uid+"~"+keyword+"^id2~name2^id3~name3";
		// TODO: set  result value to return to front end
		
		return SUCCESS;
	}

	public String getUid(){
		return uid;
	}
	public void setUid(String uid){
		this.uid=uid;
	}

	public String getKeyword(){
		return keyword;
	}
	public void setKeyword(String keyword){
		this.keyword=keyword;
	}
	
	public String getResult() {
		return result;
	}

}