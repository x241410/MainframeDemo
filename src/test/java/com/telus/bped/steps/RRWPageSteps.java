package com.telus.bped.steps;

import com.telus.bped.pages.LDORSPage;
import com.telus.bped.pages.RRWPage;
import com.test.reporting.Reporting;
import com.test.ui.actions.BaseSteps;
import com.test.ui.actions.Validate;
import com.test.utils.Status;

/**
 ****************************************************************************
 * DESCRIPTION: Support for RRW page Steps(common) AUTHOR: x241410
 ****************************************************************************
 */

public class RRWPageSteps extends BaseSteps {

	RRWPage RRWPage = new RRWPage();
	
	/**
	 * To Do: This Method is used to verify homepage of RRW Application
	 * 
	 */

	public void verifyHomePage() {
		
		 BaseSteps.Waits.waitForElementVisibilityLongWait(RRWPage.searchOrdersLink);
	     boolean actualRes = RRWPage.isSearchOrdersLinkDisplayed();
	     Validate.assertEquals(actualRes, true, "HomePage is not displayed", false);
	     Reporting.logReporter(Status.INFO, "RRW Homepage is displayed");
	     
		}
	
	/**
	 * To Do: This Method is used to perform Search from RRW Application
	 * 
	 */
	
	public void performSearchFromRRW() {
		
        BaseSteps.Clicks.clickElement(RRWPage.searchOrdersLink);
        BaseSteps.Waits.waitForElementVisibilityLongWait(RRWPage.findImageBtn);

        BaseSteps.Clicks.clickElement(RRWPage.findImageBtn);

        boolean searchResultDisplayedStatus = RRWPage.isNoOrdersFoundLabelDisplayed();
        if (!searchResultDisplayedStatus) {
            Validate.takeStepFullScreenShot("Search Results", Status.INFO);
        }

        Validate.takeStepFullScreenShot("Search Results", Status.INFO);

		}
}
