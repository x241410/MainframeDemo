package com.telus.bped.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;

import com.test.reporting.Reporting;
import com.test.ui.actions.BaseSteps;
import com.test.ui.actions.Validate;
import com.test.ui.actions.WebDriverSession;
import com.test.utils.Status;

public class CustomerFulfillmentPage extends WebDriverSession{
	 public CustomerFulfillmentPage(){
	        PageFactory.initElements(getWebDriverSession(), this);
	    }
	@FindBy(name = "ff_productClass")
	public WebElement productTypeSelect;

	@FindBy(name = "ff_object_uid")
	public WebElement fullFillmentSelect;
	
	 public void isLogInSucceed() {
		 WebDriver driver = getWebDriverSession();
		 BaseSteps.Waits.waitUntilPageLoadCompleteLongWait();
		 String actualRes=driver.getTitle();
	     Reporting.logReporter(Status.INFO, "Customer Fulfillment page is displayed");		 		 
		 Validate.assertEquals(actualRes, "Customer Fulfillment", "Customer Fulfillment page is displayed", false);
	 }
	 public void isProductTypeLoaded() {
		 BaseSteps.Waits.waitUntilPageLoadCompleteLongWait();
		 BaseSteps.Waits.waitForElementToBeClickable(productTypeSelect);
		
		 Select selectsProductType = new Select(productTypeSelect);
		 List<WebElement> optionsProductType = selectsProductType.getOptions();
	     Reporting.logReporter(Status.INFO, "Product Type got loaded");		 		 
		 Validate.assertEquals(optionsProductType!=null&&optionsProductType.size()>0,true, "Product Type list got loaded", false);
		 selectsProductType.selectByIndex(0);
		BaseSteps.Waits.waitGeneric(5*1000);
		 Select selectsFullFillment = new Select(fullFillmentSelect);
		 List<WebElement> optionsFullFillment = selectsFullFillment.getOptions();
	     Reporting.logReporter(Status.INFO, "Fullfillment list got loaded");		 		 
		 Validate.assertEquals(optionsFullFillment!=null&&optionsFullFillment.size()>0,true, "Fullfillment got loaded", false);
		 		 
	 }

}
