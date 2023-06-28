package com.telus.bped.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.test.ui.actions.BaseSteps;
import com.test.ui.actions.WebDriverSession;

public class OSTPage extends WebDriverSession {

	public OSTPage() {

		PageFactory.initElements(getWebDriverSession(), this);

	}
	
	@FindBy(xpath = "//img[contains(@src, 'ostLogo')]")
	public WebElement homepageLogo;
	
	@FindBy(xpath = "//a[contains(text(), 'Logout')]")
	public WebElement logOutBtn;
	
	@FindBy(xpath = "//div[contains(text(), \"What's New\")]")
	public WebElement whatNewLink;
	
	@FindBy(xpath = "//div[contains(text(), 'Search')]")
	public WebElement searchLink;
	
	@FindBy(xpath = "//div[contains(text(), 'Admin')]")
	public WebElement adminLink;
	
	@FindBy(xpath = "//div[contains(text(), 'Dashboard')]")
	public WebElement dashboardLink;
	
	@FindBy(xpath = "//div[contains(text(), 'AdminDashboard')]")
	public WebElement adminDashboardLink;
	
	
	@FindBy(xpath = "//label[contains(text(), 'Admin Dashboard')]")
	public WebElement adminDashboard;
	
	@FindBy(xpath = "//label[contains(text(), 'Order Management Dashboard')]")
	public WebElement dashboard;
	
	
	@FindBy(xpath = "//div[contains(text(), 'Update My Order Preferences')]")
	public WebElement admin;
	
	@FindBy(xpath = "//label[contains(text(), 'Search/Results Page')]")
	public WebElement search;
	
	public boolean isOSTHomepageDisplayed(){
		
		try {
			WebDriverWait wait = new WebDriverWait(WebDriverSession.getWebDriverSession(), Duration.ofSeconds(60));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[contains(@src, 'ostLogo')]")));
			return homepageLogo.isDisplayed();
		} catch (Exception e) {
			return false;
			}
		}

	public boolean isSearchPageDisplayed(){
		
		try {
			WebDriverWait wait = new WebDriverWait(WebDriverSession.getWebDriverSession(), Duration.ofSeconds(60));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[contains(text(), 'Search/Results Page')]")));
			return search.isDisplayed();
		} catch (Exception e) {
			return false;
			}
		}
	
	public boolean isAdminPageDisplayed(){
			
			try {
				WebDriverWait wait = new WebDriverWait(WebDriverSession.getWebDriverSession(), Duration.ofSeconds(60));
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(), 'Update My Order Preferences')]")));
				return admin.isDisplayed();
			} catch (Exception e) {
				return false;
				}
			}
	
	public boolean isAdminDashboardPageDisplayed(){
		
		try {
			WebDriverWait wait = new WebDriverWait(WebDriverSession.getWebDriverSession(), Duration.ofSeconds(60));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[contains(text(), 'Admin Dashboard')]")));
			return adminDashboard.isDisplayed();
		} catch (Exception e) {
			return false;
			}
		}
	
	public boolean isDashboardPageDisplayed(){
		
		try {
			WebDriverWait wait = new WebDriverWait(WebDriverSession.getWebDriverSession(), Duration.ofSeconds(60));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[contains(text(), 'Order Management Dashboard')]")));
			return dashboard.isDisplayed();
		} catch (Exception e) {
			return false;
			}
		}

}
