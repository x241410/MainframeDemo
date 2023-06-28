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

public class LynxPage extends WebDriverSession {

	public LynxPage() {

		PageFactory.initElements(getWebDriverSession(), this);

	}
	
	@FindBy(xpath = "//*[@id='File']")
	public WebElement homePageText;

	@FindBy(xpath = "//a[text()='Search']")
	public WebElement searchLink;

	@FindBy(xpath = "//button[text()='Search']")
	public WebElement searchButton;


	@FindBy(xpath = "//*[@id='listingsTable']/tbody/tr")
	public WebElement numberOfRecords;
	
	@FindBy(xpath = "//td[contains(text(), 'No data available')]")
	public WebElement noDataAvailableText;
	
	
	@FindBy(xpath = "//a[contains(text(), 'Logout')]")
	public WebElement logoutBtn;

	public boolean isHomePageTextDisplayed() {
		WebDriverSession.getWebDriverSession().switchTo().frame("_sweclient");
		WebDriverSession.getWebDriverSession().switchTo().frame("_sweappmenu");
		try {
			BaseSteps.Waits.waitForElementVisibilityLongWait(homePageText);
			if (homePageText.isDisplayed())
				return true;
			else
				return false;
		} catch (Exception e) {
			return false;
		}
	}
	
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
	
	
	public WebElement navigateToRespectiveTabs(String tabName) {
		
		//WebDriverSession.getWebDriverSession().switchTo().frame("_sweclient");
		
		
		WebDriverWait wait = new WebDriverWait(WebDriverSession.getWebDriverSession(), Duration.ofSeconds(30));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='SWEApplet2']")));
	}

	

	public WebElement numberOfRecords() {

		WebDriverWait wait = new WebDriverWait(WebDriverSession.getWebDriverSession(), Duration.ofSeconds(30));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='listingsTable']/tbody/tr")));
	}

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
}
