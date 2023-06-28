package com.telus.bped.steps;

import java.lang.reflect.Field;

import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.telus.bped.pages.ARTPage;
import com.telus.bped.pages.AppianPage;
import com.telus.bped.pages.BLIFPage;
import com.telus.bped.pages.CONTRACTSUITEPage;
import com.telus.bped.pages.ECBPage;
import com.telus.bped.pages.HUMBOLDTPage;
import com.telus.bped.pages.IVSPage;
import com.telus.bped.pages.LSRPage;
import com.telus.bped.pages.LoginPage;
import com.telus.bped.pages.OSTPage;
import com.telus.bped.pages.REXPage;
import com.telus.bped.pages.RRWPage;
import com.telus.bped.pages.TLCPage;
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

	public static JSONObject userAccessVar=null;
	/**
	 * Method Description: The purpose of this method is to wait until Login Page
	 * loads
	 */
//	public void waitUntilPageLoads() {
//		LoginPage LoginPage = new LoginPage();
//		BaseSteps.Waits.waitForElementVisibility(LoginPage.SSO_userNameInputBox);
//		BaseSteps.Waits.waitForElementVisibility(LoginPage.SSO_passwordInputBox);
//		BaseSteps.Waits.waitForElementVisibility(LoginPage.SSO_userNameInputBox);
//	}
//
//	/**
//	 * Method Description: The purpose of this method is to wait until Login Page
//	 * loads
//	 */
//	public void waitUntilRRWPageLoads() {
//		LoginPage LoginPage = new LoginPage();
//		BaseSteps.Waits.waitForElementVisibility(LoginPage.RRW_userNameInputBox);
//		BaseSteps.Waits.waitForElementVisibility(LoginPage.RRW_passwordInputBox);
//		BaseSteps.Waits.waitForElementVisibility(LoginPage.RRW_LoginBtn);
//	}
//
//	public void waitUntilIVS2ExternalPageLoads() {
//		LoginPage LoginPage = new LoginPage();
//		BaseSteps.Waits.waitForElementVisibility(LoginPage.IVS2_EXTERNAL_userNameInputBox);
//		BaseSteps.Waits.waitForElementVisibility(LoginPage.IVS2_EXTERNAL_passwordInputBox);
//		BaseSteps.Waits.waitForElementVisibility(LoginPage.IVS2_EXTERNAL_LoginBtn);
//	}
//
//	public void waitUntilVPOPExternalPageLoads() {
//		LoginPage LoginPage = new LoginPage();
//		BaseSteps.Waits.waitForElementVisibility(LoginPage.VPOP_EXTERNAL_userNameInputBox);
//		BaseSteps.Waits.waitForElementVisibility(LoginPage.VPOP_EXTERNAL_passwordInputBox);
//		BaseSteps.Waits.waitForElementVisibility(LoginPage.VPOP_EXTERNAL_LoginBtn);
//	}
//
//	public void waitUntilCSPageLoads() {
//		LoginPage LoginPage = new LoginPage();
//		BaseSteps.Waits.waitForElementVisibility(LoginPage.CONTRACT_SUITE_userNameInputBox);
//		BaseSteps.Waits.waitForElementVisibility(LoginPage.CONTRACT_SUITE_passwordInputBox);
//		BaseSteps.Waits.waitForElementVisibility(LoginPage.CONTRACT_SUITE_LoginBtn);
//	}
//
//	public void waitUntilIVSLegacyPageLoads() {
//		LoginPage LoginPage = new LoginPage();
//		BaseSteps.Waits.waitForElementVisibility(LoginPage.LEGACY_IVSL_passwordInputBox);
//		BaseSteps.Waits.waitForElementVisibility(LoginPage.LEGACY_IVSL_passwordInputBox);
//		BaseSteps.Waits.waitForElementVisibility(LoginPage.LEGACY_IVSL_LoginBtn);
//	}
//
//	public void waitUntilHumboldtPageLoads() {
//		LoginPage LoginPage = new LoginPage();
//		// BaseSteps.Waits.waitForElementVisibility(LoginPage.userNameInputBox);
//		// BaseSteps.Waits.waitForElementVisibility(LoginPage.passwordInputBox);
//		BaseSteps.Waits.waitForElementVisibility(LoginPage.HUMBOLDT_LoginBtn);
//	}
//
	

//	/**
//	 * Method Description: The purpose of this method is to login using SSO login
//	 * for TLC page
//	 * 
//	 * @param appName
//	 */
//	public void singInToTLCSSOApplication(String appName) {
//
//		Reporting.logReporter(Status.INFO, "STEP===> Perform Login into " + appName + "application.");
//		JSONObject userAccess = userAccessJsonFile.getJSONObject(SystemProperties.EXECUTION_ENVIRONMENT);
//
//		String username = ReadJSON.getString(userAccess, appName + "_username");
//		String password = ReadJSON.getString(userAccess, appName + "_password");
//
//		LoginPage LoginPage = new LoginPage();
//		BaseSteps.Waits.waitForElementVisibilityLongWait(LoginPage.TLC_preLoginBtn);
//		BaseSteps.Clicks.clickElement(LoginPage.TLC_preLoginBtn);
//		waitUntilPageLoads();
//		BaseSteps.SendKeys.sendKey(LoginPage.SSO_userNameInputBox, EncryptionUtils.decode(username));
//		BaseSteps.SendKeys.sendKey(LoginPage.SSO_passwordInputBox, EncryptionUtils.decode(password));
//
//		BaseSteps.Clicks.clickElement(LoginPage.SSO_LoginBtn);
//
//	}
//
//	/**
//	 * Method Description: The purpose of this method is to login using SSO login
//	 * for LSR page
//	 * 
//	 * @param appName
//	 */
//	public void singInToLSRSSOApplication(String appName) {
//
//		Reporting.logReporter(Status.INFO, "STEP===> Perform Login into " + appName + "application.");
//		JSONObject userAccess = userAccessJsonFile.getJSONObject(SystemProperties.EXECUTION_ENVIRONMENT);
//
//		String username = ReadJSON.getString(userAccess, appName + "_username");
//		String password = ReadJSON.getString(userAccess, appName + "_password");
//
//		LoginPage LoginPage = new LoginPage();
//		BaseSteps.Waits.waitForElementVisibilityLongWait(LoginPage.LSR_preLoginBtn);
//		BaseSteps.Clicks.clickElement(LoginPage.LSR_preLoginBtn);
//		waitUntilPageLoads();
//		BaseSteps.SendKeys.sendKey(LoginPage.SSO_userNameInputBox, EncryptionUtils.decode(username));
//		BaseSteps.SendKeys.sendKey(LoginPage.SSO_passwordInputBox, EncryptionUtils.decode(password));
//
//		BaseSteps.Clicks.clickElement(LoginPage.SSO_LoginBtn);
//
//	}
//
//	/**
//	 * Method Description: The purpose of this method is to login using SSO login
//	 * page
//	 * 
//	 * @param appName
//	 */
//	public void singInToNonSSOApplication(String appName) {
//
//		Reporting.logReporter(Status.INFO, "STEP===> Perform Login into " + appName + "application.");
//		JSONObject userAccess = userAccessJsonFile.getJSONObject(SystemProperties.EXECUTION_ENVIRONMENT);
//
//		String username = ReadJSON.getString(userAccess, appName + "_username");
//		String password = ReadJSON.getString(userAccess, appName + "_password");
//
//		LoginPage LoginPage = new LoginPage();
//		waitUntilRRWPageLoads();
//
//		BaseSteps.SendKeys.sendKey(LoginPage.RRW_userNameInputBox, EncryptionUtils.decode(username));
//		BaseSteps.SendKeys.sendKey(LoginPage.RRW_passwordInputBox, EncryptionUtils.decode(password));
//
//		BaseSteps.Clicks.clickElement(LoginPage.RRW_LoginBtn);
//
//	}
//
//	/**
//	 * Method Description: The purpose of this method is to login IVS2 External page
//	 * 
//	 * @param appName
//	 */
//	public void singInToIVS2ExternalApplication(String appName) {
//
//		Reporting.logReporter(Status.INFO, "STEP===> Perform Login into " + appName + "application.");
//		JSONObject userAccess = userAccessJsonFile.getJSONObject(SystemProperties.EXECUTION_ENVIRONMENT);
//
//		String username = ReadJSON.getString(userAccess, appName + "_username");
//		String password = ReadJSON.getString(userAccess, appName + "_password");
//
//		LoginPage LoginPage = new LoginPage();
//		waitUntilIVS2ExternalPageLoads();
//
//		BaseSteps.SendKeys.sendKey(LoginPage.IVS2_EXTERNAL_userNameInputBox, EncryptionUtils.decode(username));
//		BaseSteps.SendKeys.sendKey(LoginPage.IVS2_EXTERNAL_passwordInputBox, EncryptionUtils.decode(password));
//
//		BaseSteps.Clicks.clickElement(LoginPage.IVS2_EXTERNAL_LoginBtn);
//
//	}
//
//	/**
//	 * Method Description: The purpose of this method is to login VPOP External page
//	 * 
//	 * @param appName
//	 */
//	public void singInToVPOPExternalApplication(String appName) {
//
//		Reporting.logReporter(Status.INFO, "STEP===> Perform Login into " + appName + "application.");
//		JSONObject userAccess = userAccessJsonFile.getJSONObject(SystemProperties.EXECUTION_ENVIRONMENT);
//
//		String username = ReadJSON.getString(userAccess, appName + "_username");
//		String password = ReadJSON.getString(userAccess, appName + "_password");
//
//		LoginPage LoginPage = new LoginPage();
//		waitUntilVPOPExternalPageLoads();
//		BaseSteps.SendKeys.sendKey(LoginPage.VPOP_EXTERNAL_userNameInputBox, EncryptionUtils.decode(username));
//		BaseSteps.SendKeys.sendKey(LoginPage.VPOP_EXTERNAL_passwordInputBox, EncryptionUtils.decode(password));
//
//		BaseSteps.Clicks.clickElement(LoginPage.VPOP_EXTERNAL_LoginBtn);
//
//	}
//
//	/**
//	 * Method Description: The purpose of this method is to login Contract Suite CS
//	 * page
//	 * 
//	 * @param appName
//	 */
//	public void singInToContractSuiteApplication(String appName) {
//
//		Reporting.logReporter(Status.INFO, "STEP===> Perform Login into " + appName + "application.");
//		JSONObject userAccess = userAccessJsonFile.getJSONObject(SystemProperties.EXECUTION_ENVIRONMENT);
//
//		String username = ReadJSON.getString(userAccess, appName + "_username");
//		String password = ReadJSON.getString(userAccess, appName + "_password");
//
//		LoginPage LoginPage = new LoginPage();
//		waitUntilCSPageLoads();
//		BaseSteps.SendKeys.sendKey(LoginPage.CONTRACT_SUITE_userNameInputBox, EncryptionUtils.decode(username));
//		BaseSteps.SendKeys.sendKey(LoginPage.CONTRACT_SUITE_passwordInputBox, EncryptionUtils.decode(password));
//
//		BaseSteps.Clicks.clickElement(LoginPage.CONTRACT_SUITE_LoginBtn);
//
//	}
//
//	/**
//	 * Method Description: The purpose of this method is to login Legacy IVS page
//	 * 
//	 * @param appName
//	 */
//	public void singInToLegacyIVSApplication(String appName) {
//
//		Reporting.logReporter(Status.INFO, "STEP===> Perform Login into " + appName + "application.");
//		JSONObject userAccess = userAccessJsonFile.getJSONObject(SystemProperties.EXECUTION_ENVIRONMENT);
//
//		String username = ReadJSON.getString(userAccess, appName + "_username");
//		String password = ReadJSON.getString(userAccess, appName + "_password");
//
//		LoginPage LoginPage = new LoginPage();
//		waitUntilIVSLegacyPageLoads();
//		BaseSteps.SendKeys.sendKey(LoginPage.LEGACY_IVSL_userNameInputBox, EncryptionUtils.decode(username));
//		BaseSteps.SendKeys.sendKey(LoginPage.LEGACY_IVSL_passwordInputBox, EncryptionUtils.decode(password));
//
//		BaseSteps.Clicks.clickElement(LoginPage.LEGACY_IVSL_LoginBtn);
//
//	}
//
	
//	/**
//	 * Method Description: The purpose of this method is to login using SSO login
//	 * page
//	 * 
//	 * @param appName
//	 */
//	public void singInToSSOApplication(String appName) {
//		WebElement userNameInputBox = null;
//		WebElement passwordInputBox = null;
//		WebElement loginBtn = null;
//		WebElement PreLoginBtn = null;
//
//		Reporting.logReporter(Status.INFO, "STEP===> Perform Login into " + appName + "application.");
//		JSONObject userAccess = userAccessJsonFile.getJSONObject(SystemProperties.EXECUTION_ENVIRONMENT);
//
//		String username = ReadJSON.getString(userAccess, appName + "_username");
//		String password = ReadJSON.getString(userAccess, appName + "_password");
//
//		LoginPage loginPage = new LoginPage();
//
//		userNameInputBox = getLoginPagelocatorElement(loginPage,  "SSO_userNameInputBox",appName);
//		passwordInputBox = getLoginPagelocatorElement(loginPage,   "SSO_passwordInputBox",appName);
//		loginBtn = getLoginPagelocatorElement(loginPage, "SSO_LoginBtn",appName);
//		PreLoginBtn = getLoginPagelocatorElement(loginPage, appName + "_preLoginBtn",appName);
//		
//		BaseSteps.Waits.waitUntilPageLoadCompleteLongWait();
//		if(null!=PreLoginBtn) {
//			BaseSteps.Waits.waitForElementVisibilityLongWait(PreLoginBtn);
//			BaseSteps.Clicks.clickElement(PreLoginBtn);	
//		}
//
//		waitUntilApplicationPageLoads(userNameInputBox, passwordInputBox, loginBtn);
//		BaseSteps.SendKeys.sendKey(userNameInputBox, EncryptionUtils.decode(username));
//		BaseSteps.SendKeys.sendKey(passwordInputBox, EncryptionUtils.decode(password));
//		BaseSteps.Clicks.clickElement(loginBtn);
//
//	}

//	/**
//	 * Method Description: The purpose of this method is to login Humboldt page
//	 * 
//	 * @param appName
//	 */
//	public void singInToHumboldtApplication(String appName) {
//
//		Reporting.logReporter(Status.INFO, "STEP===> Perform Login into " + appName + "application.");
//		JSONObject userAccess = userAccessJsonFile.getJSONObject(SystemProperties.EXECUTION_ENVIRONMENT);
//
//		String username = ReadJSON.getString(userAccess, appName + "_username");
//		String password = ReadJSON.getString(userAccess, appName + "_password");
//
//		LoginPage LoginPage = new LoginPage();
//		waitUntilHumboldtPageLoads();
//		BaseSteps.SendKeys.sendKey(LoginPage.SSO_userNameInputBox, EncryptionUtils.decode(username));
//		BaseSteps.SendKeys.sendKey(LoginPage.SSO_passwordInputBox, EncryptionUtils.decode(password));
//
//		BaseSteps.Clicks.clickElement(LoginPage.HUMBOLDT_LoginBtn);
//
//	}

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

	public void logoutApp(String appName) {

		BLIFPage BLIFPage = new BLIFPage();
		RRWPage RRWPage = new RRWPage();
		REXPage REXPage = new REXPage();
		ARTPage ARTPage = new ARTPage();
		IVSPage IVSPage = new IVSPage();
		CONTRACTSUITEPage CONTRACTSUITEPage = new CONTRACTSUITEPage();
		TLCPage TLCPage = new TLCPage();
		HUMBOLDTPage HUMBOLDTPage = new HUMBOLDTPage();
		OSTPage OSTPage = new OSTPage();
		LSRPage LSRPage = new LSRPage();
		ECBPage ECBPage = new ECBPage();
		AppianPage AppianPage= new AppianPage();


		switch (appName.toUpperCase()) {
		case "VPOP-INTERNAL":
		case "BLIF":
			BaseSteps.Debugs.scrollToElement(BLIFPage.logoutBtn);
			BaseSteps.Waits.waitGeneric(2000);
			BaseSteps.Clicks.clickElement(BLIFPage.logoutBtn);
			break;

		case "REX":
			WebDriverSteps.getWebDriverSession().switchTo().frame(REXPage.headerFrame);
			BaseSteps.Debugs.scrollToElement(REXPage.logoutBtn);
			BaseSteps.Waits.waitGeneric(2000);
			BaseSteps.Clicks.clickElement(REXPage.logoutBtn);
			break;

		case "ART":
			BaseSteps.Clicks.clickElement(ARTPage.profileDropDown);
			BaseSteps.Clicks.clickElement(ARTPage.singoutBtn);
			BaseSteps.Waits.waitGeneric(2000);
			break;

		case "IVS2-External":
			BaseSteps.Clicks.clickElement(IVSPage.logoutBtn);
			BaseSteps.Waits.waitGeneric(2000);
			break;

		case "IVS2-Internal":
			BaseSteps.Waits.waitForElementToBeClickableLongWait(IVSPage.logoutBtn);
			BaseSteps.Waits.waitGeneric(2000);
			break;

		case "CONTRACT-SUITE":
			BaseSteps.Waits.waitForElementToBeClickableLongWait(CONTRACTSUITEPage.logOutBtn);
			BaseSteps.Clicks.clickElement(RRWPage.logoutBtn);
			BaseSteps.Waits.waitGeneric(2000);
			break;

		case "LEGACY-IVS":
			BaseSteps.Waits.waitForElementToBeClickableLongWait(IVSPage.logOutBtn_LegacyIVS);
			BaseSteps.Clicks.clickElement(IVSPage.logOutBtn_LegacyIVS);
			BaseSteps.Waits.waitGeneric(2000);
			break;

		case "TLC":
			BaseSteps.Waits.waitForElementToBeClickableLongWait(TLCPage.logoutBtn);
			BaseSteps.Clicks.clickElement(TLCPage.logoutBtn);
			BaseSteps.Waits.waitGeneric(2000);
			break;

		case "HUMBOLDT":
			BaseSteps.Waits.waitForElementToBeClickableLongWait(HUMBOLDTPage.logOutBtn);
			BaseSteps.Clicks.clickElement(HUMBOLDTPage.logOutBtn);
			BaseSteps.Waits.waitGeneric(2000);
			break;

		case "OST":
			BaseSteps.Waits.waitForElementToBeClickableLongWait(OSTPage.logOutBtn);
			BaseSteps.Clicks.clickElement(OSTPage.logOutBtn);
			BaseSteps.Waits.waitGeneric(2000);
			break;

		case "LSR":
			BaseSteps.Waits.waitForElementToBeClickableLongWait(LSRPage.logOutBtn);
			BaseSteps.Clicks.clickElement(LSRPage.logOutBtn);
			BaseSteps.Waits.waitGeneric(2000);
			break;

		case "RRW":
			BaseSteps.Waits.waitForElementToBeClickableLongWait(RRWPage.backBtn);
			BaseSteps.Clicks.clickElement(RRWPage.backBtn);
			BaseSteps.Waits.waitForElementToBeClickableLongWait(RRWPage.logoutBtn);
			BaseSteps.Clicks.clickElement(RRWPage.logoutBtn);
			break;
		case "ECB":
			BaseSteps.Waits.waitForElementToBeClickableLongWait(ECBPage.ECB_logout);
			BaseSteps.Clicks.clickElement(ECBPage.ECB_logout);
			BaseSteps.Waits.waitGeneric(2000);
			break;
			
		case "APPIAN":
			BaseSteps.Waits.waitForElementToBeClickableLongWait(AppianPage.APPIAN_logout);
			BaseSteps.Clicks.clickElement(AppianPage.APPIAN_logout);
			BaseSteps.Waits.waitGeneric(2000);
			break;
		}
		//WebDriverSession.getWebDriverSession().manage().deleteAllCookies();
	//	Waits.waitGeneric(2);
	//	WebDriverSession.getWebDriverSession().navigate().refresh();
		Validate.takeStepScreenShot("Logout performed");
		Reporting.logReporter(Status.INFO, "User logged out from " + appName + " successfully.");
	}

}
