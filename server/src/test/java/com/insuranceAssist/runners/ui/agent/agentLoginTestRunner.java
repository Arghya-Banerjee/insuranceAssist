package com.insuranceAssist.runners.ui.agent;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        glue = {"com.insuranceAssist.stepDefinitions.ui.agent"},
        features = {"src/test/resources/features/ui/agent/agentLogin.feature"},
        plugin = {
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
        },
        monochrome = true,
        tags="@agent-login"
)
public class AgentLoginTestRunner extends AbstractTestNGCucumberTests {
}
