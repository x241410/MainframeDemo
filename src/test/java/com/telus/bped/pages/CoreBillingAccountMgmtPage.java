package com.telus.bped.pages;

import com.test.ui.actions.BaseSteps;
import com.test.ui.actions.WebDriverSession;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CoreBillingAccountMgmtPage extends WebDriverSession {
    int toAppear=1;
    public CoreBillingAccountMgmtPage() {
        PageFactory.initElements(getWebDriverSession(), this);
    }
    @FindBy(xpath = "//input[@value='Use single sign-on (SSO)']")
    public WebElement ssoSignOnButton;

    @FindBy(xpath = "//*[@uitestid='gwt-debug-filterSelector']")
    public WebElement filterSelector;

    @FindBy(xpath = "//*[@uitestid='gwt-debug-all']")
    public WebElement selectAll;

    @FindBy(xpath = "//*[@uitestid='gwt-debug-hamburger']")
    public WebElement menuIcon;

    @FindBy(xpath = "//*[@uitestid='gwt-debug-searchInput']")
    public WebElement menuSearchBar;

    @FindBy(xpath = "//div[text()='Services']")
    public WebElement services;

    @FindBy(xpath = "//*[@aria-label='Collapse navigation bar']")
    public WebElement backArrow;

    @FindBy(id = "dt-filter-field-0")
    public WebElement fileterProject;

    @FindBy(xpath = "//h2")
    public WebElement header2;

    @FindBy(xpath = "//span[text()='/CoreAccountManagmentAPI_v1_0--IMSAccMgnt']")
    public List<WebElement> selectProject;

    @FindBy(xpath = "(//div[text()='Failure rate']/following::div/span[@uitestid='gwt-debug-value'])[1]")
    public WebElement failureRateValue;

    public void clickSSOsignOnButton(){
        BaseSteps.Waits.waitForElementVisibilityLongWait(ssoSignOnButton);
        ssoSignOnButton.click();
    }
    public void clickFilterSelector(){
        BaseSteps.Waits.waitForElementVisibilityLongWait(filterSelector);
        filterSelector.click();
    }

    public void clickAll(){
        BaseSteps.Waits.waitForElementVisibilityLongWait(selectAll);
        selectAll.click();
    }


    public void clickMenuIcon(){
        BaseSteps.Waits.waitForElementVisibilityLongWait(menuIcon);
        if(!menuSearchBar.isDisplayed())
           menuIcon.click();
    }
    public void searchServices(){
        BaseSteps.Waits.waitForElementVisibilityLongWait(menuSearchBar);
        menuSearchBar.sendKeys("services");
    }
    public void clickServices(){
        BaseSteps.Waits.waitForElementVisibilityLongWait(services);
        services.click();
        BaseSteps.Waits.waitUntilPageLoadCompleteLongWait();
        BaseSteps.Waits.waitForAjaxLoaderSpinnerInvisibility(toAppear,10);
    }
    public void clickBackArrow(){
        BaseSteps.Waits.waitForElementVisibilityLongWait(backArrow);
        backArrow.click();
    }

    public void filterProject(){
        BaseSteps.Waits.waitForElementVisibilityLongWait(fileterProject);
        fileterProject.sendKeys("CoreAccountManagmentAPI_v1_0");
        fileterProject.sendKeys(Keys.RETURN);
        BaseSteps.Waits.waitUntilPageLoadCompleteLongWait();
        BaseSteps.Waits.waitGeneric(2*1000);
        BaseSteps.Waits.waitForAjaxLoaderSpinnerInvisibility(toAppear,10);
        clickBackArrow();
        header2.click();
    }
    public int getNumberOfProjectListed(){
        BaseSteps.Waits.waitForAjaxLoaderSpinnerInvisibility(toAppear,10);
        BaseSteps.Waits.waitForElementVisibilityLongWait(selectProject.get(0));
        return selectProject.size();
    }
    public void selectCoreAccountManagemetApi(int index){
        BaseSteps.Waits.waitForElementVisibilityLongWait(selectProject.get(index));
        BaseSteps.Waits.waitForAjaxLoaderSpinnerInvisibility(toAppear,10);
        selectProject.get(index).click();
    }
    public String getFailureRateValue(){
        BaseSteps.Waits.waitForAjaxLoaderSpinnerInvisibility(toAppear,10);
        BaseSteps.Waits.waitForElementVisibilityLongWait(failureRateValue);
        return failureRateValue.getText();
    }
    public void clickBrowserBackButton(){
        getWebDriverSession().navigate().back();
        BaseSteps.Waits.waitUntilPageLoadComplete();
        BaseSteps.Waits.waitForAjaxLoaderSpinnerInvisibility(toAppear,10);

    }
    public boolean verifyLoginSucceeded(){
        BaseSteps.Waits.waitUntilPageLoadComplete();
        BaseSteps.Waits.waitForAjaxLoaderSpinnerInvisibility(toAppear,10);
        BaseSteps.Waits.waitForElementVisibilityLongWait(filterSelector);
        return filterSelector.isDisplayed();
    }
}
