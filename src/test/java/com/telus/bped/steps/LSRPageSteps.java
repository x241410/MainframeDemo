package com.telus.bped.steps;

import org.junit.Assert;

import com.telus.bped.pages.ARTPage;
import com.telus.bped.pages.LSRPage;
import com.test.reporting.Reporting;
import com.test.ui.actions.BaseSteps;
import com.test.ui.actions.Validate;
import com.test.utils.Status;

/**
 ****************************************************************************
 * DESCRIPTION: Support for LSR page Steps(common) AUTHOR: x258025
 ****************************************************************************
 */

public class LSRPageSteps  extends BaseSteps {

	/**
	 * To Do: This Method is used to verify home page of LSR Application
	 * 
	 */
	LSRPage LSRPage=new LSRPage();
	
	public void verifyLSRHomePage() {
		
		try {
	        boolean actualRes = LSRPage.isLSRHomepageDisplayed();
	        Validate.assertEquals(actualRes, true, "HomePage is not displayed", false);
	        Reporting.logReporter(Status.INFO, "LSR Homepage is displayed");
		}catch (Exception e) {
			Assert.fail("LSR Homepage is Not displayed "+e);
		}
		
	}
	
	public void isAppLogoff() {
		if (LSRPage.isAppIsLogoff()==true) {
			BaseSteps.Waits.waitForElementVisibilityLongWait(LSRPage.LSR_preLoginBtn);
			BaseSteps.Clicks.clickElement(LSRPage.LSR_preLoginBtn);
		}
	}
	
	/**
	 * To Do: This Method is used to perform Search from LSR Application
	 * 
	 */
	public void performSearchFromLSR(String lsrSearch) {
		
			isAppLogoff();
			LSRPage lsrPage=new LSRPage();
			BaseSteps.Waits.waitForElementVisibility(lsrPage.searchField, 90);
			isAppLogoff();
			BaseSteps.Clicks.clickElementLongWait(lsrPage.searchField);
			BaseSteps.SendKeys.sendKey(lsrPage.searchField, lsrSearch);
			try {
				BaseSteps.Waits.waitForElementVisibility(lsrPage.searchBtn, 90);
				BaseSteps.Clicks.clickElementLongWait(lsrPage.searchBtn);
				BaseSteps.Debugs.scrollToElement(lsrPage.searchBtn);
			}catch (Exception e) {
				isAppLogoff();
				BaseSteps.Waits.waitForElementVisibility(lsrPage.searchBtn, 90);
				BaseSteps.Clicks.clickElementLongWait(lsrPage.searchBtn);
				BaseSteps.Debugs.scrollToElement(lsrPage.searchBtn);
			
			}
	        boolean SearchResultForLSR = lsrPage.isRecordAvailableForLSR();
	        Validate.assertEquals(SearchResultForLSR, true, "Record Not Found In LSR", false);
	        Reporting.logReporter(Status.INFO, "LSR Record is displayed");
	}
}
