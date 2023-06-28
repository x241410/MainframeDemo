package com.telus.bped.steps;

import com.telus.bped.pages.ControlMLoginPage;
import com.test.files.interaction.ReadJSON;
import com.test.reporting.Reporting;
import com.test.ui.actions.BaseSteps;
import com.test.ui.actions.Validate;
import com.test.ui.actions.WebDriverSession;
import com.test.ui.actions.WebDriverSteps;
import com.test.utils.EncryptionUtils;
import com.test.utils.Status;
import com.test.utils.SystemProperties;
import org.json.JSONObject;
import org.openqa.selenium.By;

public class ControlMLoginPageSteps extends BaseSteps {

    private static JSONObject jsonFile = new JSONObject(ReadJSON.parse("Environments.json"));
    private static JSONObject appNames = jsonFile.getJSONObject("APPLICATION_NAMES");
    public static JSONObject userAccessJsonFile = new JSONObject(ReadJSON.parse("UserAccess.json"));

    public void waitUntilPageLoads() {
        ControlMLoginPage LoginPage = new ControlMLoginPage();
        Waits.waitForElementVisibility(LoginPage.userNameInputBox);
        Waits.waitForElementVisibility(LoginPage.passwordInputBox);
        Waits.waitForElementVisibility(LoginPage.loginButton);
    }

//    public void singInSDApplication(String appName) {
//
//        Reporting.logReporter(Status.INFO, "STEP===> Perform Login into " + appName + "application.");
//        JSONObject userAccess = userAccessJsonFile.getJSONObject(SystemProperties.EXECUTION_ENVIRONMENT);
//
//        String username = ReadJSON.getString(userAccess, appName + "_username");
//        String password = ReadJSON.getString(userAccess, appName + "_password");
//
//        ControlMLoginPage LoginPage = new ControlMLoginPage();
//        waitUntilPageLoads();
//
//        System.out.println("username  = "+ EncryptionUtils.decode(username) +"pass = "+EncryptionUtils.decode(password));
//
//        SendKeys.sendKey(LoginPage.userNameInputBox, EncryptionUtils.decode(username));
//        SendKeys.sendKey(LoginPage.passwordInputBox, EncryptionUtils.decode(password));
//
//        Clicks.clickElement(LoginPage.loginButton);
//
//    }
    public void appLogin_ControlM() {

        String appName = ReadJSON.getString(appNames, "ControlM_APP_NAME");
        WebDriverSteps.openApplication(appName);
        Validate.takeStepScreenShot("Control M  Login Page");
//        singInSDApplication(appName);
        handleSiteNotSecureConnectionError();

    }
    public void handleSiteNotSecureConnectionError() {

        String pageTitle = WebDriverSession.getWebDriverSession().getTitle();
        while ("Form is not secure".equals(pageTitle)) {
            WebDriverSession.getWebDriverSession().findElement(By.xpath("//*[@id='proceed-button']")).click();
            pageTitle = WebDriverSession.getWebDriverSession().getTitle();
        }
        Reporting.logReporter(Status.DEBUG, "Handle handleSiteNotSecureConnectionError");

    }


}
