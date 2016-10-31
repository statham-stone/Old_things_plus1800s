package little_thing_create;

public class create_little_thing_java {
	private String information;
	
	public String execute()
	{
		//insert by the information
		System.out.print("little thing information details:"+information);
	    
		int done_success=0;
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
