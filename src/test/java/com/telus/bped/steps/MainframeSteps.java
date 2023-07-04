package com.telus.bped.steps;


import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.test.ui.actions.Validate;
import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.testng.Assert;

import com.test.reporting.Reporting;
import com.test.utils.EncryptDecrypt;
import com.test.utils.Status;



public class MainframeSteps {

	public static JSONArray mainframeResult=null;
	JSONObject mainframeResultObject = new JSONObject();

	public static boolean flag=false;
	public static String startTime=null;
	public static String PSTDate=null;
	
	public void launchMainframeApplication(String command){
		
		//String command = "cmd /c \"robot C:\\Users\\himanshu.singh01\\Desktop\\Robot-Framework-Mainframe-3270-Library-master\\atest\\mainframe.robot\"";
        
		try {
            Process proc = Runtime.getRuntime().exec(command);
            
            BufferedReader stdInput = new BufferedReader(new 
            InputStreamReader(proc.getInputStream()));
            String s = null;
            
            if ((s = stdInput.readLine()) != null) {
            	Reporting.logReporter(Status.INFO," Application is running...."); 
            	flag=true;
            }else {
//            	Assert.fail("Mainframe execution is not started");
				Reporting.logReporter(Status.FAIL," Application is not running....");
				flag=false;
			}

            
          while ((s = stdInput.readLine()) != null) {
        	  Reporting.logReporter(Status.INFO, s);  
        	  
          }
       
		}catch (Exception e) {
			Assert.fail("Unable to launch session");
                e.printStackTrace();
            }
	}
	
	
	/**
	 * This method will create a Directory
	 * @param dirPath
	 * @param srcDir
	 */
	public void createMFDir(String dirPath,String srcDir) {
		
			File files = new File(dirPath);
			if (!files.exists()) {
	            if (files.mkdirs()) {
	                System.out.println("directories are created!");
	            } else {
	                System.out.println("Failed to create directories!");
	            }
	        }
	
		File source = new File(srcDir);
		
		File dest = new File(dirPath);
		try {
		    FileUtils.copyDirectory(source, dest);
		} catch (IOException e) {
		    e.printStackTrace();
		}
	}
	
	public void reportMF() {
		
//		String fileExtension = ".jpg";
//		String mainframeReportDir=System.getProperty("user.dir")+"\\MainframeReportScreenshot";
//		String mainframeReport=System.getProperty("user.dir")+fileExtension;
//		
//		File source = new File(mainframeReport);
//		File dest = new File(mainframeReportDir);
//		try {
//			FileUtils.cleanDirectory(dest); 
//		    FileUtils.copyDirectory(source, dest);
//		    System.out.println("Report Saved");
//		} catch (IOException e) {
//			System.out.println("Report Not Saved");
//		    e.printStackTrace();
//		}
	}
	
	
	/**
	 * This method will Delete a Directory
	 * @param dirPath
	 */
	
	public void deleteMFDir(String dirPath) {
		
		try {
			File createdDir = new File(dirPath);
			FileUtils.deleteDirectory(createdDir);
			createdDir.delete();
			System.out.println("directories are deleted");
		}catch (Exception e) {
			Assert.fail();
		}
		
	}
	
	public JSONArray getMainframeAppStatus()	{
        //To get data in JSON Array
	
	if (!flag) {
		Validate.assertTrue(false,"Mainframe Application Not Started",true,"Mainframe Application Working");
	}
	
	try {
		File xmlFile = new File("output.xml");
		byte[] b= Files.readAllBytes(xmlFile.toPath());
		String xml=new String (b);
		
		Document doc = Jsoup.parse(xml);
		Elements elements = doc.select("test");
		int testCount = elements.size();
		System.out.println(testCount);
		
		JSONObject obj = XML.toJSONObject(xml);
		
		
		
		if (testCount==1) {
			JSONObject testObj = obj.getJSONObject("robot").getJSONObject("suite").getJSONObject("test");
			String appName = testObj.getString("name");
            JSONObject appStatus = testObj.getJSONObject("status");
		    startTime = appStatus.getString("starttime");
            String overallStatus = appStatus.getString("status");
			if (overallStatus.equalsIgnoreCase("Fail")){
				Validate.assertTrue(false,"Mainframe Application Health Check Failed",true,"Mainframe Application Working");
			}
			executedAtDate();
            JSONObject testResultsObj = new JSONObject();
            testResultsObj.put("executedAt",PSTDate);
            testResultsObj.put("appStatus",overallStatus+"ED");
            testResultsObj.put("appName",appName);
            JSONArray jsonArraySingleRes = new JSONArray();
            jsonArraySingleRes.put(testResultsObj);
			mainframeResult=jsonArraySingleRes;
            
            return mainframeResult;
		}else {
			JSONArray entry=obj.getJSONObject("robot").getJSONObject("suite").getJSONArray("test");
			 JSONArray jsonArray = new JSONArray();
		        
		        //jsonObj will be pushed into jsonArray
				for (int i = 0; i < entry.length(); ++i) {
					
					JSONObject jsonObj = new JSONObject();
				    JSONObject jsonObj1 = entry.getJSONObject(i);
				    JSONObject status= jsonObj1.getJSONObject("status");;
				    startTime=status.get("starttime").toString();
				    
				    jsonObj.put("executedAt",PSTDate);
				    String executionStatus=status.get("status").toString();
					if (executionStatus.equalsIgnoreCase("Fail")){
						Validate.assertTrue(false,"Mainframe Application Health Check Failed",true,"Mainframe Application Working");

					}

					if (executionStatus.equals("PASS")) {
				    	String finalStatus=executionStatus+"ED";
				    	jsonObj.put("appStatus",finalStatus);
				    }else {
				    	String finalStatus=executionStatus+"ED";
				    	jsonObj.put("appStatus",finalStatus);
				    }
				    
				    jsonObj.put("appName",jsonObj1.get("name"));
				    jsonArray.put(jsonObj);
				}
				
				return mainframeResult=jsonArray;
	}
	}catch (Exception e) {
        e.printStackTrace();
        return null;
	}
    }
		
	public void executedAtDate() {
		
		try {	
	    	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
		    Date date = sdf.parse(startTime);
		    SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    Calendar cal = Calendar.getInstance();
	        cal.setTime(date);
	        cal.add(Calendar.HOUR, -12);
	        cal.add(Calendar.MINUTE, -30);
	        date = cal.getTime();
		    PSTDate=sdf2.format(date)+" (PST)";  
	    }catch (Exception e) {
	    	 e.printStackTrace();
	    }
	    
	}
	
	public static void main(String[] args) throws Exception {
		String str = "nbx33N1XSjfzTHJT8CYV0H1dPboFr6SnomfFhoFKTohJ0oMwUfqzBbZNLygltza51baq3kfD/N+BDmon+3ZPJMUkMOX7lscuBIjFIn5q15eiR9gH";
		System.out.println(EncryptDecrypt.generateDecryptData(str, "gh-key-string"));
	}

}

