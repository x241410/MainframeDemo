package com.telus.bped.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.test.ui.actions.WebDriverSession;

public class logOutPage extends WebDriverSession {

	public logOutPage() {

		PageFactory.initElements(getWebDriverSession(), this);

	}
	
	//SSO Application

	@FindBy(id = "username")
	public WebElement SSO_userNameInputBox;

}
