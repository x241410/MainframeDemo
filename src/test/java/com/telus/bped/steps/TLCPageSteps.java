package com.telus.bped.steps;

import org.junit.Assert;

import com.telus.bped.pages.ARTPage;
import com.telus.bped.pages.TLCPage;
import com.test.reporting.Reporting;
import com.test.ui.actions.BaseSteps;
import com.test.ui.actions.Validate;
import com.test.utils.Status;

public class TLCPageSteps  extends BaseSteps {

	/**
	 * To Do: This Method is used to verify home page of TLC Application
	 * 
	 */
	TLCPage TLCPage=new TLCPage();
	
	public void verifyTLCHomePage() {
		
		try {
			boolean actualRes = TLCPage.isHomepageDisplayed();
	        Validate.assertEquals(actualRes, true, "HomePage is not displayed", false);
	        Reporting.logReporter(Status.INFO, "TLC Homepage is displayed");
		}catch (Exception e) {
			Assert.fail("TLC Homepage is Not displayed "+e);
		}
		
	}
	
	/**
	 * To Do: This Method is used to perform Search from TLC Application
	 * 
	 */
	public void performSearchFromTLC() {
		
		try {
	        boolean ActualRes = TLCPage.isRecordAvailableForTLC();
	        Validate.assertEquals(ActualRes, true, "Record Not Found In TLC ", false);
	        Reporting.logReporter(Status.INFO, "TLC Record is displayed");
		}catch (Exception e) {
			Assert.fail("Unable to find any data in TLC Application due to "+e);
		}
	}

}
