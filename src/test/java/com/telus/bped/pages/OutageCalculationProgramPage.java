package com.telus.bped.pages;

import com.test.ui.actions.BaseSteps;
import com.test.ui.actions.WebDriverSession;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class OutageCalculationProgramPage extends WebDriverSession {

	public OutageCalculationProgramPage() {
        PageFactory.initElements(getWebDriverSession(), this);
    }
    @FindBy(xpath = "//span[contains(text(),'TT/SVCTag/Outage Information')]")
    public WebElement outageInformation;

    @FindBy(name = "TTID")
    public WebElement troubleTicketIdSearchBox;

    @FindBy(name = "processbtn")
    public WebElement troubleTicketIdSearchButton;

    @FindBy(name = "servTags")
    public WebElement serviceTagResultsBox;

    @FindBy(name = "sDb")
    public WebElement dataBaseDropDown;

    @FindBy(name = "LOGIN")
    public WebElement OCP2_loginButton;

    @FindBy(xpath = "//a[@href=\"OCPMonthly.html\"]")
    public WebElement monthlyRollUps;

    @FindBy(xpath = "//li/a[1]")
    public WebElement availableOption;


    @FindBy(name = "boxMonth")
    public WebElement selectedMonthBox;

    @FindBy(name = "boxMonth")
    public WebElement selectedYearBox;

    @FindBy(name = "helper")
    public WebElement helperFrame;


    public boolean isOCP2PageLoaded(){
        getWebDriverSession().switchTo().frame("main");
        BaseSteps.Waits.waitForElementVisibilityLongWait(OCP2_loginButton);
        return OCP2_loginButton.isDisplayed() && dataBaseDropDown.isDisplayed() ;
    }
    public void selectProductionDatabase(){
            BaseSteps.Waits.waitForElementVisibilityLongWait(dataBaseDropDown);
            Select dbDrobdown = new Select(dataBaseDropDown);
            dbDrobdown.selectByValue("ESDPR");
    }

    public void clickOCP2Login(){
        BaseSteps.Waits.waitForElementVisibilityLongWait(OCP2_loginButton);
        OCP2_loginButton.click();
    }

    public void clickMonthlyRollUps(){
        BaseSteps.Waits.waitForElementVisibilityLongWait(monthlyRollUps);
        monthlyRollUps.click();
    }

    public void clickAvailableOptions(){
        getWebDriverSession().switchTo().defaultContent();
        getWebDriverSession().switchTo().frame("helper");
        BaseSteps.Waits.waitForElementVisibilityLongWait(availableOption);
        availableOption.click();
        getWebDriverSession().switchTo().defaultContent();
        getWebDriverSession().switchTo().frame("main");
    }

    public String getOCP2SelectedMonthText(){
        BaseSteps.Waits.waitForElementVisibilityLongWait(selectedMonthBox);
        return selectedMonthBox.getAttribute("value");
    }

    public String getOCP2SelectedYearText(){
        BaseSteps.Waits.waitForElementVisibilityLongWait(selectedYearBox);
        return selectedYearBox.getAttribute("value");
    }
    public boolean isOCP1PageLoaded(){
        BaseSteps.Waits.waitForElementVisibilityLongWait(outageInformation);
        return outageInformation.isDisplayed() ;
    }
    public void clickOutageInformation(){
        BaseSteps.Waits.waitForElementVisibilityLongWait(outageInformation);
        outageInformation.click();
    }

    public void typeTextTroubleTicketIdSearchBox(String text){
        BaseSteps.Waits.waitForElementVisibilityLongWait(troubleTicketIdSearchBox);
        troubleTicketIdSearchBox.sendKeys(text);
    }

    public void clickTroubleTicketIdSearchButton(){
        BaseSteps.Waits.waitForElementVisibilityLongWait(troubleTicketIdSearchButton);
        troubleTicketIdSearchButton.click();
    }

    public String getServiceTagResultsBoxText(){
        BaseSteps.Waits.waitForElementVisibilityLongWait(serviceTagResultsBox);
        return serviceTagResultsBox.getText();
    }

}
