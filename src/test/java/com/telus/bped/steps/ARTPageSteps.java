package com.telus.bped.steps;

import org.junit.Assert;
import org.openqa.selenium.support.ui.Select;

import com.telus.bped.pages.ARTPage;
import com.telus.bped.pages.REXPage;
import com.test.reporting.Reporting;
import com.test.ui.actions.BaseSteps;
import com.test.ui.actions.Validate;
import com.test.ui.actions.WebDriverSteps;
import com.test.utils.Status;


/**
 ****************************************************************************
 * DESCRIPTION: Support for ART page Steps(common) AUTHOR: x258025
 ****************************************************************************
 */

public class ARTPageSteps extends BaseSteps {

	/**
	 * To Do: This Method is used to verify home page of ART Application
	 * 
	 */
	ARTPage ARTPage=new ARTPage();
	
	public void verifyARTHomePage() {
		
		try {
	        boolean actualRes = ARTPage.isARTHomepageDisplayed();
	        Validate.assertEquals(actualRes, true, "HomePage is not displayed", false);
	        Reporting.logReporter(Status.INFO, "ART Homepage is displayed");
		}catch (Exception e) {
			Assert.fail("ART Homepage is Not displayed "+e);
		}
		
	}
	
	/**
	 * To Do: This Method is used to perform Search from ART
	 * 
	 */
	public void performSearchFromART() { 
			
		try {
			BaseSteps.Clicks.clickElement(ARTPage.activeBtn);
		}catch (Exception e) {
			BaseSteps.Waits.waitForElementToBeClickableLongWait(ARTPage.activeBtn);
        }
		try {
	        BaseSteps.Waits.waitForElementVisibilityLongWait(ARTPage.activeRecords);
	        boolean actualRes = ARTPage.isRecordAvailableForART();
	        Validate.assertEquals(actualRes, true, "Record displayed for Active ART ", false);   	
        }catch (Exception e) {
        	Validate.takeStepFullScreenShot("Search Results", Status.INFO);
        	Assert.fail("Record is Not available");
        }
	}

}
