package com.telus.bped.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

import com.test.ui.actions.BaseSteps;
import com.test.ui.actions.WebDriverSession;

public class REXPage extends WebDriverSession {

	public REXPage() {

		PageFactory.initElements(getWebDriverSession(), this);

	}

	
	@FindBy(xpath = "//img[contains(@src, 'telus.jpg')]")
	public WebElement homepageLogo;

	@FindBy(xpath = "//img[contains(@src, 'search.gif')]")
	public WebElement searchBtn;
	
	@FindBy(xpath = "//select[@id='status']")
	public WebElement statusDropdown;

	@FindBy(xpath = "//td[@class='myTitle_']/parent::tr/following-sibling::tr")
	public List<WebElement> availableRecords;
	
	@FindBy(xpath = "//td[contains(text(), 'No records found')]")
	public WebElement noRecordsAvailabe;
	
	@FindBy(xpath = "//img[contains(@src, 'logout.gif')]")
	public WebElement logoutBtn;

	@FindBy(xpath = "//frame[@name='search']")
	public WebElement headerFrame;
	
	@FindBy(xpath = "//frame[@name='main']")
	public WebElement mainFrame;
	
	public boolean isREXHomepageDisplayed(){
		
		try {
			BaseSteps.Waits.waitForElementVisibilityLongWait(homepageLogo);
			return homepageLogo.isDisplayed();
		} catch (Exception e) {
			return false;
			}
		}
	
	 public boolean isRecordAvailable() {
	       
		 int rexRecord= availableRecords.size();
		 if (rexRecord > 0) {
			 return true;
		 }
		 return false;
	    }
}
