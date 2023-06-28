package com.telus.bped.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.test.ui.actions.WebDriverSession;

public class LoginPage extends WebDriverSession {

    public LoginPage() {

        PageFactory.initElements(getWebDriverSession(), this);

    }

    //SSO Application

    @FindBy(id = "username")
    public WebElement SSO_userNameInputBox;

    @FindBy(id = "password")
    public WebElement SSO_passwordInputBox;

    @FindBy(xpath = "//button[contains(text(),'Log in')]")
    public WebElement SSO_LoginBtn;

    //RRW application

    @FindBy(name = "txtUsername")
    public WebElement RRW_userNameInputBox;

    @FindBy(name = "txtPassword")
    public WebElement RRW_passwordInputBox;

    @FindBy(name = "imgEnterButton")
    public WebElement RRW_LoginBtn;

    //IVS2 External application

    @FindBy(id = "idtoken1")
    public WebElement IVS2_EXTERNAL_userNameInputBox;

    @FindBy(id = "idtoken2")
    public WebElement IVS2_EXTERNAL_passwordInputBox;

    @FindBy(id = "login-btn")
    public WebElement IVS2_EXTERNAL_LoginBtn;

    //IVS Legacy application

    @FindBy(name = "username")
    public WebElement LEGACY_IVS_userNameInputBox;

    @FindBy(name = "password")
    public WebElement LEGACY_IVS_passwordInputBox;

    @FindBy(xpath = "//input[contains(@src, 'submit.jpg')]")
    public WebElement LEGACY_IVS_LoginBtn;

    //VPOP External application

    @FindBy(id = "IDToken1")
    public WebElement VPOP_EXTERNAL_userNameInputBox;

    @FindBy(id = "IDToken2")
    public WebElement VPOP_EXTERNAL_passwordInputBox;

    @FindBy(xpath = ".//*[@value=\"Continue\"]")
    public WebElement VPOP_EXTERNAL_LoginBtn;

    //Contract Suite application

    @FindBy(id = "loginForm:workspace_login_user_name")
    public WebElement CONTRACT_SUITE_userNameInputBox;

    @FindBy(id = "loginForm:workspace_login_password")
    public WebElement CONTRACT_SUITE_passwordInputBox;

    @FindBy(id = "loginForm:submitbutton")
    public WebElement CONTRACT_SUITE_LoginBtn;

    //TLC App

    @FindBy(name = "login")
    public WebElement TLC_preLoginBtn;

    //Humboldt App
    @FindBy(id = "username")
    public WebElement HUMBOLDT_userNameInputBox;
    @FindBy(id = "password")
    public WebElement HUMBOLDT_passwordInputBox;

    @FindBy(xpath = "//input[@name='submit']")
    public WebElement HUMBOLDT_LoginBtn;

    //LSR App
    @FindBy(xpath = "//a[contains(text(), 'here')]")
    public WebElement LSR_preLoginBtn;

    //ECB APP
    @FindBy(id = "user")
    public WebElement ECB_userNameInputBox;

    @FindBy(id = "password")
    public WebElement ECB_passwordInputBox;


    @FindBy(id = "btnLogin")
    public WebElement ECB_LoginBtn;


    //LYNX
    @FindBy(id="s_swepi_1")
    public WebElement LYNX_userNameInputBox;

    @FindBy(id="s_swepi_2")
    public WebElement LYNX_passwordInputBox;

    @FindBy(id = "s_swepi_22")
    public WebElement LYNX_LoginBtn;




    /*
     *
     * Kenan Billing Platform
     * */
    @FindBy(id = "user")
    public WebElement KBP_userNameInputBox;

    @FindBy(id = "password")
    public WebElement KBP_passwordInputBox;


    @FindBy(id = "btnLogin")
    public WebElement KBP_LoginBtn;


    /*
     * Order Inquiry App
     * */
    @FindBy(name = "txtUsername")
    public WebElement ORDERINQUIRY_userNameInputBox;

    @FindBy(name = "txtPassword")
    public WebElement ORDERINQUIRY_passwordInputBox;

    @FindBy(name = "Submit")
    public WebElement ORDERINQUIRY_LoginBtn;

    /*
     * Customer Fulfillment App
     * */
    @FindBy(name = "user")
    public WebElement CUSTOMERFULFILLMENT_userNameInputBox;

    @FindBy(name = "pass")
    public WebElement CUSTOMERFULFILLMENT_passwordInputBox;

    @FindBy(xpath = "//input[@value=\"Login\"]")
    public WebElement CUSTOMERFULFILLMENT_LoginBtn;

    /*
     * WFMA App
     * */
    @FindBy(name = "j_username")
    public WebElement WFMA_userNameInputBox;

    @FindBy(name = "j_password")
    public WebElement WFMA_passwordInputBox;

    @FindBy(xpath = "//input[@value=\"Login\"]")
    public WebElement WFMA_LoginBtn;

    @FindBy(name = "userName")
    public WebElement OCP1_userNameInputBox;

    @FindBy(name = "password")
    public WebElement OCP1_passwordInputBox;

    @FindBy(xpath = "//input[@alt=\"Submit button\"]")
    public WebElement OCP1_LoginBtn;

    
    //GOnet Billing Engine
    @FindBy(name = "j_username")
    public WebElement GNBE_userNameInputBox;

    @FindBy(name = "j_password")
    public WebElement GNBE_passwordInputBox;

    @FindBy(xpath = "//input[@value='Login']")
    public WebElement GNBE_LoginBtn;

	@FindBy(name = "j_username")
	public WebElement BRS_userNameInputBox;

	@FindBy(name = "j_password")
	public WebElement BRS_passwordInputBox;

	@FindBy(xpath = "//input[@value=\"Login\"]")
	public WebElement BRS_LoginBtn;
	
	
	//MITS Reporting
    @FindBy(name = "username")
    public WebElement MITS_userNameInputBox;

    @FindBy(name = "password")
    public WebElement MITS_passwordInputBox;

    @FindBy(xpath = "//button[@tb-test-id='button-signin']")
    public WebElement MITS_LoginBtn;

}
