package table_create;
public class table_create_java 
{
	public String new_table_information;

	public String execute()
	{	
		System.out.println("此处调用相关数据库函数");
		System.out.println(new_table_information);
		System.out.println("-------------------------------------------\n");		
		int create_status=1;
		if(create_status==1)
		{
			return "SUCCESS";
		}
		else 
		{
			return "FAILED";
		}
	}
	
	public String getNew_table_information(){
		return new_table_information;
	}

	public void setNew_table_information(String new_table_information) {
		this.new_table_information = new_table_information;
	}
}
