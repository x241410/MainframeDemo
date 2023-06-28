package com.telus.bped.pages;

import com.test.ui.actions.WebDriverSession;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ControlMLoginPage extends WebDriverSession {

    @FindBy(id = "login-user-name")
    public  WebElement userNameInputBox;
    @FindBy(id = "login-user-password")
    public  WebElement passwordInputBox;
    @FindBy(id = "login-submit-button")
    public  WebElement loginButton;

    public ControlMLoginPage() {

        PageFactory.initElements(getWebDriverSession(), this);

    }
}
