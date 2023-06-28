package com.telus.bped.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.test.ui.actions.BaseSteps;
import com.test.ui.actions.WebDriverSession;

public class GNBEPage  extends WebDriverSession {

	public GNBEPage() {

		PageFactory.initElements(getWebDriverSession(), this);

	}

	
	@FindBy(xpath = "//img[@id='iconDomainConfigGeneralPage']/parent::a[@title='ITN04GONetBillSvcQ']")
	public WebElement homepageITN04;
	
	@FindBy(xpath = "//img[@id='iconDomainConfigGeneralPage']/parent::a[@title='PRGONetBillWebQ']")
	public WebElement homepagePR;

	@FindBy(xpath = "//img[@id='joinEnvironmentsSummaryPage']/parent::a")
	public WebElement environmentPlusBtn;
	
	@FindBy(xpath = "//a[@id='linkCoreServerServerTablePage']")
	public WebElement serverLinkBtn;
	
	@FindBy(xpath = "//table[contains(@summary, 'Servers')]")
	public WebElement serverPage;
	
	@FindBy(xpath = "//td[@id='name1']/following-sibling::td[@id='state1']")
	public WebElement state;
	
	@FindBy(xpath = "//td[@id='name1']")
	public List<WebElement> serverName;
	
	
	
	@FindBy(xpath = "//tr[contains(@class, 'row')]")
	public WebElement totalServers;
	
	
	public boolean isGNBEHomepageDisplayedForITN04(){
			
			try {
				BaseSteps.Waits.waitForElementVisibilityLongWait(homepageITN04);
				return homepageITN04.isDisplayed();
			} catch (Exception e) {
				return false;
				}
			}
	
	public boolean isGNBEHomepageDisplayedForPR(){
		
		try {
			BaseSteps.Waits.waitForElementVisibilityLongWait(homepagePR);
			return homepagePR.isDisplayed();
		} catch (Exception e) {
			return false;
			}
		}
	
//	public List<WebElement> serverCount(){
//			return serverName.size();
//		
//		}
}
