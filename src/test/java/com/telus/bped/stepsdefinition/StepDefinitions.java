package com.telus.bped.stepsdefinition;

import static com.telus.bped.steps.LoginPageSteps.userAccessVar;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import com.intuit.karate.core.FeatureResult;
import com.telus.api.test.utils.APIJava;
import com.telus.bped.steps.LoginPageSteps;
import com.telus.bped.utils.APIUtils;
import com.telus.bped.utils.GenericUtils;
import com.telus.bped.utils.MainframeUtils;
import com.test.files.interaction.ReadJSON;
import com.test.reporting.Reporting;
import com.test.ui.actions.BaseSteps;
import com.test.ui.actions.BaseTest;
import com.test.ui.actions.Validate;
import com.test.utils.EncryptionUtils;
import com.test.utils.Status;
import com.test.utils.SystemProperties;

import io.cucumber.java.en.Given;

/**
 * ***************************************************************************
 * DESCRIPTION: This class contains the steps implementations for the BPED
 * application smoke tests AUTHOR: x241410
 * ***************************************************************************
 */

public class StepDefinitions extends BaseTest {

	public static JSONArray mainframeAppStatus = null;
	public static JSONArray mainframeCrisStatus = null;
	public static JSONArray mainframeSoecsStatus = null;
	static JSONObject appNames = null;
	String testCaseDescription = null;
	String environment = null;
	String dataFilePath = null;
	JSONObject commonDataJson = null;
	JSONObject commonDataVar = null;
	JSONObject dbAuthVar = null;
	String BPMPR_host = null;
	String BPMPR_port = null;
	String BPMPR_serviceName = null;
	String BPMPR_username = null;
	String BPMPR_password = null;
	LoginPageSteps loginPageSteps = new LoginPageSteps();

	public static String getJobsFolder(String appName) {

		String applicationName = appName.toUpperCase();

		if (applicationName.contains("VPOP")) {
			applicationName = ReadJSON.getString(appNames, "VPOP");

		} else {
			applicationName = ReadJSON.getString(appNames, applicationName);
		}
		return EncryptionUtils.decode(applicationName);
	}

	public static void captureScreenshots(String fileName) {

		try {
			String ssDirectory = System.getProperty("user.dir") + "\\MainframeProject\\atest\\screenshots";
			File f = new File(ssDirectory);
			GenericUtils.getAllImagesHelper(f, fileName);
		} catch (IOException e) {
			Reporting.logReporter(Status.DEBUG, "Unable to capture screenshots");
		}
	}

	@Given("test data configuration for {string}")
	public void test_data_configuration_for(String scriptName) {
		testCaseDescription = "The purpose of this test case is to verify \"" + scriptName + "\" workflow.";
		environment = SystemProperties.EXECUTION_ENVIRONMENT;
		APIUtils googleSheetsUtils = new APIUtils();
		try {
			String commonData = googleSheetsUtils.getKeyValue("BPED_TEST_DATA", true);
			commonDataVar = googleSheetsUtils.getJSONObjectFromGit(commonData);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		commonDataJson = commonDataVar.getJSONObject(environment);
		appNames = commonDataVar.getJSONObject("JOBSFOLDER");

		Reporting.logReporter(Status.INFO,
				"Automation Configuration - Environment Configured for Automation Execution [" + environment + "]");
		Reporting.logReporter(Status.INFO, "Test Case Name : [" + scriptName + "]");
		Reporting.logReporter(Status.INFO, "Test Case Description : [" + testCaseDescription + "]");

	}

	@Given("user login into {string}")
	public void user_login_into(String applicationName) {
		loginPageSteps.appLogin(applicationName);
	}

	@Given("user open {string} Application")
	public void user_openApprication(String applicationName) {
		loginPageSteps.openApplication(applicationName);
	}

	@Given("Test {string} Applications")
	public void verifyMainframeApp(String applicationName) throws IOException {

		MainframeUtils MainframeUtils = new MainframeUtils();
		// MainframeUtils.delScreenshotDir();
		String robotFilePath = System.getProperty("user.dir") + File.separator + "MainframeProject" + File.separator
				+ "Tests" + File.separator + applicationName + ".robot";

		/*
		 * For local runs, uncomment below line
		 */

		// String wsUrl = System.getProperty("user.dir");

		/*
		 * For Jenkins runs
		 */
		String buildUrl = System.getenv("BUILD_URL");
		String wsUrl = buildUrl.split("BPED_Mainframe_Test")[0].concat("BPED_Mainframe_Test/ws/");

		String reportFilePath = SystemProperties.getStringValue("mainframe.build.report.artifact.path")
				+ applicationName + "_Report" + ".html";
		String logFilePath = SystemProperties.getStringValue("mainframe.build.report.artifact.path") + applicationName
				+ "_Log" + ".html";
		String reportFileArtifectPath = wsUrl + reportFilePath;
		String logFileArtifectPath = wsUrl + logFilePath;

		try {
			String command = MainframeUtils.generateCommand(getEnviornmentVariablesForApplication(applicationName),
					robotFilePath, reportFilePath, reportFileArtifectPath, logFilePath, logFileArtifectPath);

			MainframeUtils.launchMainframeApplication(command);

		} catch (Exception e) {
			Reporting.logReporter(Status.INFO,
					"Unable to validate " + applicationName + " application health check" + e);
		} finally {
			captureScreenshots(applicationName);
		}
		mainframeAppStatus = MainframeUtils.getMainframeAppStatus();
	}

	/**
	 * 
	 * @param applicationName
	 * @return
	 */
	private HashMap<String, String> getEnviornmentVariablesForApplication(String applicationName) {
		JSONObject userAccess = userAccessVar.getJSONObject(SystemProperties.EXECUTION_ENVIRONMENT);

		switch (applicationName) {
		case "CRISAB":
		case "CAMSAB":
		case "CRIS3AB":
		case "CAMSBC":
		case "CRISBC":
		case "CRIS3BC": {
			String tn_number = null;
			String region = null;
			if (applicationName.contains("CAMSAB") || applicationName.contains("CRISAB")
					|| applicationName.contains("CRIS3AB")) {
				tn_number = EncryptionUtils.decode(userAccess.getString("POST_TN_AB"));
				region = EncryptionUtils.decode(userAccess.getString("TPX_AB_REGION"));
			} else {
				tn_number = EncryptionUtils.decode(userAccess.getString("POST_TN_BC"));
				region = EncryptionUtils.decode(userAccess.getString("TPX_BC_REGION"));
			}
			String region_username = EncryptionUtils.decode(userAccess.getString("TPX_AB_BC_REGION_USERNAME"));// .getString("TPX_AB_IMSE_ENV_USERNAME");
			String region_pass = EncryptionUtils.decode(userAccess.getString("TPX_AB_BC_REGION_PASSWORD"));
			String cris_username = EncryptionUtils.decode(userAccess.getString("TPX_AB_BC_CRIS_USERNAME"));
			String cris_pass = EncryptionUtils.decode(userAccess.getString("TPX_AB_BC_CRIS_PASSWORD"));

			HashMap<String, String> keys = new HashMap<>();

			keys.put("POST_TN", tn_number);
			keys.put("REGION", region);
			keys.put("REG_USERNAME", region_username);
			keys.put("REG_PASSWORD", region_pass);
			keys.put("APP_USERNAME", cris_username);
			keys.put("APP_PASSWORD", cris_pass);
//                keys.put("PATH",robotFilePath);

			return keys;
		}
		case "SOECS": {
			String soecs_username = EncryptionUtils.decode(userAccess.getString("SOECS_USERNAME"));
			String soecs_pass = EncryptionUtils.decode(userAccess.getString("SOECS_PASSWORD"));
			HashMap<String, String> keys = new HashMap<>();
			keys.put("SOECS_USERNAME", soecs_username);
			keys.put("SOECS_PASSWORD", soecs_pass);
//                keys.put("PATH",robotFilePath);

			return keys;
		}

		default:
			return null;
		}
	}

}