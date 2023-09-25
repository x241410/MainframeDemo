package com.telus.runners;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.jayway.jsonpath.JsonPath;
import com.telus.bped.steps.LoginPageSteps;
import com.telus.bped.utils.GoogleSheetData;
import com.telus.bped.utils.GoogleSheetsUtils;
import com.telus.bped.utils.MainframeUtils;
import com.test.cucumber.AbstractTestNGCucumberTests;
import com.test.reporting.Reporting;
import com.test.ui.actions.WebDriverSession;
import com.test.ui.actions.WebDriverSteps;
import com.test.utils.Status;
import com.test.utils.SystemProperties;

import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/resources/features", glue = {
		"com.telus.bped.stepsdefinition" }, tags = "@CBNBC", plugin = { "pretty",
				"com.test.cucumber.ExtentCucumberAdapter:", 
				"com.telus.cucumber.plugin.ReportPortalCucumberPlugin",
				"rerun:target/rerun.txt" }, monochrome = true, publish = true

)
public class AppCucumberRunner extends AbstractTestNGCucumberTests {

	@BeforeSuite
	public void beforeSuit() {

		boolean appType=SystemProperties.getBooleanValue("application.type.isMainframe");
		if (appType==true) {
			WebDriverSteps.openApplication("MAINFRAME");
		}
		
		Reporting.logReporter(Status.INFO,
				"......................................... Befor Suite called ....................................");
		System.out.println("Info : -- " + GoogleSheetData.getExecutionStatus());

		GoogleSheetsUtils googleSheetsUtils = new GoogleSheetsUtils();
		try {
			String useraccess = googleSheetsUtils.getKeyValue("USERACCESS", true);
			LoginPageSteps.userAccessVar = googleSheetsUtils.getJSONObjectFromGit(useraccess);
			String gSheetConfigData = googleSheetsUtils.getKeyValue("GSHEET_CONFIG", true);
			GoogleSheetsUtils.setgSheetConfigVar(googleSheetsUtils.getJSONObjectFromGit(gSheetConfigData));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		MainframeUtils MainframeUtils = new MainframeUtils();
		MainframeUtils.delScreenshotDir();
	}

	@AfterSuite
	public void afterSuite() throws Exception {
		Reporting.logReporter(Status.INFO,
				"......................................... After Suite called ....................................");
		Reporting.logReporter(Status.INFO, String.valueOf(GoogleSheetData.getExecutionStatus().size()));
		String status = String.valueOf(GoogleSheetData.getExecutionStatus());
		JSONArray p1_apps = getStatusBasedOnpriority(status, "$.[?(@.P1_APPS=='P1_APPS')]");
		JSONArray p2_apps = getStatusBasedOnpriority(status, "$.[?(@.P2_APPS=='P2_APPS')]");
		JSONArray p3_apps = getStatusBasedOnpriority(status, "$.[?(@.P3_APPS=='P3_APPS')]");
		Reporting.logReporter(Status.INFO, "whole status: " + status);
		Reporting.logReporter(Status.INFO,
				"-----------------------------------------------------------------------------------------------------------------");
		Reporting.logReporter(Status.INFO, "p1_apps: " + String.valueOf(p1_apps));
		Reporting.logReporter(Status.INFO,
				"-----------------------------------------------------------------------------------------------------------------");
		Reporting.logReporter(Status.INFO, "p2_apps: " + String.valueOf(p2_apps));
		Reporting.logReporter(Status.INFO,
				"-----------------------------------------------------------------------------------------------------------------");
		Reporting.logReporter(Status.INFO, "p3_apps: " + String.valueOf(p3_apps));

		System.out.println("Info : -- " + new JSONObject(GoogleSheetData.getExecutionStatus()));
		System.out.println("P1 APPS:======>" + p1_apps);
		System.out.println("P2 APPS:======>" + p2_apps);
		System.out.println("P3 APPS:======>" + p3_apps);

		GoogleSheetsUtils.updateBulKDataIntoGSheets(p1_apps, p2_apps, p3_apps);
		
		if (WebDriverSession.getWebDriverForCurrentThreat() != null) {
            WebDriverSteps.closeTheBrowser();
        }
		
		

		
	}

	/**
	 * @param json
	 * @param path
	 * @return
	 */
	private JSONArray getStatusBasedOnpriority(String json, String path) {
		try {
			Object tempJson = JsonPath.read(json, path);
			String jsonObj = tempJson.toString();
			return new JSONArray(jsonObj);
		} catch (Exception e) {
			Reporting.logReporter(Status.INFO, "JsonPath Exception: " + e);
			return null;
		}

	}

}
