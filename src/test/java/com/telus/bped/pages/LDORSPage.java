package com.telus.bped.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.test.ui.actions.BaseSteps;

import com.test.ui.actions.WebDriverSession;

public class LDORSPage extends WebDriverSession {

	public LDORSPage() {

		PageFactory.initElements(getWebDriverSession(), this);

	}

	@FindBy(xpath = "//a[text()='Search']")
	public WebElement searchLink;

	@FindBy(xpath = "//*[@id='status']")
	public WebElement statusDropdown;

	@FindBy(xpath = "//*[@id='action-submit']")
	public WebElement submitBtn;

	@FindBy(xpath = "//img[contains(@src,'details_open.png')]")
	public WebElement activeRecords;

	
	public boolean isSearchLinkDisplayed() {
		try {
			if (searchLink.isDisplayed())
				return true;
			else
				return false;
		} catch (Exception e) {
			return false;
		}
	}
	
	public boolean isRecordAvailableForLDORS() {
		
		try {
			BaseSteps.Waits.waitForElementVisibilityLongWait(activeRecords);
			return activeRecords.isDisplayed();
		} catch (Exception e) {
			return false;
			}
		}
}
