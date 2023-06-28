package com.telus.bped.steps;

import com.telus.bped.pages.ORDERINQUIRYPage;
import com.test.reporting.Reporting;
import com.test.ui.actions.BaseSteps;
import com.test.ui.actions.Validate;
import com.test.utils.Status;
import org.junit.Assert;


/**
 * ***************************************************************************
 * DESCRIPTION: Support for ORDERINQUIRY page Steps(common) AUTHOR: x245310
 * ***************************************************************************
 */

public class ORDERINQUIRYPageSteps extends BaseSteps {


    ORDERINQUIRYPage ORDERINQUIRYPage = new ORDERINQUIRYPage();

    public void verifyOrderInquiryHomePage() {

        try {
            boolean actualRes = ORDERINQUIRYPage.isOrderInquiryHomepageDisplayed();
            Validate.assertEquals(actualRes, true, "HomePage is not displayed", false);
            Reporting.logReporter(Status.INFO, "Order Inquiry Homepage is displayed");
        } catch (Exception e) {
            Assert.fail("Order Inquiry Homepage is Not displayed " + e);
        }

    }

    /**
     * To Do: This Method is used to perform Search from OrderInquiry
     */
    public void performSearchFromOrderInquiry(String orderNumber) {


        ORDERINQUIRYPage.setOrderNumberSearchBox(orderNumber);
        ORDERINQUIRYPage.clickSearchButton();

//        ORDERINQUIRYPage.isSearchResultDisplayed();
        Validate.assertTrue(ORDERINQUIRYPage.isSearchResultDisplayed(),"No Records Displayed",false,"Records Found.");

    }

}
