package little_thing_create;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import db.Database;

public class choose_table_java {
	
		private String user_id;
		private String table_list;
		public String execute()
		{	//Done
			
			Database db1 = new Database();
			String sql_result =db1.connect();
			
			this.table_list="3~table_success_1~table2333333~table3";
			this.table_list=db1.findUserTable(Integer.parseInt(user_id));
			//table_listString=findTable(user_id)
	
			int wtf=0;
			
			System.out.print("\n------------------------------\n");
			System.out.print(table_list);
			System.out.print("\n------------------------------\n");			
			System.out.print(user_id);
			
			
		
			ServletRequest request=ServletActionContext.getRequest();
			HttpServletRequest req =(HttpServletRequest) request;
			HttpSession sesssion=req.getSession();
			sesssion.setAttribute("table_list",table_list);
			
					
			if(wtf==0)
			{
				return "SUCCESS";
			}
			else 
			{
				return "FAILED";
			}
		}
		
		public String getUser_id(){
			return user_id;
		}

		public void setUser_id(String user_id) {
			this.user_id=user_id;
		}

		public String getTable_list() {
			return table_list;
		}

		public void setTable_list(String table_list) {
			this.table_list = table_list;
		}
	}
