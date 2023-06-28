package com.telus.bped.steps;

import org.junit.Assert;

import com.telus.bped.pages.ARTPage;
import com.telus.bped.pages.CONTRACTSUITEPage;
import com.test.reporting.Reporting;
import com.test.ui.actions.BaseSteps;
import com.test.ui.actions.Validate;
import com.test.utils.EncryptDecrypt;
import com.test.utils.Status;

/**
 ****************************************************************************
 * DESCRIPTION: Support for CS page Steps(common) AUTHOR: x258025
 ****************************************************************************
 */
public class CONTRACTSUITEPageSteps  extends BaseSteps {

	/**
	 * To Do: This Method is used to verify home page of CS Application
	 * 
	 */
	CONTRACTSUITEPage CONTRACTSUITEPage=new CONTRACTSUITEPage();
	
	public void verifyCSHomePage() {
		
		try {
	        boolean actualRes = CONTRACTSUITEPage.isHomepageDisplayed();
	        Validate.assertEquals(actualRes, true, "HomePage is not displayed", false);
	        Reporting.logReporter(Status.INFO, "Contract Suite Homepage is displayed");
		}catch (Exception e) {
			Assert.fail("Contract Suite Homepage is Not displayed "+e);
		}
		
	}
	
	/**
	 * To Do: This Method is used to perform Search from CS Application
	 * 
	 */
	public void performSearchFromCS() {
		
		
		try {
	        boolean ActualRes = CONTRACTSUITEPage.isRecordAvailableForCS();
	        Validate.assertEquals(ActualRes, true, "Record Not Found In Contract Suite ", false);
	        Reporting.logReporter(Status.INFO, "Contract Suite Record is displayed");
		}catch (Exception e) {
			Assert.fail("Unable to find any data in Contract Suite Application due to "+e);
		}
	}

}
