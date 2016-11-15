package little_thing_create;

import db.Database;

public class create_little_thing_java {
	private String information;
	
	public String execute()
	{//Done
		//create by the information
		
		Database db1 = new Database();
		String sql_result =db1.connect();
		
		System.out.print("+++++++++++++++++++++++++++++++++++++++++++++++++++\n");
		System.out.print("Last create information");
		System.out.print(information);
		System.out.print("+++++++++++++++++++++++++++++++++++++++++++++++++++\n");

		String string_arr[]=information.split("~");
		String user_id=string_arr[0];
		String table_name=string_arr[1];
		String little_infor=string_arr[2];
		
		for(int i=3;i<string_arr.length;i++)
		{
			little_infor=little_infor+"~"+string_arr[i];
		}
		
		int done_success=db1.insertSEvent(Integer.parseInt(user_id), table_name, little_infor);
		
	
		if(done_success==0)
		{
			return "SUCCESS";
		}
		else 
		{
			return "FAILED";
		}
		
	}

	public String getInformation() {
		return information;
	}

	public void setInformation(String information) {
		this.information = information;
	}

}
