package com.telus.bped.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

import com.test.ui.actions.BaseSteps;
import com.test.ui.actions.WebDriverSession;

public class ARTPage extends WebDriverSession {

	public ARTPage() {

		PageFactory.initElements(getWebDriverSession(), this);

	}

	
	@FindBy(xpath = "//a[contains(text(), 'ART')]")
	public WebElement homepageLogo;

	@FindBy(xpath = "//li/a[contains(text(), 'Active')]")
	public WebElement activeBtn;
	
	@FindBy(xpath = "//table[@id='records']//tr[@data-request-id]")
	public WebElement activeRecords;
	
	@FindBy(xpath = "//td[contains(text(),'No matching records found')]")
	public WebElement noRecord;
	
	@FindBy(xpath = "//a[@class='dropdown-toggle']")
	public WebElement profileDropDown;
	
	@FindBy(xpath = "//a[contains(text(),'Signout')]")
	public WebElement singoutBtn;
	
	
	
	
	public boolean isARTHomepageDisplayed(){
			
			try {
				BaseSteps.Waits.waitForElementVisibilityLongWait(homepageLogo);
				return homepageLogo.isDisplayed();
			} catch (Exception e) {
				return false;
				}
			}
	public boolean isRecordAvailableForART() {
		
		try {
			BaseSteps.Waits.waitForElementVisibilityLongWait(activeRecords);
			return activeRecords.isDisplayed();
		} catch (Exception e) {
			return false;
			}
		}
}
