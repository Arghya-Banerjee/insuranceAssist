package com.insuranceAssist.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ClientDashboardPage {

    WebDriver driver;
    ClientNewClaimPage claimPage;
    ClientOverviewPage overviewPage;

    public ClientDashboardPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Locators

    @FindBy(xpath = "/html/body/app-root/app-layout/div/app-client-dashboard/app-client-overview/div/div[1]/div/div/div[1]/p")
    WebElement clientUsername;

    @FindBy(xpath = "/html/body/app-root/app-layout/nav/div/div/button")
    WebElement logoutButton;

    @FindBy(xpath = "//a[text() = 'New Claim']")
    WebElement newClaimButton;

    @FindBy(xpath = "//a[text() = 'Overview']")
    WebElement overViewButton;

    @FindBy(xpath = "//*[@id=\"ia-tab-history-link\"]")
    WebElement claimHistoryButton;

    // Actions

    public String getDashboardUsernameText(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(driver -> {
            WebElement element = clientUsername;
            return !element.getText().trim().isEmpty();
        });
        return clientUsername.getText();
    }

    public LoginPage clickLogoutButton(){
        logoutButton.click();
        return new LoginPage(driver);
    }

    public ClientNewClaimPage clickNewClaimPage(){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(newClaimButton)).click();
        return new ClientNewClaimPage(driver);

    }

    public ClientClaimHistoryPage clickClaimHistoryButton(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(claimHistoryButton));
        button.click();
        return new ClientClaimHistoryPage(driver);
    }

    public ClientOverviewPage clickOverviewPage(){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(overViewButton)).click();
        return new ClientOverviewPage(driver);

    }



}
