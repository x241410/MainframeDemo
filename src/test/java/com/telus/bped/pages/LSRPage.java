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

public class LSRPage  extends WebDriverSession {

	public LSRPage() {

		PageFactory.initElements(getWebDriverSession(), this);

	}
	
	@FindBy(xpath = "//h2[text()= 'Search']")
	public WebElement homepageLogo;
	//a[contains(text(), 'LSR Search')]
	@FindBy(xpath = "//a[contains(text(), 'Logout')]")
	public WebElement logOutBtn;
	
	@FindBy(xpath = "//input[@id='searchfield']")
	public WebElement searchField;
	
	@FindBy(xpath = "//tr[@id='searchTR']//button")
	public WebElement searchBtn;
	
	@FindBy(xpath = "//tbody/tr[contains(@class, 'notSelected')]")
	public WebElement recordAvailabilityForLSR;
	
	@FindBy(xpath = "//a[contains(text(), 'here')]")
	public WebElement LSR_preLoginBtn;
	
	public boolean isLSRHomepageDisplayed(){
		
		try {
//			WebDriverWait wait = new WebDriverWait(WebDriverSession.getWebDriverSession(), Duration.ofSeconds(45));
//			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[text()= 'Search']")));
			BaseSteps.Waits.waitForElementVisibility(homepageLogo, 90);
			return homepageLogo.isDisplayed();
		} catch (Exception e) {
			return false;
			}
		}
	
	public boolean isRecordAvailableForLSR() {
		
		try {
//			WebDriverWait wait = new WebDriverWait(WebDriverSession.getWebDriverSession(), Duration.ofSeconds(45));
//			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tbody/tr[contains(@class, 'notSelected')]")));
			BaseSteps.Waits.waitForElementVisibility(recordAvailabilityForLSR, 120);
			return recordAvailabilityForLSR.isDisplayed();
		} catch (Exception e) {
			return false;
			}
		}
	
	public boolean isAppIsLogoff(){
		
		try {
//			WebDriverWait wait = new WebDriverWait(WebDriverSession.getWebDriverSession(), Duration.ofSeconds(45));
//			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(), 'here')]")));
			BaseSteps.Waits.waitForElementVisibility(LSR_preLoginBtn, 90);
			return LSR_preLoginBtn.isDisplayed();
		} catch (Exception e) {
			return false;
			}
		}
}
