package com.telus.bped.steps;

import com.telus.bped.pages.CustomerFulfillmentPage;
import com.test.reporting.Reporting;
import com.test.ui.actions.BaseSteps;
import com.test.ui.actions.Validate;
import com.test.utils.Status;

public class CustomerFulfillmentPageSteps extends BaseSteps {
	CustomerFulfillmentPage customerFulfillmentPage = new CustomerFulfillmentPage();

	public void verifyHomePage() {
		customerFulfillmentPage.isLogInSucceed();
	}

	public void verifyProductTypeloadedAndDBworking() {
		customerFulfillmentPage.isProductTypeLoaded();
	}
}
