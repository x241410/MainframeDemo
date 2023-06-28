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

public class MileageCalculatorPage extends WebDriverSession {

	public MileageCalculatorPage() {

		PageFactory.initElements(getWebDriverSession(), this);

	}
	/**
	 * Method Description: XPATH for Mileage Calculator
	 */
	
	@FindBy(xpath = "//p[contains(text(),'Mileage Calculation')]")
	public WebElement MileageCalculationDashboard;
	
	@FindBy(xpath = "//td//input[@name='nametxt']")
	public WebElement CalculationNameTxt;
	
	@FindBy(xpath = "//input[@value='Retrieve']")
	public WebElement RetrieveBtn;

	@FindBy(xpath = "//*[@name=\"fromCityProv0\" and @type=\"text\"]")
	public WebElement fromCityTown;

	@FindBy(xpath = "//*[@name=\"fromNpa0\" and @type=\"text\"]")
	public WebElement fromNPA;
	@FindBy(xpath = "//*[@name=\"fromNxx0\" and @type=\"text\"]")
	public WebElement fromNXX;

	@FindBy(xpath = "//*[@name=\"toNpa0\" and @type=\"text\"]")
	public WebElement toNPA;
	@FindBy(xpath = "//*[@name=\"toNxx0\" and @type=\"text\"]")
	public WebElement toNXX;

	@FindBy(xpath = "//*[@name=\"fromCityProv0\" and @type=\"text\"]")
	public WebElement toCityProv0;

	
	public boolean isHomepageDisplayed(){
		
		try {
//			WebDriverWait wait = new WebDriverWait(WebDriverSession.getWebDriverSession(), Duration.ofSeconds(30));
//			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[contains(text(),'Mileage Calculation')]")));
			BaseSteps.Waits.waitForElementVisibilityLongWait(MileageCalculationDashboard);
			return MileageCalculationDashboard.isDisplayed();
		} catch (Exception e) {
			return false;
			}
		}
}
