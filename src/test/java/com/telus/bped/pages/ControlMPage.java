package com.telus.bped.pages;

import com.test.ui.actions.WebDriverSession;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ControlMPage extends WebDriverSession {



    public ControlMPage(){
        PageFactory.initElements(getWebDriverSession(), this);
    }

    @FindBy(id="SelfServiceDomainTabHeader")
    public WebElement monitoringTab;

    @FindBy(id = "searchJobsPanelInput")
    public WebElement searchJobsInput;
    @FindBy(id = "searchJobsPanelSubmit")
    public WebElement searchJobButton;

    @FindBy(xpath ="(//*[@col-id='JobStatus'])[2]//span")
    public WebElement jobStatus;
    @FindBy(xpath = "(//div[@class= 'ag-header-cell-resize' and @ref='eResize' and @role='presentation'])[2]")
    public WebElement resizeStatus;
    @FindBy(xpath = "(//div[@class= 'ag-header-cell-resize' and @ref='eResize' and @role='presentation'])[1]")
    public WebElement resizeName;

    public boolean isMonitoringLinkDisplayed() {
        try {
            if (monitoringTab.isDisplayed())
                return true;
            else
                return false;
        } catch (Exception e) {
            return false;
        }
    }
    public boolean isSearchDone() {
        try {
            if (jobStatus.isDisplayed())
                return true;
            else
                return false;
        } catch (Exception e) {
            return false;
        }
    }




}
