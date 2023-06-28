package com.telus.bped.pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.test.ui.actions.BaseSteps;
import com.test.ui.actions.WebDriverSession;

public class VPOPPage extends WebDriverSession {

	public VPOPPage() {

		PageFactory.initElements(getWebDriverSession(), this);

	}

	@FindBy(xpath = "//a[contains(text(), 'VPOP')]")
	public WebElement VPOPExternal_logo;
	
	@FindBy(xpath = "//a[text()='My Orders']")
	public WebElement myOrdersLink;
	
	@FindBy(xpath = "//a[text()='Search Order']")
	public WebElement searchOrderLink;
	
	@FindBy(xpath = "//*[@id='orderId']")
	public WebElement orderIdTextBox;
	
	@FindBy(xpath = "//*[@id='searchOrderNumForm']//div[2]/input")
	public WebElement searchOrderIdBtn;
	
	@FindBy(xpath = "//*[@id='searchOrderTable']/tbody/tr")
	public WebElement numberOfRecords;
	
	@FindBy(xpath = "//td[contains(text(), 'No data available')]")
	public WebElement noDataAvailableText;
	
	@FindBy(xpath = "//*[@id='searchOrderTable']//button[contains(text(),'Action')]")
	public WebElement recordAvailabilityForVPOPExternal;
	
	@FindBy(xpath = "//input[@value='Advanced Search']")
	public WebElement advanceSearchBtn;	
	
	@FindBy(xpath = "//a[contains(text(), 'Logout')]")
	public WebElement logoutBtn;

	public boolean isNoDataAvailableLabelDisplayed() {		
		try {
			if (noDataAvailableText.isDisplayed())
				return true;
			else
				return false;
		} catch (Exception e) {
			return false;
		}
	}
	
	public boolean isSearchResultDisplayed() {
		try {
			BaseSteps.Waits.waitForElementVisibilityLongWait(numberOfRecords);
			if (numberOfRecords.isDisplayed())
				return true;
			else
				return false;
		} catch (Exception e) {
			return false;
		}
	}
	
	public WebElement numberOfRecords() {

		WebDriverWait wait = new WebDriverWait(WebDriverSession.getWebDriverSession(), Duration.ofSeconds(30));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='searchOrderTable']/tbody/tr")));
	}

	public boolean isMyOrdersLinkDisplayed() {
		try {
			if (myOrdersLink.isDisplayed())
				return true;
			else
				return false;
		} catch (Exception e) {
			return false;
		}
	}
	public boolean isHomepageDisplayed(){
			
			try {
				BaseSteps.Waits.waitForElementVisibilityLongWait(VPOPExternal_logo);
				return VPOPExternal_logo.isDisplayed();
			} catch (Exception e) {
				return false;
				}
			}
	
	public boolean isRecordAvailableForVPOPExternal() {
		
		try {
			BaseSteps.Waits.waitForElementVisibilityLongWait(recordAvailabilityForVPOPExternal);
			return recordAvailabilityForVPOPExternal.isDisplayed();
		} catch (Exception e) {
			return false;
			}
		}
}
