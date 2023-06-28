package com.telus.bped.steps;

import com.telus.bped.pages.BLIFPage;
import com.test.reporting.Reporting;
import com.test.ui.actions.BaseSteps;
import com.test.ui.actions.Validate;
import com.test.utils.Status;

/**
 ****************************************************************************
 * DESCRIPTION: Support for BLIF page Steps(common) AUTHOR: x241410
 ****************************************************************************
 */

public class BLIFPageSteps extends BaseSteps {

	/**
	 * To Do: This Method is used to verify home page of BLIF Application
	 * 
	 */
	BLIFPage BLIFPage = new BLIFPage();
	
	public void verifyHomePage() {
		
		
		
		BaseSteps.Waits.waitForElementVisibilityLongWait(BLIFPage.searchLink);
        boolean actualRes = BLIFPage.isSearchLinkDisplayed();
        Validate.assertEquals(actualRes, true, "HomePage is not displayed", false);
        Reporting.logReporter(Status.INFO, "BLIF Homepage is displayed");
	}

	/**
	 * To Do: This Method is used to perform Search from BLIF Application
	 * @param Blif Phone Number
	 */
	
	public void performSearchFromBlif() {
		
        BaseSteps.Clicks.clickElement(BLIFPage.searchLink);
        BaseSteps.Waits.waitForElementVisibilityLongWait(BLIFPage.searchButton);
        BaseSteps.Clicks.clickElement(BLIFPage.searchButton);
        BLIFPage.isSearchResultDisplayed();
        boolean noSearchResultDisplayedStatus = BLIFPage.isNoDataAvailableLabelDisplayed();
        if (noSearchResultDisplayedStatus) {
            BaseSteps.SendKeys.clearFieldAndSendKeys(BLIFPage.phoneNumSearchTextBox, "");
            BaseSteps.Clicks.clickElement(BLIFPage.searchButton);
            BaseSteps.Waits.waitForElementVisibility(BLIFPage.numberOfRecords, 150);
        }

        Validate.takeStepFullScreenShot("Search Results", Status.INFO);
	}
		
}
