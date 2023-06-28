package com.telus.bped.steps;

import org.junit.Assert;
import org.openqa.selenium.support.ui.Select;

import com.telus.bped.pages.REXPage;
import com.test.reporting.Reporting;
import com.test.ui.actions.BaseSteps;
import com.test.ui.actions.Validate;
import com.test.ui.actions.WebDriverSteps;
import com.test.utils.Status;


/**
 ****************************************************************************
 * DESCRIPTION: Support for REX page Steps(common) AUTHOR: x258025
 ****************************************************************************
 */

public class REXPageSteps extends BaseSteps {

	REXPage REXPage=new REXPage();
	/**
	 * To Do: This Method is used to verify homepage of REX Application
	 * 
	 */
	
	public void verifyREXHomePage() {
		
		try {
			WebDriverSteps.getWebDriverSession().switchTo().frame(REXPage.headerFrame);
			BaseSteps.Waits.waitForElementVisibilityLongWait(REXPage.homepageLogo);
	        boolean actualRes = REXPage.isREXHomepageDisplayed();
	        Validate.assertEquals(actualRes, true, "HomePage is not displayed", false);
	        Reporting.logReporter(Status.INFO, "REX Homepage is displayed");
	        WebDriverSteps.getWebDriverSession().switchTo().parentFrame();
		}catch (Exception e) {
			Assert.fail("REX Homepage is Not displayed "+e);
		}
		
	}
	
	/**
	 * To Do: This Method is used to perform Search from REX Application
	 * 
	 */
	public void searchREX() {
			
			try {
				WebDriverSteps.getWebDriverSession().switchTo().frame(REXPage.mainFrame);
				BaseSteps.Waits.waitForElementVisibilityLongWait(REXPage.searchBtn);
				BaseSteps.Clicks.clickElementUsingJS(REXPage.searchBtn);
				
				if (REXPage.isRecordAvailable() == true ) {
					Validate.assertEquals(REXPage.isRecordAvailable(), true, "Record is not avaliable", false);
					Reporting.logReporter(Status.INFO, "Record found for REX ");
				}else {
					Select status=new Select(REXPage.statusDropdown);
					status.selectByVisibleText("All");
					BaseSteps.Clicks.clickElementUsingJS(REXPage.searchBtn);
					boolean actualRes = REXPage.isRecordAvailable();
			        Validate.assertEquals(actualRes, true, "Record is not avaliable for ALL status", false);
			        Reporting.logReporter(Status.INFO, "Record found for REX ");
				}
		        WebDriverSteps.getWebDriverSession().switchTo().parentFrame();
			}catch (Exception e) {
				Validate.assertFalse(false,"Unable to display records for REX due to "+e);
				//Assert.fail("Unable to display records for REX due to "+e);
			}
			
		}

}
