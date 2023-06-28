package com.telus.bped.steps;

import org.junit.Assert;

import com.telus.bped.pages.LDORSPage;
import com.test.reporting.Reporting;
import com.test.ui.actions.BaseSteps;
import com.test.ui.actions.Validate;
import com.test.ui.actions.WebDriverSession;
import com.test.ui.actions.WebDriverSteps;
import com.test.utils.Status;
import com.telus.bped.pages.MileageCalculatorPage;

	/**
	 ****************************************************************************
	 * DESCRIPTION: Support for Mileage Calculator page Steps(common) AUTHOR: x258025
	 ****************************************************************************
	 */

	public class MileageCalculatorPageSteps extends BaseSteps {
	
		MileageCalculatorPage MileageCalculatorPage = new MileageCalculatorPage();
		
		public void launchMileageCalculator() {
			
			WebDriverSession.getWebDriverSession().get("http://btln009652:8080/orderinq/MileCalc");
			Validate.takeStepScreenShot(" Mileage Calculator");
		}
			
			/**
			 * To Do: This Method is used to verify homepage of Mileage Calculator Application
			 * 
			 */
			
		public void verifyHomePage() {
			
			//WebDriverSteps.openApplication(applicationName);
	        boolean actualRes = MileageCalculatorPage.isHomepageDisplayed();
	        Validate.assertEquals(actualRes, true, "HomePage is not displayed", false);
	        Reporting.logReporter(Status.INFO, "Mileage Calculator Homepage is displayed");
			}
		
		/**
		 * To Do: This Method is used to perform Search from Mileage Calculator Application
		 * 
		 */
		public void performSearchFromMileageCalculator() {
			
			BaseSteps.SendKeys.sendKey(MileageCalculatorPage.CalculationNameTxt, "Honorio");
			BaseSteps.Clicks.clickElement(MileageCalculatorPage.RetrieveBtn);
	 
		}

		public void verifySearchMileageCalculator() {

			BaseSteps.Waits.waitForElementVisibilityLongWait(MileageCalculatorPage.fromNPA);
			BaseSteps.Waits.waitGeneric(5000);
			String fromNPA=MileageCalculatorPage.fromNPA.getAttribute("value");
			String toNPA=MileageCalculatorPage.toNPA.getAttribute("value");
			String fromNXX=MileageCalculatorPage.fromNXX.getAttribute("value");
			String toNXX=MileageCalculatorPage.toNXX.getAttribute("value");
			String fromCity=MileageCalculatorPage.fromCityTown.getAttribute("value");
			String toCity=MileageCalculatorPage.toCityProv0.getAttribute("value");

			Validate.assertEquals(isTextEmptyOrNull(fromNPA),true, "FromNPA got loaded", false);
			Validate.assertEquals(isTextEmptyOrNull(toNPA),  true, "toNPA got loaded", false);
			Validate.assertEquals(isTextEmptyOrNull(fromNXX),true, "fromNXX got loaded", false);
			Validate.assertEquals(isTextEmptyOrNull(toNXX),true, "toNXX got loaded", false);
			Validate.assertEquals(isTextEmptyOrNull(fromCity),true, "fromCity got loaded", false);
			Validate.assertEquals(isTextEmptyOrNull(toCity),true, "toCity got loaded", false);

			/**
			 * To Do: Need to Verify after app issue resolve
			 *
			 */

		}
		private boolean isTextEmptyOrNull(String text){
			if(null==text||text.isEmpty()){
				return false;
			}else{
				return true;
			}
		}

	}
