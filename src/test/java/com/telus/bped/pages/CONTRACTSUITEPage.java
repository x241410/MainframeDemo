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

public class CONTRACTSUITEPage extends WebDriverSession {

	public CONTRACTSUITEPage() {

		PageFactory.initElements(getWebDriverSession(), this);

	}
	
	@FindBy(xpath = "//span[contains(text(), \"You're logged in\")]")
	public WebElement homepageLogo;
	
	@FindBy(xpath = "//input[contains(@name, 'ableWorkList:_OCID0_:checkBox')]")
	public WebElement availableRecords;
	
	@FindBy(xpath = "//a[@id='_id9_signOutLinkLink']")
	public WebElement logOutBtn;
	
	public boolean isHomepageDisplayed(){
		
		try {
			BaseSteps.Waits.waitForElementVisibilityLongWait(homepageLogo);
			return homepageLogo.isDisplayed();
		} catch (Exception e) {
			return false;
			}
		}
	public boolean isRecordAvailableForCS() {
			
			try {
				WebDriverWait wait = new WebDriverWait(WebDriverSession.getWebDriverSession(), Duration.ofSeconds(45));
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[contains(@name, 'ableWorkList:_OCID0_:checkBox')]")));
				BaseSteps.Waits.waitForElementVisibilityLongWait(availableRecords);
				return availableRecords.isDisplayed();
			} catch (Exception e) {
				return false;
				}
			}
}
