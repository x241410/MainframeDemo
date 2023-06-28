package com.telus.bped.steps;

import com.telus.bped.pages.TLCPage;
import com.telus.bped.pages.XMLGatewayPage;
import com.test.reporting.Reporting;
import com.test.ui.actions.BaseSteps;
import com.test.ui.actions.Validate;
import com.test.utils.Status;
import org.junit.Assert;

public class XMLGatewayPageSteps  extends BaseSteps {

    /**
     * To Do: This Method is used to verify home page of XML Gateway Application
     *
     */
    XMLGatewayPage xMLGatewayPage=new XMLGatewayPage();

    public void verifyXMLGatewayApplicationHomePage() {

        try {
            String processTicketText = xMLGatewayPage.getProcessTicketText();
            String axisServiceText = xMLGatewayPage.getAxisServiceText();
            String formInvokingMessageText = xMLGatewayPage.getFormInvokingMessageText();

            Reporting.logReporter(Status.INFO, "verify XM LGateway Application HomePage is displayed");
            Validate.assertEquals(processTicketText, "ProcessTicketPort", "ProcessTicketPort is displayed", false);
            Validate.assertEquals(axisServiceText, "Hi there, this is an AXIS service!", "Axis service displayed", false);
            Validate.assertEquals(formInvokingMessageText, "Perhaps there will be a form for invoking the service here...", "formInvokingMessageText is displayed", false);

        }catch (Exception e) {
            Assert.fail("TLC Homepage is Not displayed "+e);
        }

    }
}
