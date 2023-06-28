package com.telus.bped.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import com.test.ui.actions.BaseSteps;
import com.test.ui.actions.WebDriverSession;
import com.test.ui.actions.WebDriverSteps;

public class IVSPage extends WebDriverSession {

	public IVSPage() {

		PageFactory.initElements(getWebDriverSession(), this);

	}

	/**
	 * Method Description: XPATH for IVS
	 */
	
	@FindBy(xpath = "//a[text()='Corporation']")
	public WebElement corporationLink;
	
	@FindBy(xpath = "//tbody/tr[contains(@class, 'notSelected')]")
	public WebElement recordAvailabilityForIVS2External;
	
	@FindBy(xpath = "//tbody/tr[contains(@class, 'notSelected')]")
	public WebElement recordAvailabilityForIVS2Internal;
	
	@FindBy(xpath = "//div[text()='Search']")
	public WebElement searchCorporationRecordForIVS2;
	
	@FindBy(xpath = "//input[@id='input-4']")
	public WebElement corporationName;
	
	@FindBy(xpath = "//div[contains(text(), 'Logout')]")
	public WebElement logoutBtn;
	
	//Legacy IVS
	
	@FindBy(xpath = "//frame[@name='search']")
	public WebElement searchFrame;
	
	@FindBy(xpath = "//img[contains(@src,'searchbox_corporation.gif')]")
	public WebElement corpSearchBox;
	
	@FindBy(xpath = "//frame[@name='main']")
	public WebElement mainFrame;
	
	@FindBy(xpath = "//input[@name='corpNameKey']")
	public WebElement searchName;
	
	@FindBy(xpath = "//img[contains(@src,'logout.gif')]")
	public WebElement logOutBtn_LegacyIVS;
	
	@FindBy(xpath = "//input[contains(@src,'submit.gif')]")
	public WebElement submitBtn;
	
	@FindBy(xpath = "//input[@type='checkbox' and @name='bodylist']")
	public WebElement recordAvailabilityForLegacyIVS;;
	
	public boolean isIVS2HomepageDisplayed(){
		
		try {
//			WebDriverWait wait = new WebDriverWait(WebDriverSession.getWebDriverSession(), Duration.ofSeconds(120));
//			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Corporation']")));
			BaseSteps.Waits.waitForElementVisibility(corporationLink,90);
			return corporationLink.isDisplayed();
		} catch (Exception e) {
			return false;
			}
		}
	public boolean isRecordAvailableForIVS2External() {
			
			try {
//				WebDriverWait wait = new WebDriverWait(WebDriverSession.getWebDriverSession(), Duration.ofSeconds(60));
//				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tbody/tr[contains(@class, 'notSelected')]")));
				BaseSteps.Waits.waitForElementVisibility(recordAvailabilityForIVS2External, 90);
				return recordAvailabilityForIVS2External.isDisplayed();
			} catch (Exception e) {
				return false;
				}
			}

	public boolean isRecordAvailableForIVS2Internal() {
		
		try {
//			WebDriverWait wait = new WebDriverWait(WebDriverSession.getWebDriverSession(), Duration.ofSeconds(60));
//			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tbody/tr[contains(@class, 'notSelected')]")));
			BaseSteps.Waits.waitForElementVisibility(recordAvailabilityForIVS2Internal,90);
			return recordAvailabilityForIVS2Internal.isDisplayed();
		} catch (Exception e) {
			return false;
			}
		}
	
	public boolean isLegacyIVSHomepageDisplayed(){
		
		try {
//			WebDriverWait wait = new WebDriverWait(WebDriverSession.getWebDriverSession(), Duration.ofSeconds(60));
//			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[contains(@src,'searchbox_corporation.gif')]")));
			BaseSteps.Waits.waitForElementVisibility(corpSearchBox,90);
			return corpSearchBox.isDisplayed();
		} catch (Exception e) {
			return false;
			}
		}
	
	public boolean isLegacyIVSRecordAvailable() {
		
		try {
			//WebDriverSteps.getWebDriverSession().switchTo().frame(mainFrame);
//			WebDriverWait wait = new WebDriverWait(WebDriverSession.getWebDriverSession(), Duration.ofSeconds(60));
//			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='checkbox' and @name='bodylist']")));
			BaseSteps.Waits.waitForElementVisibility(recordAvailabilityForLegacyIVS, 90);
			return recordAvailabilityForLegacyIVS.isDisplayed();
		} catch (Exception e) {
			return false;
			}
		}
}