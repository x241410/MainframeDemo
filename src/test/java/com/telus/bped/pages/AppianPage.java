package com.telus.bped.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.test.ui.actions.BaseSteps;
import com.test.ui.actions.WebDriverSession;

public class AppianPage extends WebDriverSession {

	public AppianPage() {

		PageFactory.initElements(getWebDriverSession(), this);

	} 
	
	
	
	@FindBy(xpath = "//frame[@id='fContent']")
	public WebElement homepageFrame;
	
	@FindBy(xpath = "//iframe[@id='miniWebIframe_353']")
	public WebElement homepageLogoFrame;
	
	@FindBy(xpath = "//strong[contains(text(), 'Welcome to Appian')]")
	public WebElement homepageLogo;
	
	@FindBy(xpath = "//div[@id='environmentNavigation']//a/em[contains(text(), 'Subscription')]")
	public WebElement subscriptionLink;
	
	@FindBy(xpath = "//h1[contains(text(), 'SBA')]")
	public WebElement subscriptionPage;
	
	@FindBy(xpath = "//a[contains(text(), 'Sign Out')]")
	public WebElement APPIAN_logout;
	
	public boolean isHomepageDisplayed(){
		
		try {
			BaseSteps.Waits.waitForElementVisibility(homepageLogo,60);
			return homepageLogo.isDisplayed();
		} catch (Exception e) {
			return false;
			}
		}
	
	public boolean isSubscriptionpageDisplayed(){
		
		try {
			BaseSteps.Waits.waitForElementVisibility(subscriptionPage,60);
			return subscriptionPage.isDisplayed();
		} catch (Exception e) {
			return false;
			}
		}
}
