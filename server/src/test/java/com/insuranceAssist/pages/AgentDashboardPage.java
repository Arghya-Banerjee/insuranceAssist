package com.insuranceAssist.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AgentDashboardPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public AgentDashboardPage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    // Locators

    @FindBy(xpath = "/html/body/app-root/app-layout/div/app-agent/app-overview/div/div[1]/div/div/p[1]")
    WebElement dashboardUsername;

    @FindBy(xpath = "/html/body/app-root/app-layout/nav/div/div/button[2]")
    WebElement logoutButton;

    @FindBy(xpath = "//*[@id=\"overview-btn\"]")
    WebElement overviewButton;

    @FindBy(xpath = "//*[@id=\"claim-approval-btn\"]")
    WebElement claimApprovalButton;

    @FindBy(xpath = "//*[@id=\"claim-history-btn\"]")
    WebElement claimHistoryButton;

    @FindBy(xpath = "//*[@id=\"network-hospital-btn\"]")
    WebElement networkHospitalButton;

    // Actions

    public String getUrl(){
        return driver.getTitle();
    }

    public String getDashboardUsernameText(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement button = wait.until(ExpectedConditions.visibilityOf(dashboardUsername));
        String fullUsernameText = button.getText();
        return fullUsernameText.substring(11);
    }

    public void clickLogoutButton(){
        wait.until(ExpectedConditions.elementToBeClickable(logoutButton)).click();
    }

    public AgentClaimHistoryPage clickClaimHistoryButton(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(claimHistoryButton));
        button.click();
        return new AgentClaimHistoryPage(driver);
    }

}
