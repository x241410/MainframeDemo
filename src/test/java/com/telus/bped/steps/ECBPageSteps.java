package com.telus.bped.steps;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.comparator.LastModifiedFileComparator;
import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.telus.bped.pages.BLIFPage;
import com.telus.bped.pages.ECBPage;
import com.test.reporting.Reporting;
import com.test.ui.actions.BaseSteps;
import com.test.ui.actions.Validate;
import com.test.ui.actions.WebDriverSession;
import com.test.utils.Status;
import com.test.utils.SystemProperties;

/**
 ****************************************************************************
 * DESCRIPTION: Support for ECB page Steps(common) AUTHOR: T970222
 ****************************************************************************
 */

public class ECBPageSteps {
	String title = "Citrix XenApp - Applications";
	ECBPage ECBPage = new ECBPage();

	/**
	 * To Do: This Method is used to verify home page of ECB Application
	 * 
	 */
	public void verifyHomePage(String appName) {
		BaseSteps.Waits.waitUntilPageLoadCompleteLongWait();
		String pageTitle = WebDriverSession.getWebDriverSession().getTitle();
		Validate.takeStepFullScreenShot("Search Results", Status.INFO);
		Validate.assertEquals(pageTitle, title, appName + " logged in successfully", false);
		Validate.assertTrue(ECBPage.ECB_loggedOnAs.isDisplayed(), appName + " logged in successfully");
		Validate.assertTrue(ECBPage.ECB_viewButton.isDisplayed(), appName + " logged in successfully");
		Reporting.logReporter(Status.INFO, appName + " Homepage is displayed");
	}

	/**
	 * To Do: This Method is used to check link downloadable from ECB
	 * @throws IOException 
	 * 
	 */
	public void verifyLinkIsDownloadable(String appName) {
		HashMap<String, Boolean> downlaodStatus = new HashMap<String, Boolean>();
		//String tempFile=System.getProperty("java.io.tmpdir");
		String tempFile = SystemProperties.DRIVER_AUTO_DOWNLOAD_PATH;
		File fileDownlaodLocation = new File(tempFile);
		BaseSteps.Finds.findElement(By.xpath("//*[@alt='" + appName + "']/..")).click();
		BaseSteps.Waits.waitGeneric(4000);

		String file = String.valueOf(getTheNewestFile(String.valueOf(fileDownlaodLocation), "ica"));

		try {
			Process process = Runtime.getRuntime().exec(new String[]{"cmd.exe", "/c", file});
			System.out.println(file);
		} catch (Exception ex) {
		}

	}

	public File getTheNewestFile(String filePath, String ext) {
		File theNewestFile = null;
		File dir = new File(filePath);
		FileFilter fileFilter = new WildcardFileFilter("*." + ext);
		File[] files = dir.listFiles(fileFilter);

		if (files != null) {
			/** The newest file comes first **/
			Arrays.sort(files, LastModifiedFileComparator.LASTMODIFIED_REVERSE);
			theNewestFile = files[0];
		}

		return theNewestFile;
	}
}
