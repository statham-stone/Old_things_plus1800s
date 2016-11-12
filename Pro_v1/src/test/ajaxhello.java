package test;

import com.opensymphony.xwork2.ActionSupport;

public class ajaxhello extends ActionSupport {

 private static final long serialVersionUID = 7443363719737618408L;
 private String name;
 private String inch;
 private String result;
 @Override
 public String execute() throws Exception {
  // TODO Auto-generated method stub
  if("张三".equals(name)) {
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