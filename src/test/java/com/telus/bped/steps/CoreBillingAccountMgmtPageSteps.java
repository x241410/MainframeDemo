package com.telus.bped.steps;

import com.telus.bped.pages.CoreBillingAccountMgmtPage;
import com.test.reporting.Reporting;
import com.test.ui.actions.BaseSteps;
import com.test.ui.actions.Validate;
import com.test.utils.Status;

public class CoreBillingAccountMgmtPageSteps {
    static CoreBillingAccountMgmtPage coreBillingAccountMgmtPage = new CoreBillingAccountMgmtPage();
    public static void clickSSO(){
        coreBillingAccountMgmtPage.clickSSOsignOnButton();
    }
    public static void resetFilter(){
        coreBillingAccountMgmtPage.clickFilterSelector();
        coreBillingAccountMgmtPage.clickAll();
    }
    public static void navigateToServices(){
        coreBillingAccountMgmtPage.clickMenuIcon();
        coreBillingAccountMgmtPage.searchServices();
        coreBillingAccountMgmtPage.clickServices();
    }
    public static void verifyCoreAccountManagementAPIFailureRate(){
        coreBillingAccountMgmtPage.filterProject();
        int numberOfProject=coreBillingAccountMgmtPage.getNumberOfProjectListed();
        for(int index=0;index<numberOfProject;index++) {
            System.out.println("select project number: ==========================================================================================> "+(index+1));
            Reporting.logReporter(Status.INFO, " select project number: "+(index+1));
            coreBillingAccountMgmtPage.selectCoreAccountManagemetApi(index);
            String failurePercentageValue = coreBillingAccountMgmtPage.getFailureRateValue();
            Reporting.logReporter(Status.INFO, " Verify failure rate of project list "+index);
            Validate.assertEquals(failurePercentageValue, "0", "Verify failure rate of project list "+index, false);
            coreBillingAccountMgmtPage.clickBrowserBackButton();
            BaseSteps.Waits.waitUntilPageLoadCompleteLongWait();
            BaseSteps.Waits.waitGeneric(10*1000);
        }
    }
    public static void verifyLoginSucceeded(){
        Reporting.logReporter(Status.INFO, "Verify Dynatrace logged in succeeded.");
        Validate.assertEquals(coreBillingAccountMgmtPage.verifyLoginSucceeded(), true, "Verify Dynatrace logged in succeeded", false);
    }

}
