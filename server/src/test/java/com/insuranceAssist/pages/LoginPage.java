package com.insuranceAssist.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    WebDriver driver;

    public LoginPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Locators

    @FindBy(xpath = "//*[@id=\"role\"]")
    WebElement roleDropDown;

    @FindBy(xpath = "//*[@id=\"username\"]")
    WebElement usernameInput;

    @FindBy(xpath = "//*[@id=\"password\"]")
    WebElement passwordInput;

    @FindBy(xpath = "//*[@id=\"login-btn\"]")
    WebElement loginButton;

    @FindBy(xpath = "/html/body/app-root/app-auth/nav/div/div/button")
    WebElement registerButton;

    // Actions

    public void selectRole(String role){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(roleDropDown));
        Select roleSelect = new Select(roleDropDown);
        roleSelect.selectByVisibleText(role);
    }

    public void inputUsername(String username){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(usernameInput));
        usernameInput.clear();
        usernameInput.sendKeys(username);
    }

    public void inputPassword(String password){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(usernameInput));
        passwordInput.clear();
        passwordInput.sendKeys(password);
    }

    public RegisterPage clickRegisterButton(){
        registerButton.click();
        return new RegisterPage(driver);
    }

    public ClientDashboardPage clientClickLoginButton() {
        loginButton.click();

        By DASHBOARD_MARKER = By.xpath("//*[@id=\"ia-client-username\"]");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.or(
                ExpectedConditions.alertIsPresent(),
                ExpectedConditions.presenceOfElementLocated(DASHBOARD_MARKER)
        ));

        try {
            Alert alert = driver.switchTo().alert();
            String text = alert.getText();
            alert.accept();
            if (!"Invalid Credentials. Please try again.".equals(text)) {
                throw new AssertionError("Unexpected alert text: " + text);
            }
            return null;
        } catch (NoAlertPresentException ignored) {
            return new ClientDashboardPage(driver);
        }
    }


    public AgentDashboardPage agentClickLoginButton() {
        loginButton.click();

        By DASHBOARD_MARKER = By.xpath("/html/body/app-root/app-layout/div/app-agent/app-overview/div/div[1]/div/div/p[1]");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.or(
                ExpectedConditions.alertIsPresent(),
                ExpectedConditions.presenceOfElementLocated(DASHBOARD_MARKER)
        ));

        try {
            Alert alert = driver.switchTo().alert();
            String text = alert.getText();
            alert.accept();
            if (!"Invalid Credentials. Please try again.".equals(text)) {
                throw new AssertionError("Unexpected alert text: " + text);
            }
            return null;
        } catch (NoAlertPresentException ignored) {
            return new AgentDashboardPage(driver);
        }
    }

    public String getInvalidCredentialsAlertText(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        alert.accept();
        return alertText;
    }

}
