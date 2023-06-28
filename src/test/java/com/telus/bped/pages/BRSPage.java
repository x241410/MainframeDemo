package com.telus.bped.pages;

import com.test.ui.actions.BaseSteps;
import com.test.ui.actions.WebDriverSession;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BRSPage extends WebDriverSession {
    String searchPilotValue = BRSPage.class.getSimpleName() + "_BPED_HealthCheck";

    public BRSPage() {
        PageFactory.initElements(getWebDriverSession(), this);
    }

    @FindBy(xpath = "//*[@name='account_type_radio' and @value='pilot_radio_button']")
    public WebElement PilotNumberRadioButton;

    @FindBy(name = "pilot_no")
    public WebElement PilotNumberInputBox;

    @FindBy(name = "Submit")
    public WebElement submitButton;
    @FindBy(xpath = "//pre")
    public WebElement pilotNumberSearchResult;

    public void clickPilotRadioButton() {
        BaseSteps.Waits.waitForElementVisibilityLongWait(PilotNumberRadioButton);
        PilotNumberRadioButton.click();
    }

    public void typePilotNumber() {
        BaseSteps.Waits.waitForElementVisibilityLongWait(PilotNumberInputBox);
        PilotNumberInputBox.sendKeys(searchPilotValue);
    }

    public void clickSubmitButton() {
        BaseSteps.Waits.waitForElementVisibilityLongWait(submitButton);
        submitButton.click();
    }


    public String getPilotNumberSearchResult() {
        getWebDriverSession().switchTo().defaultContent();
        getWebDriverSession().switchTo().frame("under_top");
        BaseSteps.Waits.waitForElementVisibilityLongWait(pilotNumberSearchResult);
        return pilotNumberSearchResult.getText();
    }

    public boolean isHomeScreenDisplayed(){
        getWebDriverSession().switchTo().frame("top");
        BaseSteps.Waits.waitForElementVisibilityLongWait(PilotNumberRadioButton);
        return PilotNumberRadioButton.isDisplayed();
    }
}
