package ajax;
import db.Database;
import com.opensymphony.xwork2.ActionSupport;
/**
 * Login page's Register button will call this action through ajax
 * USAGE:
 * 		assign string "-1" to result means register failed
 * 		assign other values to result will be recognized as uid
 * 			and front end will save this uid in cookies
 * 
 * @author Ralph
 *
 */
public class UserRegister extends ActionSupport {

	private static final long serialVersionUID = 7443363719737618408L;
	private String username;
	private String password;
	private String result;

	@Override
	public String execute() throws Exception {

		Database db1 = new Database();
		String sql_result =db1.connect();
		
		
		result=Integer.toString(db1.signUp(username, password));
//		if ("lolol".equals(username)) {
//			result = "1";
//		} else
//			result = "-1";
//		// TODO: set  result value to return to front end
		
		return SUCCESS;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getResult() {
		return result;
	}

}