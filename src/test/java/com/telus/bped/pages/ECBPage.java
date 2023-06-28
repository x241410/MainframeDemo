package com.telus.bped.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.test.ui.actions.WebDriverSession;

public class ECBPage extends WebDriverSession {

	public ECBPage() {

		PageFactory.initElements(getWebDriverSession(), this);

	}
	@FindBy(xpath = "//div[@class=\"iconView\"]/a")
	public List<WebElement> ECB_iconViewLink;
	
	@FindBy(id = "username")
	public WebElement ECB_loggedOnAs;
	
	@FindBy(id = "viewButton")
	public WebElement ECB_viewButton;
	
	@FindBy(id = "logoffLink")
	public WebElement ECB_logout;

}
