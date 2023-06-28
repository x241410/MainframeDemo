package com.telus.bped.steps;

import com.telus.bped.pages.WFMAPage;
import com.test.ui.actions.BaseSteps;

public class WFMAPageSteps extends BaseSteps {
	WFMAPage wfmaPage = new WFMAPage();

	public void verifyHomePage() {
		wfmaPage.isLogInSucceed();
	}

	public void verifyMessagingBridge() {
		wfmaPage.verifyMessagingBridge();
	}
}
