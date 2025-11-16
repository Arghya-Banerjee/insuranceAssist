package com.insuranceAssist.stepDefinitions.ui.client;

import com.insuranceAssist.pages.ClientDashboardPage;
import com.insuranceAssist.pages.ClientNewClaimPage;
import com.insuranceAssist.pages.LoginPage;
import com.insuranceAssist.utils.DriverSetup;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import org.openqa.selenium.*;
import org.testng.Assert;

public class clientInsuranceClaim {

    private WebDriver driver;
    private ClientNewClaimPage claimPage;
    private LoginPage lp;
    private ClientDashboardPage dashboardPage;

    public clientInsuranceClaim() {
        this.driver = DriverSetup.getDriver();
        this.lp = new LoginPage(driver);
//        this.claimPage = new ClientNewClaimPage(driver);

    }


    @Given("I am on the Create New Claim page")
    public void i_am_on_the_page() throws Exception {

        driver.get("http://localhost:4200/auth");
        lp.selectRole("Client");
        lp.inputUsername("client1example");
        lp.inputPassword("123456");
        dashboardPage = lp.clientClickLoginButton();
        claimPage = dashboardPage.clickNewClaimPage();


    }

    @When("I select {string} as the coverage type")
    public void i_select_as_the_coverage_type(String coverageType) {
        claimPage.setClaimType(coverageType);
    }

    @When("I enter procedure details with at least 10 characters")
    public void i_enter_procedure_details_with_at_least_10_characters() {
        claimPage.setProcedureDetails("Appendix removal");
    }

    @When("I enter procedure details with less than 10 characters")
    public void i_enter_procedure_details_with_less_than_10_characters() {

        claimPage.setProcedureDetails("Short");
    }

    @When("I enter an amount greater than or equal to 500")
    public void i_enter_an_amount_greater_than_or_equal_to_500() {
        claimPage.setClaimAmount("1500");
    }

    @When("I enter an amount less than 500")
    public void i_enter_an_amount_less_than_500() {
        claimPage.setClaimAmount("300");
    }

    @When("I upload a valid supporting document")
    public void i_upload_a_valid_supporting_document() {
        claimPage.uploadDocument();
    }

    @When("I do not upload any document")
    public void i_do_not_upload_any_document() {
        // intentionally skip upload
    }

    @When("I click the {string} button")
    public void i_click_the_button(String buttonText) {
        claimPage.clickSubmit();
    }

    @Then("I should see an alert with text {string}")
    public void i_should_see_an_alert_with_text(String expectedText) {

        String actualText = claimPage.getAlert(expectedText);
        Assert.assertEquals(actualText, expectedText);

    }

    @Then("the {string} button should be disabled")
    public void the_button_should_be_disabled(String buttonText) {
        WebElement button = driver.findElement(By.xpath("//button[contains(text(),'" + buttonText + "')]"));
        Assert.assertFalse(button.isEnabled(), "Expected button to be disabled");
    }

    @And("logout and close browser")
    public void close_browser(){
        dashboardPage.clickLogoutButton();
        driver.close();
    }


}
