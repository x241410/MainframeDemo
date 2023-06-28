package com.telus.bped.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

import com.test.ui.actions.BaseSteps;
import com.test.ui.actions.WebDriverSession;

public class ORDERINQUIRYPage extends WebDriverSession {

    public ORDERINQUIRYPage() {

        PageFactory.initElements(getWebDriverSession(), this);

    }


    @FindBy(xpath = "//p[contains(text(), 'CRIS-SS Order Inquiry')]")
    public WebElement homepageLogo;

    @FindBy(name = "txtOrderNumber")
    public WebElement orderNumberSearchBox;

    @FindBy(name = "Submit")
    public WebElement searchButton;

    @FindBy(xpath = "//*[contains(text(), 'Effective Date')]")
    public WebElement effectiveDateColumm;



    @FindBy(xpath = "//a[contains(text(),'Signout')]")
    public WebElement singoutBtn;




    public boolean isOrderInquiryHomepageDisplayed(){

        try {
            BaseSteps.Waits.waitForElementVisibilityLongWait(homepageLogo);
            return homepageLogo.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void setOrderNumberSearchBox(String orderNumber){

            BaseSteps.Waits.waitForElementVisibilityLongWait(orderNumberSearchBox);
            BaseSteps.SendKeys.clearFieldAndSendKeys(orderNumberSearchBox, orderNumber);


    }
    public void clickSearchButton(){

        BaseSteps.Waits.waitForElementVisibilityLongWait(searchButton);
        BaseSteps.Clicks.clickElement(searchButton);

    }
    public boolean isSearchResultDisplayed() {
        try {
            BaseSteps.Waits.waitForElementVisibilityLongWait(effectiveDateColumm);
            if (effectiveDateColumm.isDisplayed())
                return true;
            else
                return false;
        } catch (Exception e) {
            return false;
        }
    }
}
