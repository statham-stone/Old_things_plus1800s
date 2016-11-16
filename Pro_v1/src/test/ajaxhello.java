package test;

import db.Database;
import com.opensymphony.xwork2.ActionSupport;

public class ajaxhello extends ActionSupport {

	private static final long serialVersionUID = 7443363719737618408L;
	private String name;
	private String inch;
	private String result;

	@Override
	public String execute() throws Exception {

		Database db1 = new Database();
		String sql_result =db1.connect();

		
		// TODO Auto-generated method stub
		if ("张三".equals(name)) {
			result = "身份验证通过,身高为" + inch;
		} else
			result = "不是张三！";
		return SUCCESS;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getInch() {
		return inch;
	}

	public void setInch(String inch) {
		this.inch = inch;
	}

	public String getResult() {
		return result;
	}

}