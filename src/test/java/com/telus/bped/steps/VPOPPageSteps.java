package com.telus.bped.steps;

import org.junit.Assert;

import com.telus.bped.pages.VPOPPage;
import com.test.reporting.Reporting;
import com.test.ui.actions.BaseSteps;
import com.test.ui.actions.Validate;
import com.test.utils.Status;

/**
 ****************************************************************************
 * DESCRIPTION: Support for VPOP page Steps(common) AUTHOR: x241410
 ****************************************************************************
 */

	public class VPOPPageSteps extends BaseSteps {
	
		VPOPPage VPOPPage=new VPOPPage();
		
		/**
		 * To Do: This Method is used to verify homepage of VPOP Internal Application
		 * 
		 */
		public void verifyVPOPInternalHomePage() {
					
	        BaseSteps.Waits.waitForElementVisibilityLongWait(VPOPPage.myOrdersLink);
	        boolean actualRes = VPOPPage.isMyOrdersLinkDisplayed();
	        Validate.assertEquals(actualRes, true, "HomePage is not displayed", false);
	        Reporting.logReporter(Status.INFO, "VPOP Internal Homepage is displayed");
				
			}
		
		/**
		 * To Do: This Method is used to perform Search from VPOP Internal Application
		 * 
		 */
		
		public void performSearchFromVPOPInternal() {
			
	        BaseSteps.Clicks.clickElement(VPOPPage.searchOrderLink);
	        BaseSteps.Waits.waitForElementVisibilityLongWait(VPOPPage.searchOrderIdBtn);
	        BaseSteps.Clicks.clickElement(VPOPPage.searchOrderIdBtn);
	        VPOPPage.isSearchResultDisplayed();
	        BaseSteps.Waits.waitGeneric(2000);
	        boolean noSearchResultDisplayedStatus = VPOPPage.isNoDataAvailableLabelDisplayed();
	        if (noSearchResultDisplayedStatus) {
	            BaseSteps.Debugs.scrollToElement(VPOPPage.orderIdTextBox);
	            BaseSteps.SendKeys.clearFieldAndSendKeys(VPOPPage.orderIdTextBox, "");
	            BaseSteps.Waits.waitForElementVisibilityLongWait(VPOPPage.advanceSearchBtn);

	            BaseSteps.Debugs.scrollToElement(VPOPPage.advanceSearchBtn);
	            BaseSteps.Clicks.clickElementUsingJS(VPOPPage.advanceSearchBtn);
	            BaseSteps.Waits.waitForElementVisibility(VPOPPage.numberOfRecords, 150);
	        }

	        Validate.takeStepFullScreenShot("Search Results", Status.INFO);
			
		}

		/**
		 * To Do: This Method is used to verify homepage of VPOP External Application
		 * 
		 */
		
		public void verifyVPOP_ExternalHomePage() {
			
			try {
				
				boolean actualRes = VPOPPage.isHomepageDisplayed();
		        Validate.assertEquals(actualRes, true, "HomePage is not displayed", false);
		        Reporting.logReporter(Status.INFO, "VPOP_External Homepage is displayed");
		        
			}catch (Exception e) {
				Assert.fail("VPOP_External Homepage is Not displayed "+e);
			}
			
		}
		
		/**
		 * To Do: This Method is used to perform Search from VPOP External Application
		 * 
		 */
		
		public void searchVPOPExternal() {
			
			try {
				BaseSteps.Clicks.clickElement(VPOPPage.searchOrderLink);
		        BaseSteps.Waits.waitForElementVisibilityLongWait(VPOPPage.searchOrderIdBtn);
		        BaseSteps.Clicks.clickElement(VPOPPage.searchOrderIdBtn);
		        
		        boolean SearchResultForVPOPExternal = VPOPPage.isRecordAvailableForVPOPExternal();
		        Validate.assertEquals(SearchResultForVPOPExternal, true, "Record Not Found In VPOP External", false);
			}catch (Exception e) {
				Assert.fail("Unable to find any data in VPOP External Application due to "+e);
			}
		}
	}
