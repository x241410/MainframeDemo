package com.telus.bped.steps;

import com.telus.bped.pages.BLIFPage;
import com.telus.bped.pages.LDORSPage;
import com.test.reporting.Reporting;
import com.test.ui.actions.BaseSteps;
import com.test.ui.actions.Validate;
import com.test.utils.Status;

/**
 ****************************************************************************
 * DESCRIPTION: Support for LDORS page Steps(common) AUTHOR: x241410
 ****************************************************************************
 */

public class LDORSPageSteps extends BaseSteps {

	LDORSPage LDORSPage = new LDORSPage();
	
	/**
	 * To Do: This Method is used to verify homepage of LDORS Application
	 * 
	 */
	
	public void verifyHomePage() {
		
        BaseSteps.Waits.waitForElementVisibilityLongWait(LDORSPage.searchLink);
        boolean actualRes = LDORSPage.isSearchLinkDisplayed();
        Validate.assertEquals(actualRes, true, "HomePage is not displayed", false);
        Reporting.logReporter(Status.INFO, "LDORS Homepage is displayed");
	}
	
	/**
	 * To Do: This Method is used to perform Search from LDORS Application
	 * 
	 */
	public void performSearchFromLDORS() {
		
		BaseSteps.Dropdowns.selectByVisibleText(LDORSPage.statusDropdown, "Pass");
        Validate.takeStepScreenShot("Selected Search Parameter", Status.INFO);
        BaseSteps.Waits.waitForElementToBeClickable(LDORSPage.submitBtn);
        BaseSteps.Clicks.clickElement(LDORSPage.submitBtn);
        boolean actualRes = LDORSPage.isRecordAvailableForLDORS();
        Validate.assertEquals(actualRes, true, "Records is not displayed for LDORS", false);
	}
}
