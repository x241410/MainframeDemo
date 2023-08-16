package com.telus.bped.steps;

import java.lang.reflect.Field;

import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.telus.bped.pages.LoginPage;
import com.test.files.interaction.ReadJSON;
import com.test.reporting.Reporting;
import com.test.ui.actions.BaseSteps;
import com.test.ui.actions.Validate;
import com.test.ui.actions.WebDriverSession;
import com.test.ui.actions.WebDriverSteps;
import com.test.utils.EncryptionUtils;
import com.test.utils.Status;
import com.test.utils.SystemProperties;

/**
 ****************************************************************************
 * DESCRIPTION: Support for Login page Steps(common) AUTHOR: x241410
 ****************************************************************************
 */

public class LoginPageSteps extends BaseSteps {

	private static JSONObject jsonFile = new JSONObject(ReadJSON.parse("Environments.json"));
	private static JSONObject appNames = jsonFile.getJSONObject("APPLICATION_NAMES");
	private static JSONObject loginIsSSO = jsonFile.getJSONObject("LOGIN_IS_SSO");

	public static JSONObject userAccessVar = null;

	/**
	 * Method Description: The purpose of this method is to login page
	 * 
	 * @param appName
	 */
	public void singInToApplication(String appName) {
		WebElement userNameInputBox = null;
		WebElement passwordInputBox = null;
		WebElement loginBtn = null;
		WebElement PreLoginBtn = null;

		Reporting.logReporter(Status.INFO, "STEP===> Perform Login into " + appName + "application.");

//		JSONObject userAccess = userAccessJsonFile.getJSONObject(SystemProperties.EXECUTION_ENVIRONMENT);
		JSONObject userAccess = userAccessVar.getJSONObject(SystemProperties.EXECUTION_ENVIRONMENT);

		String username = ReadJSON.getString(userAccess, appName + "_username");
		String password = ReadJSON.getString(userAccess, appName + "_password");
		String locatorPrefix = appName.replace("-", "_");

		LoginPage loginPage = new LoginPage();

		// get login page locator
		if (ReadJSON.getBoolean(loginIsSSO, appName)) {
			userNameInputBox = getLoginPagelocatorElement(loginPage, "SSO_userNameInputBox", appName);
			passwordInputBox = getLoginPagelocatorElement(loginPage, "SSO_passwordInputBox", appName);
			loginBtn = getLoginPagelocatorElement(loginPage, "SSO_LoginBtn", appName);
			PreLoginBtn = getLoginPagelocatorElement(loginPage, locatorPrefix + "_preLoginBtn", appName);
		} else {
			userNameInputBox = getLoginPagelocatorElement(loginPage, locatorPrefix + "_userNameInputBox", appName);
			passwordInputBox = getLoginPagelocatorElement(loginPage, locatorPrefix + "_passwordInputBox", appName);
			loginBtn = getLoginPagelocatorElement(loginPage, locatorPrefix + "_LoginBtn", appName);
		}
		PreLoginBtn = getLoginPagelocatorElement(loginPage, locatorPrefix + "_preLoginBtn", appName);

		// This is extra steps needed for some Application
		if (null != PreLoginBtn) {
			BaseSteps.Waits.waitForElementVisibilityLongWait(PreLoginBtn);
			BaseSteps.Clicks.clickElement(PreLoginBtn);
		}

		// Perform action on element
		waitUntilApplicationPageLoads(userNameInputBox, passwordInputBox, loginBtn);
		BaseSteps.SendKeys.sendKey(userNameInputBox, EncryptionUtils.decode(username));
		BaseSteps.SendKeys.sendKey(passwordInputBox, EncryptionUtils.decode(password));
		BaseSteps.Clicks.clickElement(loginBtn);
		handleSiteNotSecureConnectionError();

	}

	public void waitUntilApplicationPageLoads(WebElement userNameInputBox, WebElement passwordInputBox,
			WebElement LoginBtn) {
		BaseSteps.Waits.waitForElementVisibilityLongWait(userNameInputBox);
		BaseSteps.Waits.waitForElementVisibilityLongWait(passwordInputBox);
		BaseSteps.Waits.waitForElementVisibilityLongWait(LoginBtn);
	}

	public WebElement getLoginPagelocatorElement(LoginPage loginPage, String locator, String appname) {
		try {
//			Reporting.logReporter(Status.INFO, "LogInfo===> start: get " + locator + " for  application " + appname);
			Field field = loginPage.getClass().getDeclaredField(locator);
			field.setAccessible(true);
			WebElement element = (WebElement) field.get(loginPage);
//			Reporting.logReporter(Status.INFO,
//					"LogInfo===> End:get " + locator + "for application " + appname + " is : " + element);
			return element;
		} catch (Exception e) {
			e.printStackTrace();
			Reporting.logReporter(Status.INFO,
					"LogInfo===> End:get " + locator + "for application " + appname + " is : " + e);
			return null;
		}

	}

	/**
	 * Method Description: The purpose of this method is to login into SSO based
	 * applications
	 * 
	 * @param appName - This holds the name of the application to be logged in
	 */

	public void appLogin(String appName) {
		LoginPageSteps LoginPageSteps = new LoginPageSteps();
		String applicationName = appName.toUpperCase();
		WebDriverSteps.openApplication(applicationName);
		Validate.takeStepScreenShot(applicationName + " Login Page");
		singInToApplication(applicationName);
	}

	public void openApplication(String appName) {
		WebDriverSteps.openApplication(appName.toUpperCase());
		Validate.takeStepScreenShot(appName.toUpperCase() + " Home Page");
	}

	/**
	 * Description: The purpose of this method is to handle form not secure page if
	 * displayed
	 *
	 */
	public void handleSiteNotSecureConnectionError() {

		String pageTitle = WebDriverSession.getWebDriverSession().getTitle();
		while ("Form is not secure".equals(pageTitle)) {
			WebDriverSession.getWebDriverSession().findElement(By.xpath("//*[@id='proceed-button']")).click();
			pageTitle = WebDriverSession.getWebDriverSession().getTitle();
		}
		Reporting.logReporter(Status.DEBUG, "Handle handleSiteNotSecureConnectionError");

	}

}
