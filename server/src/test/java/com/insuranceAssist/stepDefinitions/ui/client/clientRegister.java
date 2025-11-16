package com.insuranceAssist.stepDefinitions.ui.client;

import com.insuranceAssist.pages.LoginPage;
import com.insuranceAssist.pages.RegisterPage;
import com.insuranceAssist.utils.DriverSetup;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClientRegister {

    WebDriver driver;

    LoginPage loginPage;
    RegisterPage registerPage;

    @Given("the user is on the InsuranceAssist landing page")
    public void userOnRegistrationPage() {
        driver = DriverSetup.getDriver();
        driver.get("http://localhost:4200");
        driver.manage().window().maximize();
        loginPage = new LoginPage(driver);
    }

    @And("click on the register button")
    public void clickRegisterButton() {
        registerPage = loginPage.clickRegisterButton();
    }

    @When("the client enters primary registration details:")
    public void enterPrimaryDetails(DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        List<String> data = new ArrayList<>();
        for (Map<String, String> row : rows) {
            data.add(row.get("Value"));
        }

        System.out.println(data);

        String clientFullName   = data.get(0);
        String clientEmail      = data.get(1);
        String clientDob        = data.get(2);
        String clientGender     = data.get(3);
        String clientPhone      = data.get(4);
        String clientAddress    = data.get(5);

        registerPage.setClientFullname(clientFullName);
        registerPage.setClientEmail(clientEmail);
        registerPage.setClientConfirmEmail(clientEmail);
        registerPage.setClientDob(clientDob);
        registerPage.setClientGender(clientGender);
        registerPage.setClientAddress(clientAddress);
        registerPage.setClientPhoneNumber(clientPhone);

    }

    @And("sets the password to {string}")
    public void setPassword(String password) {
        registerPage.setClientPassword(password);
        registerPage.setClientConfirmPassword(password);
    }

    @And("submits the registration form")
    public void submitForm() {
        registerPage.clickSignupButton();
    }

    @And("adds {int} dependents with the following details")
    public void addsDependentsWithDetails(int numberOfDependents,
                                          DataTable dataTable) {

        Map<String, String> dependentsMap = new HashMap<>();
        for (Map<String, String> row : dataTable.asMaps(String.class, String.class)) {
            dependentsMap.put(row.get("Field"), row.get("Value"));
        }

        if (numberOfDependents == 0) {
            registerPage.clickSkipDependents();
        } else {
            for (int i = 1; i <= numberOfDependents; i++) {
                if (i > 1) {
                    registerPage.clickAddDependent();
                }

                // Dynamically fetch values for Dep1, Dep2, etc.
                String fullName = dependentsMap.get("Dep" + i + "FullName");
                String dob      = dependentsMap.get("Dep" + i + "DOB");
                String phone    = dependentsMap.get("Dep" + i + "Phone");
                String email    = dependentsMap.get("Dep" + i + "Email");
                String address  = dependentsMap.get("Dep" + i + "Address");
                String gender   = dependentsMap.get("Dep" + i + "Gender");
                String relation = dependentsMap.get("Dep" + i + "Relation");

                registerPage.fillDependentDetails(i, fullName, dob, phone,
                        email, address, gender, relation);
            }
            registerPage.clickDependentsContinueButton();
        }
    }

    @And("selects the {string} policy plan")
    public void selectPolicyPlan(String plan) {
        registerPage.selectPlan(plan);
    }

    @Then("the registration process is completed successfully")
    public void registrationCompleted() {
        String registrationText = registerPage.getRegistrationText();
        Assert.assertEquals(registrationText, "Registration and policy creation successful!");
    }

    @And("close the browser")
    public void closeBrowser(){
        driver.close();
    }
}
