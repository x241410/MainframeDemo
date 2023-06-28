package com.telus.bped.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.test.ui.actions.WebDriverSession;

public class RRWPage extends WebDriverSession {

	public RRWPage() {

		PageFactory.initElements(getWebDriverSession(), this);

	}
	
	@FindBy(xpath = "//a[(text()='Search Orders')]")
	public WebElement searchOrdersLink;
	
	@FindBy(xpath = "//*[@id='EnterImageButton']")
	public WebElement findImageBtn;	
	
	@FindBy(xpath = "//*[@id='NoOrdersFoundLabel']")
	public WebElement noOrdersFoundLabel;
	
	@FindBy(xpath = "//img[@src='images/back.gif']")
	public WebElement backBtn;
	
	@FindBy(xpath = "//a[contains(text(), 'Logout')]")
	public WebElement logoutBtn;
	
	
	public boolean isNoOrdersFoundLabelDisplayed() {		
		try {
			if (noOrdersFoundLabel.isDisplayed())
				return true;
			else
				return false;
		} catch (Exception e) {
			return false;
		}
	}
	

	public boolean isSearchOrdersLinkDisplayed() {
		try {
			if (searchOrdersLink.isDisplayed())
				return true;
			else
				return false;
		} catch (Exception e) {
			return false;
		}
	}

	
}
