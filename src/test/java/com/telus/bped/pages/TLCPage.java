package com.telus.bped.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.test.ui.actions.BaseSteps;
import com.test.ui.actions.WebDriverSession;

public class TLCPage extends WebDriverSession {

	public TLCPage() {

		PageFactory.initElements(getWebDriverSession(), this);

	}
	
	@FindBy(xpath = "//button[contains(text(), 'Logout')]")
	public WebElement logoutBtn;
	
	@FindBy(xpath = "//a[contains(text(), 'Browse Submissions')]")
	public WebElement BrowseSubNav;
	
	@FindBy(xpath = "//thead/following-sibling::tbody//button")
	public WebElement AvailabeRecords;
	
	public boolean isHomepageDisplayed(){
		
		try {
			BaseSteps.Waits.waitForElementVisibilityLongWait(BrowseSubNav);
			return BrowseSubNav.isDisplayed();
		} catch (Exception e) {
			return false;
			}
		}
	public boolean isRecordAvailableForTLC() {
			
			try {
				WebDriverWait wait = new WebDriverWait(WebDriverSession.getWebDriverSession(), Duration.ofSeconds(45));
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//thead/following-sibling::tbody//button")));
				BaseSteps.Waits.waitForElementVisibilityLongWait(AvailabeRecords);
				return AvailabeRecords.isDisplayed();
			} catch (Exception e) {
				return false;
				}
			}
}
