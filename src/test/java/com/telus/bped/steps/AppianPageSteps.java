package com.telus.bped.steps;

import org.junit.Assert;

import com.telus.bped.pages.AppianPage;
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

public class AppianPageSteps extends BaseSteps {

	/**
	 * To Do: This Method is used to verify home page of Appian Application
	 * 
	 */
	AppianPage AppianPage=new AppianPage();
	
	public void verifyHomePage() {
		
		try {
			WebDriverSteps.getWebDriverSession().switchTo().frame(AppianPage.homepageFrame);
			WebDriverSteps.getWebDriverSession().switchTo().frame(AppianPage.homepageLogoFrame);
	        boolean actualRes = AppianPage.isHomepageDisplayed();
	        Validate.assertEquals(actualRes, true, "HomePage is not displayed", false);
	        Reporting.logReporter(Status.INFO, "Appian Homepage is displayed");
	        WebDriverSteps.getWebDriverSession().switchTo().defaultContent();
		}catch (Exception e) {
			Assert.fail("Appian Homepage is Not displayed "+e);
		}
		
	} 
	public void verifySubscriptionPage() {
		
		try {
			
			WebDriverSteps.getWebDriverSession().switchTo().frame(AppianPage.homepageFrame);
			BaseSteps.Waits.waitForElementVisibilityLongWait(AppianPage.subscriptionLink);
			BaseSteps.Clicks.clickElement(AppianPage.subscriptionLink);
	        boolean actualRes = AppianPage.isSubscriptionpageDisplayed();
	        Validate.assertEquals(actualRes, true, "Subscription page is not displayed", false);
	        Reporting.logReporter(Status.INFO, "Subscription page is displayed");
		}catch (Exception e) {
			Assert.fail("Subscription page is Not displayed "+e);
		}
		
	} 

}
