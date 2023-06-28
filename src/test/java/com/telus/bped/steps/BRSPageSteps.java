package com.telus.bped.steps;

import com.telus.bped.pages.BRSPage;
import com.test.reporting.Reporting;
import com.test.ui.actions.Validate;
import com.test.utils.Status;

public class BRSPageSteps {
    static BRSPage brsPage =new BRSPage();

    public static void verifyLoginSucceeded(){
        Reporting.logReporter(Status.INFO, "Verify BRS logged in succeeded.");
        Validate.assertEquals(brsPage.isHomeScreenDisplayed(), true, "Verify BRS logged in succeeded", false);
    }

    public static  void searchPilotNumber(){
        brsPage.clickPilotRadioButton();
        brsPage.typePilotNumber();
        brsPage.clickSubmitButton();
    }
    public static void verifyPilotNumberSearchResult(){
        String result=brsPage.getPilotNumberSearchResult();
        Reporting.logReporter(Status.INFO, "Verify Pilot number Search result.");
        Validate.assertEquals(result.contains("Unable to find this bill."), true, "Verify Pilot number Search result", false);
        Validate.assertEquals(result.contains((brsPage.getClass().getSimpleName() + "_BPED_HealthCheck").toUpperCase()), true, "Verify Pilot number Search result", false);
        Validate.assertEquals(result.contains("No record found"), true, "Verify Pilot number Search result", false);

    }
}
