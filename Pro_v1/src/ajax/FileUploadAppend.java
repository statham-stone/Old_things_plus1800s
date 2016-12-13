package ajax;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import db.Database;

@SuppressWarnings("serial")
public class FileUploadAppend extends ActionSupport {

	private String tableName;
    private File file;
    private String fileFileName;
    private String fileFileContentType;
    private String result;
    private String uid;
    
    
    public String getUid(){
    	return uid;
    }
    public void setUid(String uid){
    	this.uid=uid;
    }
    
    public String getTableName(){
    	return tableName;
    }
    public void setTableName(String tableName){
    	this.tableName=tableName;
    }
    
    public String getResult() {
        return result;
    }
    public void setResult(String result) {
        this.result = result;
    }

    public File getFile() {
        return file;
    }
    public void setFile(File file) {
        this.file = file;
    }

    public String getFileFileName() {
        return fileFileName;
    }
    public void setFileFileName(String fileFileName) {
        this.fileFileName = fileFileName;
    }

    public String getFileFileContentType() {
        return fileFileContentType;
    }
    public void setFileFileContentType(String fileFileContentType) {
        this.fileFileContentType = fileFileContentType;
    }

    @SuppressWarnings("deprecation")
    @Override
    public String execute() throws Exception {
        result = tableName+":successful uploaded";

        try {
            File f = this.getFile();
            //check file type here
            if(!this.getFileFileName().endsWith(".csv")){
                result= tableName+"error file extension";
                return SUCCESS;
            }
    		Database db1 = new Database();
    		String sql_result =db1.connect();
    		db1.upload(uid, tableName, f);
            FileInputStream inputStream = new FileInputStream(f);
            	//tips: code below should be changes for process other file type
            InputStreamReader iSR = new InputStreamReader(inputStream);
            BufferedReader bufReader = new BufferedReader(iSR);
            String tempString=null;
            System.out.println(tableName);
            System.out.println(uid);
            while ((tempString = bufReader.readLine()) != null) {
                System.out.println(tempString);
            }
            bufReader.close();
            iSR.close();
            inputStream.close();
            
            //File Test ENDED 
        } catch (Exception e) {
            e.printStackTrace();
            result = tableName+ "file upload failed";
        }
        return SUCCESS;
    }

}