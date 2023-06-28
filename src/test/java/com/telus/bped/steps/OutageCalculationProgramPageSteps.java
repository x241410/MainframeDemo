package com.telus.bped.steps;

import com.telus.bped.pages.OutageCalculationProgramPage;
import com.test.reporting.Reporting;
import com.test.ui.actions.BaseSteps;
import com.test.ui.actions.Validate;
import com.test.utils.Status;
import org.apache.commons.lang.StringUtils;

public class OutageCalculationProgramPageSteps extends BaseSteps {
    String ticketId="test_OCP_BPED_health";
    OutageCalculationProgramPage outageCalculationProgramPage=new OutageCalculationProgramPage();
    public void verifyOCP1HomePage() {
        Reporting.logReporter(Status.INFO, "OCP1 Homepage is displayed");
        Validate.assertEquals(outageCalculationProgramPage.isOCP1PageLoaded(), true, "OCP1 HomePage is  displayed", false);
    }

    public void verifyOCP2HomePage() {
        Reporting.logReporter(Status.INFO, "OCP2 Homepage is displayed");
        Validate.assertEquals(outageCalculationProgramPage.isOCP2PageLoaded(), true, "OCP2 HomePage is  displayed", false);
    }

    public void searchOCP1OutageInformation() {
        Reporting.logReporter(Status.INFO, "OCP1 search outage information");
        outageCalculationProgramPage.clickOutageInformation();
        outageCalculationProgramPage.typeTextTroubleTicketIdSearchBox(ticketId);
        outageCalculationProgramPage.clickTroubleTicketIdSearchButton();
    }

    public void verifyOCP1SearchOutageInformation() {
        Reporting.logReporter(Status.INFO, "verify: OCP1 search result of outage information");
        Validate.assertEquals(outageCalculationProgramPage.getServiceTagResultsBoxText(), " No Trouble Tickets like "+ ticketId+" found", "verify: OCP1 search result of outage information", false);
    }

    public void LoggedInOCP2ProdDataBase(){
        outageCalculationProgramPage.selectProductionDatabase();
        outageCalculationProgramPage.clickOCP2Login();
    }
    public void navigateToMonthlyRollUp(){
        outageCalculationProgramPage.clickMonthlyRollUps();
        outageCalculationProgramPage.clickAvailableOptions();
    }

    public void verifyOCP2MonthlyRollUp() {
        Reporting.logReporter(Status.INFO, "verify: OCP2 Monthly RollUp");
        String monthText=outageCalculationProgramPage.getOCP2SelectedMonthText();
        String yearText=outageCalculationProgramPage.getOCP2SelectedYearText();
        Validate.assertEquals(isStringNotEmptyAndNumeric(monthText), true, "verify: OCP2 Month has numeric value", false);
        Validate.assertEquals(isStringNotEmptyAndNumeric(yearText), true, "verify: OCP2 Year has numeric value", false);
    }
    private boolean isStringNotEmptyAndNumeric(String text){
        return  !StringUtils.isEmpty(text) && StringUtils.isNumeric(text);
    }
    }
