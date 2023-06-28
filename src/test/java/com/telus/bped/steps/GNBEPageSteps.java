package com.telus.bped.steps;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.telus.bped.pages.GNBEPage;
import com.test.reporting.Reporting;
import com.test.ui.actions.BaseSteps;
import com.test.ui.actions.Validate;
import com.test.ui.actions.WebDriverSteps;
import com.test.utils.Status;

/**
 ****************************************************************************
 * DESCRIPTION: Support for Order Resolution Team page Steps(common) AUTHOR: x258025
 ****************************************************************************
 */

public class GNBEPageSteps extends BaseSteps {

	/**
	 * To Do: This Method is used to verify home page of ART Application
	 * 
	 */
	GNBEPage GNBE=new GNBEPage();
	
	public void isGNBEHomepageDisplayed() {
		
		try {
	        boolean ITN04Result = GNBE.isGNBEHomepageDisplayedForITN04();
	        if (ITN04Result!=true) {
	        	boolean PRResult = GNBE.isGNBEHomepageDisplayedForPR();
	        	Validate.assertEquals(PRResult, true, "HomePage is not displayed", false);
		        Reporting.logReporter(Status.INFO, "GoNet Billing Engine Homepage is displayed");
	        }else {
	        	Validate.assertEquals(ITN04Result, true, "HomePage is not displayed", false);
		        Reporting.logReporter(Status.INFO, "GoNet Billing Engine Homepage is displayed");
	        }
	        
	        
//	        Validate.assertEquals(actualRes, true, "HomePage is not displayed", false);
//	        Reporting.logReporter(Status.INFO, "GoNet Billing Engine Homepage is displayed");
		}catch (Exception e) {
			Assert.fail("GoNet Billing Engine Homepage is Not displayed "+e);
		}
		
		
		
	}
	
	/**
	 * To Do: This Method is used to open server page
	 * 
	 */
	public void openServerPage() { 
		
	        BaseSteps.Waits.waitForElementVisibilityLongWait(GNBE.environmentPlusBtn);
	        BaseSteps.Clicks.clickElement(GNBE.environmentPlusBtn);
	        BaseSteps.Waits.waitForElementVisibilityLongWait(GNBE.serverLinkBtn);
	        BaseSteps.Clicks.clickElement(GNBE.serverLinkBtn);
	        BaseSteps.Waits.waitForElementVisibilityLongWait(GNBE.serverPage);
       
	}
	
	/**
	 * To Do: This Method is used to verify running server
	 * 
	 */
	int NotRunningServerCount=0;
	int serverCount;
	public void verifyServers() {
		
		serverCount=WebDriverSteps.getWebDriverSession()
				.findElements(By.xpath("//tr[contains(@class, 'row')]")).size();
		
			for (int i=1; i<=serverCount; i++) {
				
				String serverName=WebDriverSteps.getWebDriverSession()
						.findElement(By.xpath("//td[@id='name"+i+"']")).getText();
				
				String serverState=WebDriverSteps.getWebDriverSession()
						.findElement(By.xpath("//td[@id='name"+i+"']/following-sibling::td[@id='state"+i+"']")).getText();
				
				if (serverState.contains("RUNNING")) {
					Reporting.logReporter(Status.INFO, "Server Name is "+serverName+" and State is "+serverState);
				}else {
					Reporting.logReporter(Status.INFO, "Server "+serverName+" is not working and current state is "+serverState);
					NotRunningServerCount++;
				}

			}
			Validate.takeStepScreenShot("Servers details");
			if (NotRunningServerCount!=0) {
				Assert.fail("Total server count is "+serverCount+" and total not working server is "+NotRunningServerCount);
			}
			
		
	}
}