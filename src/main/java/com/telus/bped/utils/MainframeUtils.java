package com.telus.bped.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.mockito.internal.matchers.Contains;
import org.testng.Assert;

import com.test.reporting.Reporting;
import com.test.ui.actions.Validate;
import com.test.utils.Status;
import com.test.utils.SystemProperties;

public class MainframeUtils {

	public static JSONArray mainframeResult = null;
	JSONObject mainframeResultObject = new JSONObject();

	public static boolean flag = false;
	public static String startTime = null;
	public static String PSTDate = null;

	/**
	 * 
	 * @param command
	 */
	public void launchMainframeApplication(String command) {

		try {
			Process proc = Runtime.getRuntime().exec(command);

			BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));
			String s = null;

			if ((s = stdInput.readLine()) != null) {
				Reporting.logReporter(Status.INFO, " Application is running....");
				flag = true;
			} else {
				Reporting.logReporter(Status.FAIL, " Application is not running....");
				flag = false;
			}

			while ((s = stdInput.readLine()) != null && !s.contains("Output:")) {
				Reporting.logReporter(Status.INFO, s);
			}
		} catch (Exception e) {
			Assert.fail("Unable to launch session");
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param EnvVariables
	 * @param robotFilePath
	 */
	public void launchMainframeApplication(HashMap<String, String> EnvVariables, String robotFilePath, String reportFilePath, String reportFileArtifectPath,String logFilePath,String logFileArtifectPath) {

		String command = generateCommand(EnvVariables, robotFilePath,reportFilePath, reportFileArtifectPath,logFilePath, logFileArtifectPath);

		try {
			Process proc = Runtime.getRuntime().exec(command);

			BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));
			String s = null;

			if ((s = stdInput.readLine()) != null) {
				Reporting.logReporter(Status.INFO, " Application is running....");
				flag = true;
			} else {
				Reporting.logReporter(Status.FAIL, " Application is not running....");
				flag = false;
			}

			while ((s = stdInput.readLine()) != null) {
				Reporting.logReporter(Status.INFO, s);
			}
			
			
		} catch (Exception e) {
			Assert.fail("Unable to launch session");
			e.printStackTrace();
		}
	}

	public String generateCommand(HashMap<String, String> envVariables, String robotFilePath, String reportFilePath, String reportFileArtifectPath,String logFilePath, String logFileArtifectPath) {
		String cmd = "";
		if (envVariables.size() > 0) {
			String envvariables = "";

			for (String a : envVariables.keySet()) {
				envvariables = envvariables + "--variable " + a + ":" + envVariables.get(a) + " ";
			}
			cmd = "cmd /c \"python -m robot --Log "+logFilePath+" --Report "+reportFilePath+" " + envvariables + " " + robotFilePath;
		} else {
			cmd = "cmd /c \"python -m robot --Log "+logFileArtifectPath+" --Report "+reportFileArtifectPath+" " + robotFilePath;
		}

		return cmd;
	}

	/**
	 * This method will create a Directory
	 * 
	 * @param dirPath
	 * @param srcDir
	 */
	public void createMFDir(String dirPath, String srcDir) {

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

	

	/**
	 * This method will Delete a Directory
	 * 
	 * @param dirPath
	 */

	public void deleteMFDir(String dirPath) {

		try {
			File createdDir = new File(dirPath);
			FileUtils.deleteDirectory(createdDir);
			createdDir.delete();
			System.out.println("directories are deleted");
		} catch (Exception e) {
			Assert.fail();
		}

	}

	public JSONArray getMainframeAppStatus() {
		// To get data in JSON Array

		if (!flag) {
			Validate.assertTrue(false, "Mainframe Application Not Started", true, "Mainframe Application Working");
		}

		try {
			File xmlFile = new File("output.xml");
			byte[] b = Files.readAllBytes(xmlFile.toPath());
			String xml = new String(b);

			Document doc = Jsoup.parse(xml);
			Elements elements = doc.select("test");
			int testCount = elements.size();
			System.out.println(testCount);

			JSONObject obj = XML.toJSONObject(xml);

			if (testCount == 1) {
				JSONObject testObj = obj.getJSONObject("robot").getJSONObject("suite").getJSONObject("test");
				String appName = testObj.getString("name");
				JSONObject appStatus = testObj.getJSONObject("status");
				startTime = appStatus.getString("starttime");
				String overallStatus = appStatus.getString("status");
				if (overallStatus.equalsIgnoreCase("Fail")) {
					Validate.assertTrue(false, "Mainframe Application Health Check Failed", true,
							"Mainframe Application Working");
				}
				executedAtDate();
				JSONObject testResultsObj = new JSONObject();
				testResultsObj.put("executedAt", PSTDate);
				testResultsObj.put("appStatus", overallStatus + "ED");
				testResultsObj.put("appName", appName);
				JSONArray jsonArraySingleRes = new JSONArray();
				jsonArraySingleRes.put(testResultsObj);
				mainframeResult = jsonArraySingleRes;

				return mainframeResult;
			} else {
				JSONArray entry = obj.getJSONObject("robot").getJSONObject("suite").getJSONArray("test");
				JSONArray jsonArray = new JSONArray();

				// jsonObj will be pushed into jsonArray
				for (int i = 0; i < entry.length(); ++i) {

					JSONObject jsonObj = new JSONObject();
					JSONObject jsonObj1 = entry.getJSONObject(i);
					JSONObject status = jsonObj1.getJSONObject("status");
					;
					startTime = status.get("starttime").toString();

					jsonObj.put("executedAt", PSTDate);
					String executionStatus = status.get("status").toString();
					if (executionStatus.equalsIgnoreCase("Fail")) {
						Validate.assertTrue(false, "Mainframe Application Health Check Failed", true,
								"Mainframe Application Working");

					}

					if (executionStatus.equals("PASS")) {
						String finalStatus = executionStatus + "ED";
						jsonObj.put("appStatus", finalStatus);
					} else {
						String finalStatus = executionStatus + "ED";
						jsonObj.put("appStatus", finalStatus);
					}

					jsonObj.put("appName", jsonObj1.get("name"));
					jsonArray.put(jsonObj);
				}

				return mainframeResult = jsonArray;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 
	 */
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
			PSTDate = sdf2.format(date) + " (PST)";
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
//	public static void main(String[] args) {
		
	
	public void delScreenshotDir() {
		try {
			String screenshotPath = SystemProperties.getStringValue("mainframe.build.report.artifact.path");
			String ScreenshotsPath = System.getProperty("user.dir")+screenshotPath;	
			String ScreenshotsDirPath= ScreenshotsPath.replaceAll("\\.", "");
			Reporting.logReporter(Status.INFO,"Screenshot folder is"+ ScreenshotsDirPath);
			FileUtils.cleanDirectory(new File(ScreenshotsDirPath));
		} catch (Exception e) {
			throw new RuntimeException(e);	
		}
	}

}
