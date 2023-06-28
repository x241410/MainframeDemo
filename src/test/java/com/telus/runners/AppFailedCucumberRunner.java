package com.telus.runners;


import com.test.cucumber.AbstractTestNGCucumberTests;

import io.cucumber.testng.CucumberOptions;


@CucumberOptions(
		glue = {"com.telus.bped.stepsdefinition"}
		,plugin = { "pretty"
				  , "com.test.cucumber.ExtentCucumberAdapter:",
				     "com.telus.cucumber.plugin.ReportPortalCucumberPlugin",					
			        "rerun:target/rerun.txt" }
		,monochrome = true,				
				features = { "@target/rerun.txt" }
		
		)
public class AppFailedCucumberRunner extends AbstractTestNGCucumberTests {
	

	
}
