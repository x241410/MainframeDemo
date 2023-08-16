package com.telus.runners;

import org.testng.annotations.BeforeSuite;

import com.telus.bped.steps.LoginPageSteps;
import com.telus.bped.utils.APIUtils;
import com.telus.bped.utils.MainframeUtils;
import com.test.cucumber.AbstractTestNGCucumberTests;
import com.test.reporting.Reporting;
import com.test.ui.actions.WebDriverSteps;
import com.test.utils.Status;

import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/resources/features", glue = {
		"com.telus.bped.stepsdefinition" }, tags = "@CRISAB", plugin = { "pretty",
				"com.test.cucumber.ExtentCucumberAdapter:", 
				"rerun:target/rerun.txt" }, monochrome = true, publish = true

)
public class AppCucumberRunner extends AbstractTestNGCucumberTests {

	@BeforeSuite
	public void beforeSuit() {

		WebDriverSteps.openApplication("MAINFRAME");
		Reporting.logReporter(Status.INFO,
				"......................................... Befor Suite called ....................................");

		 APIUtils APIUtils = new APIUtils();
	        try {
	            String useraccess = APIUtils.getKeyValue("USERACCESS", true);
	            LoginPageSteps.userAccessVar = APIUtils.getJSONObjectFromGit(useraccess);
	        } catch (Exception e) {
	            throw new RuntimeException(e);
	        }
		
		MainframeUtils MainframeUtils = new MainframeUtils();
		MainframeUtils.delScreenshotDir();
	}

}
