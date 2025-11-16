package com.insuranceAssist.stepDefinitions.ui.client;

import com.insuranceAssist.pages.ClientDashboardPage;
import com.insuranceAssist.pages.ClientNewClaimPage;
import com.insuranceAssist.pages.ClientOverviewPage;
import com.insuranceAssist.pages.LoginPage;
import com.insuranceAssist.utils.DriverSetup;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class clientOverview {
    private WebDriver driver;
    private LoginPage lp;
    private ClientDashboardPage dashboardPage;
    private ClientOverviewPage overviewPage;


    public clientOverview() {
        this.driver = DriverSetup.getDriver();
        this.lp = new LoginPage(driver);
    }

    @Given("I am on the Client Overview Page with username {string} and password {string}")
    public void i_am_on_the_client_overview_page(String loginUsername, String loginPassword) {
        driver.get("http://localhost:4200/auth");
        lp.selectRole("Client");
        lp.inputUsername(loginUsername);
        lp.inputPassword(loginPassword);
        dashboardPage = lp.clientClickLoginButton();
        overviewPage = dashboardPage.clickOverviewPage();
    }

    @When("I search for client {string}")
    public void i_search_for_client(String username) {

    }

    @Then("I should see the username {string}")
    public void i_should_see_the_username(String expectedUsername) {
        String actualUsername = overviewPage.getUsername();
        Assert.assertEquals(actualUsername, expectedUsername, "Username does not match!");
    }

    @And("I should see the remaining coverage {string}")
    public void i_should_see_the_remaining_coverage(String expectedCoverage) {
        String actualCoverage = overviewPage.getRemainingCoverage();
        Assert.assertEquals(actualCoverage, expectedCoverage, "Remaining coverage does not match!");
    }

    @And("I should see the policy tier {string}")
    public void i_should_see_the_policy_tier(String expectedTier) {
        String actualTier = overviewPage.getPolicyType();
        Assert.assertEquals(actualTier, expectedTier, "Policy tier does not match!");
    }

    @And("close the browser after verification")
    public void close_the_browser(){
        driver.close();
    }
}
