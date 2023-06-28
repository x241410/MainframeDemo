package com.telus.bped.steps;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.telus.bped.pages.LynxPage;
import com.test.reporting.Reporting;
import com.test.ui.actions.BaseSteps;
import com.test.ui.actions.Validate;
import com.test.ui.actions.WebDriverSteps;
import com.test.utils.Status;

/**
 ****************************************************************************
 * DESCRIPTION: Support for BLIF page Steps(common) AUTHOR: x241410
 ****************************************************************************
 */

public class LynxPageSteps extends BaseSteps {

	/**
	 * To Do: This Method is used to verify home page of BLIF Application
	 * 
	 */
	LynxPage LynxPage = new LynxPage();

	public void verifyHomePage() {

		boolean actualRes = LynxPage.isHomePageTextDisplayed();
		Validate.assertEquals(actualRes, true, "LYNX HomePage is not displayed", false);
		Reporting.logReporter(Status.INFO, "LYNX Homepage is displayed");
	}

	public void navigateToRespectiveTabs(String tabName) {

		// BaseSteps.Waits.waitForElementVisibilityLongWait(LynxPage.navigateToRespectiveTabs(tabName));
		LynxPage.navigateToRespectiveTabs(tabName).click();
		Reporting.logReporter(Status.INFO, "Trouble Tickets Page is displayed");
	}

	/**
	 * To Do: This Method is used to perform Search from BLIF Application
	 * 
	 * @param Blif Phone Number
	 *//*
		 * 
		 * public void performSearchFromBlif() {
		 * 
		 * BaseSteps.Clicks.clickElement(BLIFPage.searchLink);
		 * BaseSteps.Waits.waitForElementVisibilityLongWait(BLIFPage.searchButton);
		 * BaseSteps.Clicks.clickElement(BLIFPage.searchButton);
		 * BLIFPage.isSearchResultDisplayed(); boolean noSearchResultDisplayedStatus =
		 * BLIFPage.isNoDataAvailableLabelDisplayed(); if
		 * (noSearchResultDisplayedStatus) {
		 * BaseSteps.SendKeys.clearFieldAndSendKeys(BLIFPage.phoneNumSearchTextBox, "");
		 * BaseSteps.Clicks.clickElement(BLIFPage.searchButton);
		 * BaseSteps.Waits.waitForElementVisibility(BLIFPage.numberOfRecords, 150); }
		 * 
		 * Validate.takeStepFullScreenShot("Search Results", Status.INFO); }
		 */

	public void printFramesOnTheWebPage() {
		// Assume driver is initialized properly.
		List<WebElement> ele = WebDriverSteps.getWebDriverSession().findElements(By.tagName("frame"));
		Reporting.logReporter(Status.INFO, "Number of frames in a page :" + ele.size());
		for (WebElement el : ele) {
			// Returns the Id of a frame.
			Reporting.logReporter(Status.INFO, "Frame Id :" + el.getAttribute("id"));
			// Returns the Name of a frame.
			Reporting.logReporter(Status.INFO, "Frame name :" + el.getAttribute("name"));
		}
	}
	
	public void printAllElementsByTagName(String tagName) {
		// Assume driver is initialized properly.
		List<WebElement> ele = WebDriverSteps.getWebDriverSession().findElements(By.tagName(tagName));
		Reporting.logReporter(Status.INFO, "Number of Elements in a page :" + ele.size());
		for (WebElement el : ele) {
			// Returns the Id of a frame.
			Reporting.logReporter(Status.INFO, "Element Id :" + el.getAttribute("id"));
			// Returns the Name of a frame.
			Reporting.logReporter(Status.INFO, "Element name :" + el.getAttribute("name"));
		}
	}

}
