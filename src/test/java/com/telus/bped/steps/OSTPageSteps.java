package com.telus.bped.steps;

import org.junit.Assert;

import com.telus.bped.pages.OSTPage;
import com.test.reporting.Reporting;
import com.test.ui.actions.BaseSteps;
import com.test.ui.actions.Validate;
import com.test.ui.actions.WebDriverSession;
import com.test.utils.Status;

/**
 ****************************************************************************
 * DESCRIPTION: Support for OST page Steps(common) AUTHOR: x258025
 ****************************************************************************
 */

public class OSTPageSteps extends BaseSteps {

	/**
	 * To Do: This Method is used to verify home page of ART Application
	 * 
	 */
	OSTPage OSTPage=new OSTPage();
	
	public void verifyOSTHomePage() {
		
		try {
	        boolean actualRes = OSTPage.isOSTHomepageDisplayed();
	        Validate.assertEquals(actualRes, true, "HomePage is not displayed", false);
	        Reporting.logReporter(Status.INFO, "OST Homepage is displayed");
		}catch (Exception e) {
			Assert.fail("OST Homepage is Not displayed "+e);
		}
		
	}
	
	/**
	 * To Do: This Method is used to verify OST link of OST Application
	 * @param Blif Phone Number
	 */
	
	public void verifyHeaderLink() {
		
		try {
			BaseSteps.Waits.waitForElementToBeClickableLongWait(OSTPage.searchLink);
            BaseSteps.Clicks.clickElement(OSTPage.searchLink);
	        boolean actualRes = OSTPage.isSearchPageDisplayed();
	        Validate.assertEquals(actualRes, true, "Search Page  is not displayed", false);
	        Reporting.logReporter(Status.INFO, "Search Page  is displayed");
		}catch (Exception e) {
			Assert.fail("Search Page  is Not displayed "+e);
		}
		
		try {
			BaseSteps.Waits.waitForElementToBeClickableLongWait(OSTPage.adminLink);
            BaseSteps.Clicks.clickElement(OSTPage.adminLink);
	        boolean actualRes = OSTPage.isAdminPageDisplayed();
	        Validate.assertEquals(actualRes, true, "Admin Page  is not displayed", false);
	        Reporting.logReporter(Status.INFO, "Admin Page  is displayed");
		}catch (Exception e) {
			Assert.fail("Admin Page  is Not displayed "+e);
		}
		
		try {
			BaseSteps.Waits.waitForElementToBeClickableLongWait(OSTPage.dashboardLink);
            BaseSteps.Clicks.clickElement(OSTPage.dashboardLink);
	        boolean actualRes = OSTPage.isDashboardPageDisplayed();
	        Validate.assertEquals(actualRes, true, "Dashboard Page  is not displayed", false);
	        Reporting.logReporter(Status.INFO, "Dashboard Page  is displayed");
		}catch (Exception e) {
			Assert.fail("Dashboard Page  is Not displayed "+e);
		}
		
		try {
			BaseSteps.Waits.waitForElementToBeClickableLongWait(OSTPage.adminDashboardLink);
            BaseSteps.Clicks.clickElement(OSTPage.adminDashboardLink);
	        boolean actualRes = OSTPage.isAdminDashboardPageDisplayed();
	        Validate.assertEquals(actualRes, true, "Admin Dashboard Page  is not displayed", false);
	        Reporting.logReporter(Status.INFO, "Admin Dashboard Page  is displayed");
	        WebDriverSession.getWebDriverSession().navigate().refresh();
		}catch (Exception e) {
			Assert.fail("Admin Dashboard Page  is Not displayed "+e);
		}
	}
}
