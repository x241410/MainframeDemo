package com.telus.bped.steps;

import org.junit.Assert;

import com.telus.bped.pages.HUMBOLDTPage;
import com.test.reporting.Reporting;
import com.test.ui.actions.BaseSteps;
import com.test.ui.actions.Validate;
import com.test.utils.Status;

public class HUMBOLDTPageSteps extends BaseSteps {

	/**
	 * To Do: This Method is used to verify home page of HUMBOLDT Application
	 * 
	 */
	HUMBOLDTPage HUMBOLDTPage=new HUMBOLDTPage();
	
	public void verifyHumboldtHomePage() {
		
		try {
	        boolean actualRes = HUMBOLDTPage.isHomepageDisplayed();
	        Validate.assertEquals(actualRes, true, "HomePage is not displayed", false);
	        Reporting.logReporter(Status.INFO, "HUMBOLDT Homepage is displayed");
		}catch (Exception e) {
			Assert.fail("HUMBOLDT Homepage is Not displayed "+e);
		}
	}
	
	public void performSearchFromHUMBOLDT(String contarctNum) {
		
		try {
			BaseSteps.Waits.waitForElementVisibilityLongWait(HUMBOLDTPage.searchTab);
			BaseSteps.Clicks.clickElement(HUMBOLDTPage.searchTab);
			BaseSteps.Waits.waitForElementVisibilityLongWait(HUMBOLDTPage.searchContractNoField);
			BaseSteps.SendKeys.sendKey(HUMBOLDTPage.searchContractNoField, contarctNum);
			BaseSteps.Clicks.clickElement(HUMBOLDTPage.SubmitBtn);
			BaseSteps.Waits.waitForElementVisibility(HUMBOLDTPage.ViewBtn, 60);
			
			boolean actualRes = HUMBOLDTPage.isContractAvailable();
	        Validate.assertEquals(actualRes, true, "HUMBOLDT Contract is not displayed", false);
	        Reporting.logReporter(Status.INFO, "HUMBOLDT Contract  is displayed");	
		}catch (Exception e) {
			Validate.assertFalse(false,"Unable to display records for Humboldt due to "+e);
		}
	}
	
	
	
}
