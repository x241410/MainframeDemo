package com.telus.bped.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.test.ui.actions.BaseSteps;
import com.test.ui.actions.WebDriverSession;

public class HUMBOLDTPage  extends WebDriverSession {

	public HUMBOLDTPage() {

		PageFactory.initElements(getWebDriverSession(), this);

	}
	
	@FindBy(xpath = "//a[contains(text(), 'Logout')]")
	public WebElement logOutBtn;
	
	@FindBy(xpath = "//i[contains(text(), 'HUMBOLDT')]")
	public WebElement homePageHeading;
	
	@FindBy(xpath = "//a[contains(text(), 'Search')]")
	public WebElement searchTab;
	
	@FindBy(xpath = "//input[@name='contract_number']")
	public WebElement searchContractNoField;
	
	@FindBy(xpath = "//input[@name='RPTsubmit']")
	public WebElement SubmitBtn;
	
	@FindBy(xpath = "//a[contains(text(), 'View')]")
	public WebElement ViewBtn;
	
	public boolean isHomepageDisplayed(){
		
		try {
			BaseSteps.Waits.waitForElementVisibilityLongWait(homePageHeading);
			return homePageHeading.isDisplayed();
		} catch (Exception e) {
			return false;
			}
		}
	
	public boolean isContractAvailable(){
		
		try {
			BaseSteps.Waits.waitForElementVisibilityLongWait(ViewBtn);
			return ViewBtn.isDisplayed();
		} catch (Exception e) {
			return false;
			}
		}
}
