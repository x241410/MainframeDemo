package com.telus.bped.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.test.reporting.Reporting;
import com.test.ui.actions.BaseSteps;
import com.test.ui.actions.Validate;
import com.test.ui.actions.WebDriverSession;
import com.test.utils.Status;

public class WFMAPage extends WebDriverSession {

	public WFMAPage() {

		PageFactory.initElements(getWebDriverSession(), this);

	}
	
	@FindBy(id = "console-title")
	public WebElement homePageLogo;
	
	@FindBy(id = "joinServicesSummaryPage")
	public WebElement servicesPlusIcon;
	
	@FindBy(id = "joinServicesJmsSummaryPage")
	public WebElement messagingPlusIcon;
	
	@FindBy(id = "linkBridgeMessagingBridgeTablePage")
	public WebElement messagingBridgeLink;
	
	@FindBy(xpath = "//li[@title=\"Monitoring- Tab\"]")
	public WebElement monitoringTab;
	
	@FindBy(xpath = "//table/tbody/tr[(td[.='CRIS_WFM_InBound_AB']) and (td[.='ResMgtUtilSrv1'])]/td[contains(@id,'description')]")
	public WebElement CRIS_WFM_InBound_AB_server1;
	
	@FindBy(xpath = "//table/tbody/tr[(td[.='CRIS_WFM_InBound_AB']) and (td[.='ResMgtUtilSrv2'])]/td[contains(@id,'description')]")
	public WebElement CRIS_WFM_InBound_AB_server2;
	
	@FindBy(xpath = "//table/tbody/tr[(td[.='CRIS_WFM_InBound_BC']) and (td[.='ResMgtUtilSrv1'])]/td[contains(@id,'description')]")
	public WebElement CRIS_WFM_InBound_BC_server1;
	
	@FindBy(xpath = "//table/tbody/tr[(td[.='CRIS_WFM_InBound_BC']) and (td[.='ResMgtUtilSrv2'])]/td[contains(@id,'description')]")
	public WebElement CRIS_WFM_InBound_BC_server2;
	
	 public void isLogInSucceed() {
		 WebDriver driver = getWebDriverSession();
		 BaseSteps.Waits.waitUntilPageLoadCompleteLongWait();
		 //String actualRes=driver.getTitle();
	     Reporting.logReporter(Status.INFO, "WFMA page home page got Loaded");		 		 
		 Validate.assertEquals(homePageLogo.isDisplayed(), true, "WFMA page home page got Loaded", false);
	 }
	 
	 public void verifyMessagingBridge() {
		 BaseSteps.Waits.waitForElementToBeClickable(servicesPlusIcon);
		 BaseSteps.Clicks.clickElement(servicesPlusIcon);
		 
		 BaseSteps.Waits.waitForElementToBeClickable(messagingPlusIcon);
		 BaseSteps.Clicks.clickElement(messagingPlusIcon);

		 BaseSteps.Waits.waitForElementToBeClickable(messagingBridgeLink);
		 BaseSteps.Clicks.clickElement(messagingBridgeLink);

		 BaseSteps.Waits.waitForElementToBeClickable(monitoringTab);
		 BaseSteps.Clicks.clickElement(monitoringTab);
	
	     Reporting.logReporter(Status.INFO, "verify CRIS_WFM_InBound_AB_server1 message: expected- Forwarding messages. Acctual: "+CRIS_WFM_InBound_AB_server1.getText());		 		 	 		 
		 Validate.assertEquals(CRIS_WFM_InBound_AB_server1.getText(),"Forwarding messages.", "verify CRIS_WFM_InBound_AB_server1 message", false);
		 
	     Reporting.logReporter(Status.INFO, "verify CRIS_WFM_InBound_AB_server2 message: expected- Forwarding messages. Acctual: "+CRIS_WFM_InBound_AB_server2.getText());		 		 	 		 
		 Validate.assertEquals(CRIS_WFM_InBound_AB_server2.getText(),"Forwarding messages.", "verify CRIS_WFM_InBound_AB_server2 message", false);
		 
	     Reporting.logReporter(Status.INFO, "verify CRIS_WFM_InBound_BC_server1 message: expected- Forwarding messages. Acctual: "+CRIS_WFM_InBound_BC_server1.getText());		 		 	 		 
		 Validate.assertEquals(CRIS_WFM_InBound_BC_server1.getText(),"Forwarding messages.", "verify CRIS_WFM_InBound_BC_server1 message", false);
		 
	     Reporting.logReporter(Status.INFO, "verify CRIS_WFM_InBound_BC_server2 message: expected- Forwarding messages. Acctual: "+CRIS_WFM_InBound_BC_server2.getText());		 		 	 		 
		 Validate.assertEquals(CRIS_WFM_InBound_BC_server2.getText(),"Forwarding messages.", "verify CRIS_WFM_InBound_BC_server2 message", false);
		 
	 }

}
