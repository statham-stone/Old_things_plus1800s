package ajax;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class FileUpload extends ActionSupport {

	private String tableName;
    private File file;
    private String fileFileName;
    private String fileFileContentType;
    private String result;
    
    
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
            FileInputStream inputStream = new FileInputStream(f);
            //TEST THE FILE HERE
            	//tips: code below should be changes for process other file type
            InputStreamReader iSR = new InputStreamReader(inputStream);
            BufferedReader bufReader = new BufferedReader(iSR);
            String tempString=null;
            System.out.println(tableName);
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