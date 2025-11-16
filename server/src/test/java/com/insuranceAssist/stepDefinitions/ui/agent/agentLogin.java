package com.insuranceAssist.stepDefinitions.ui.agent;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.insuranceAssist.pages.AgentDashboardPage;
import com.insuranceAssist.pages.LoginPage;
import com.insuranceAssist.utils.DriverSetup;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class AgentLogin {

    WebDriver driver;
    LoginPage loginPage;
    AgentDashboardPage agentDashboardPage;

    @Then("Navigate to insuranceAssist URL")
    public void navigate_to_insuranceAssist_url() {
        driver = DriverSetup.getDriver();
        loginPage = new LoginPage(driver);
        driver.get("http://localhost:4200");
    }

    @Then("Perform login operation and verify logged in user {string}")
    public void perform_login_and_verify_user(String path) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        List<Map<String, String>> loginDate = mapper.readValue(
                new File(path),
                new TypeReference<List<Map<String, String>>>() {}
        );

        for(Map<String, String> data: loginDate){
            String username = data.get("username");
            String password = data.get("password");
            loginPage.selectRole("Agent");
            loginPage.inputUsername(username);
            loginPage.inputPassword(password);

            agentDashboardPage = loginPage.agentClickLoginButton();

            if(agentDashboardPage != null) {
                String agentDashboardUsername = agentDashboardPage.getDashboardUsernameText();
                Assert.assertEquals(agentDashboardUsername, username);
                agentDashboardPage.clickLogoutButton();
            }
        }

    }

    @Then("Close browser")
    public void close_browser() {
        driver.quit();
    }

}
