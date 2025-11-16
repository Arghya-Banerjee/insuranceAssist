package com.insuranceAssist.stepDefinitions.ui.client;

import com.insuranceAssist.pages.ClientDashboardPage;
import com.insuranceAssist.pages.LoginPage;
import com.insuranceAssist.utils.DriverSetup;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class ClientLogin {

    WebDriver driver;

    LoginPage loginPage;
    ClientDashboardPage clientDashboardPage;

    @Given("Open browser")
    public void open_browser() {
        driver = DriverSetup.getDriver();
        driver.manage().window().maximize();
        loginPage = new LoginPage(driver);
    }
    @Then("Navigate to insuranceAssist URL")
    public void navigate_to_insurance_assist_url() {
        driver.get("http://localhost:4200");
    }
    @Then("Select role {string}")
    public void select(String string) {
        loginPage.selectRole(string);
    }
    @Then("Input username {string}")
    public void input(String string) {
        loginPage.inputUsername(string);
    }
    @Then("Input password {string}")
    public void inputPassword(String string){
        loginPage.inputPassword(string);
    }
    @Then("Click on login button")
    public void click_on_login_button() {
        clientDashboardPage = loginPage.clientClickLoginButton();
    }
    @Then("Verify if client landed on dashboard page with username {string}")
    public void verify_if_client_landed_on_dashboard_page(String string) throws InterruptedException {
        Assert.assertEquals(clientDashboardPage.getDashboardUsernameText(), "@"+string);
    }
    @Then("Logout client")
    public void logout_client() {
        loginPage = clientDashboardPage.clickLogoutButton();
    }
    @Then("Close browser")
    public void close_browser() {
        driver.quit();
    }

}
