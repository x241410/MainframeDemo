package com.telus.bped.pages;

import com.test.ui.actions.BaseSteps;
import com.test.ui.actions.Validate;
import com.test.ui.actions.WebDriverSession;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class XMLGatewayPage extends WebDriverSession {

    public XMLGatewayPage() {
        PageFactory.initElements(getWebDriverSession(), this);
    }
    @FindBy(xpath = "//h1")
    public WebElement processTicket;

    @FindBy(xpath = "//p")
    public WebElement axisService;

    @FindBy(xpath = "//i")
    public WebElement formInvokingMessage;

    public String getProcessTicketText(){

        try {
            BaseSteps.Waits.waitForElementVisibilityLongWait(processTicket);
           return processTicket.getText();
        } catch (Exception e) {
           System.out.println(e.fillInStackTrace());
           return "";
        }
    }

    public String getAxisServiceText(){

        try {
            BaseSteps.Waits.waitForElementVisibilityLongWait(axisService);
            return axisService.getText();
        } catch (Exception e) {
            System.out.println(e.fillInStackTrace());
            return "";
        }
    }

    public String getFormInvokingMessageText(){

        try {
            BaseSteps.Waits.waitForElementVisibilityLongWait(formInvokingMessage);
            return formInvokingMessage.getText();
        } catch (Exception e) {
            System.out.println(e.fillInStackTrace());
            return "";
        }
    }
}
