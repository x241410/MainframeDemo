package com.telus.bped.steps;

import org.junit.Assert;
import org.openqa.selenium.support.ui.Select;

import com.telus.bped.pages.ARTPage;
import com.telus.bped.pages.IVSPage;
import com.telus.bped.pages.REXPage;
import com.test.reporting.Reporting;
import com.test.ui.actions.BaseSteps;
import com.test.ui.actions.Validate;
import com.test.ui.actions.WebDriverSteps;
import com.test.utils.EncryptionUtils;
import com.test.utils.Status;


	/**
	 ****************************************************************************
	 * DESCRIPTION: Support for IVS2 External page Steps(common) AUTHOR: x258025
	 ****************************************************************************
	 */

	public class IVSPageSteps extends BaseSteps {
	
		IVSPage IVSPage=new IVSPage();
		
		/**
		 * To Do: This Method is used to verify homepage of IVS2 Exteral Application
		 * 
		 */
		
		public void verifyIVS2InternalHomePage() {
			
			try {
				boolean actualRes = IVSPage.isIVS2HomepageDisplayed();
		        Validate.assertEquals(actualRes, true, "HomePage is not displayed", false);
		        Reporting.logReporter(Status.INFO, "IVS2_External Homepage is displayed");
			}catch (Exception e) {
				boolean actualRes = IVSPage.isIVS2HomepageDisplayed();
		        Validate.assertEquals(actualRes, true, "HomePage is not displayed", false);
		        Reporting.logReporter(Status.INFO, "IVS2_External Homepage is displayed");
				//Assert.fail("IVS2_External Homepage is Not displayed "+e);
			}
			
		}
		
		public void verifyIVS2ExternalHomePage() {
			
			try {
				boolean actualRes = IVSPage.isIVS2HomepageDisplayed();
		        Validate.assertEquals(actualRes, true, "HomePage is not displayed", false);
		        Reporting.logReporter(Status.INFO, "IVS2_External Homepage is displayed");
			}catch (Exception e) {
				boolean actualRes = IVSPage.isIVS2HomepageDisplayed();
		        Validate.assertEquals(actualRes, true, "HomePage is not displayed", false);
		        Reporting.logReporter(Status.INFO, "IVS2_External Homepage is displayed");
				//Assert.fail("IVS2_External Homepage is Not displayed "+e);
			}
			
		}
		
		/**
		 * To Do: This Method is used to verify homepage of IVS Legacy Application
		 * 
		 */
		
		public void verifyLegacyIVSHomePage() {
			
			try {
				WebDriverSteps.getWebDriverSession().switchTo().frame(IVSPage.searchFrame);
				boolean actualRes = IVSPage.isLegacyIVSHomepageDisplayed();
		        Validate.assertEquals(actualRes, true, "HomePage is not displayed", false);
		        Reporting.logReporter(Status.INFO, "Legacy IVS Homepage is displayed");
		        
			}catch (Exception e) {
				Assert.fail("Legacy IVS Homepage is Not displayed "+e);
			}
			
		}
		
		/**
		 * To Do: This Method is used to perform Search from IVS2 Exteral Application
		 * 
		 */
		public void searchIVS2External() {
			
			try {
				BaseSteps.Waits.waitForElementVisibility(IVSPage.corporationLink, 60);
				BaseSteps.Waits.waitForElementToBeClickable(IVSPage.corporationLink);
				BaseSteps.Clicks.clickElement(IVSPage.corporationLink);
				
		        BaseSteps.Waits.waitForElementVisibilityLongWait(IVSPage.searchCorporationRecordForIVS2);
		        BaseSteps.Clicks.clickElement(IVSPage.searchCorporationRecordForIVS2);
		        
		        boolean SearchResultForIVS2External = IVSPage.isRecordAvailableForIVS2External();
		        Validate.assertEquals(SearchResultForIVS2External, true, "Record Not Found In IVS2 External", false);
		        Reporting.logReporter(Status.INFO, "IVS2_External Record is displayed");
			}catch (Exception e) {
				Assert.fail("Unable to find any data in IVS2 External Application due to "+e);
			}
		}
		
		/**
		 * To Do: This Method is used to perform Search from IVS2 Internal Application
		 * 
		 */
		public void performSearchFromIVS2Internal() {
			
			try {
				BaseSteps.Waits.waitForElementVisibility(IVSPage.corporationLink, 60);
				BaseSteps.Waits.waitForElementToBeClickable(IVSPage.corporationLink);
				BaseSteps.Clicks.clickElement(IVSPage.corporationLink);
				
				BaseSteps.SendKeys.sendKey(IVSPage.corporationName,"test");
				
		        BaseSteps.Waits.waitForElementVisibilityLongWait(IVSPage.searchCorporationRecordForIVS2);
		        BaseSteps.Clicks.clickElement(IVSPage.searchCorporationRecordForIVS2);
		        
		        boolean SearchResultForIVS2Internal = IVSPage.isRecordAvailableForIVS2Internal();
		        Validate.assertEquals(SearchResultForIVS2Internal, true, "Record Not Found In IVS2 Internal", false);
		        Reporting.logReporter(Status.INFO, "IVS2_Internal Record is displayed");
			}catch (Exception e) {
				Assert.fail("Unable to find any data in IVS2 Internal Application due to "+e);
			}
		}
		
		/**
		 * To Do: This Method is used to perform Search from IVS2 Internal Application
		 * 
		 */
		public void performSearchFromLegacyIVS(String ivs2Search) {
			
			try {
				BaseSteps.Waits.waitForElementVisibility(IVSPage.searchName, 60);
				BaseSteps.SendKeys.sendKey(IVSPage.searchName,ivs2Search);
				BaseSteps.Clicks.clickElement(IVSPage.submitBtn);
				WebDriverSteps.getWebDriverSession().switchTo().parentFrame();
				WebDriverSteps.getWebDriverSession().switchTo().frame(IVSPage.mainFrame);

		        boolean SearchResultForLegacyIVS = IVSPage.isLegacyIVSRecordAvailable();
		        Validate.assertEquals(SearchResultForLegacyIVS, true, "Record Not Found In Legacy IVS", false);
		        Reporting.logReporter(Status.INFO, "Legacy IVS Record is displayed");
		        WebDriverSteps.getWebDriverSession().switchTo().parentFrame();
		        WebDriverSteps.getWebDriverSession().switchTo().frame(IVSPage.searchFrame);
			}catch (Exception e) {
				Assert.fail("Unable to find any data in Legacy IVS Application due to "+e);
			}
		}
	}
