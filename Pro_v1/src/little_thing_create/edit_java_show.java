package little_thing_create;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import sun.swing.MenuItemLayoutHelper.ColumnAlignment;
import db.Database;

public class edit_java_show {
	String user_id;
	String table_name;
	String little_id;
	
	String old_infor;
	String column_names;
	String new_infor;
	
	public String execute()
	{//Done
		//create by the information
		
		Database db1 = new Database();
		String sql_result =db1.connect();
		
		
		System.out.print("999999999999999999999999999999999999999999999999999999");
		System.out.print(user_id);
		System.out.print(table_name);		
		System.out.print(little_id);
		
//		old_infor=db1.aaaaaaaaaaaaaaaaaaaaaaaaaa
		old_infor=db1.editRetOldInf(user_id,table_name,little_id);
		column_names=db1.statham_column(user_id, table_name);
	
		System.out.print(old_infor);		
		ServletRequest request=ServletActionContext.getRequest();
		HttpServletRequest req =(HttpServletRequest) request;
		HttpSession sesssion=req.getSession();
		sesssion.setAttribute(old_infor,old_infor);
		sesssion.setAttribute(column_names,column_names);		
		
		
		System.out.print("+++++++++x++++++++++++++++++++++++++++++++++++++++++\n");
		System.out.print("Old information");
		System.out.print(old_infor);
		System.out.print(new_infor);
		return "SUCCESS";
		
	}
	
	public String insert()
	{
		
		Database db1 = new Database();
		String sql_result =db1.connect();
		
		String thing_arr[]=new_infor.split("~");
		
		db1.deleteSEvent(thing_arr[0], thing_arr[1], thing_arr[2]);
		
		String ahh=thing_arr[3];
		
		for(int i=4;i<thing_arr.length;i++)
		{
			ahh=ahh+"~"+thing_arr[i];
		}
		
		System.out.print("00000000000000000000000");		
		System.out.print(ahh);

		if(db1.insertSEvent(thing_arr[0], thing_arr[1], ahh)==1)
		{
			return "SUCCESS";			
		}
		return "FAILED";

	}
	
	public String delete() 
	{
		
		Database db1 = new Database();
		String sql_result =db1.connect();
		String thing_arr[]=new_infor.split("~");

		if(	db1.deleteSEvent(thing_arr[0], thing_arr[1], thing_arr[2])==true)
		{
			return "SUCCESS";			
		}
		return "FAILED";
		
	}
	

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getTable_name() {
		return table_name;
	}

	public void setTable_name(String table_name) {
		this.table_name = table_name;
	}

	public String getLittle_id() {
		return little_id;
	}

	public void setLittle_id(String little_id) {
		this.little_id = little_id;
	}

	public String getOld_infor() {
		return old_infor;
	}

	public void setOld_infor(String old_infor) {
		this.old_infor = old_infor;
	}

	public String getColumn_names() {
		return column_names;
	}

	public void setColumn_names(String column_names) {
		this.column_names = column_names;
	}

	public String getNew_infor() {
		return new_infor;
	}

	public void setNew_infor(String new_infor) {
		this.new_infor = new_infor;
	}

}
