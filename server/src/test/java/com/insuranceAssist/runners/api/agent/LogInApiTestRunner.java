package com.insuranceAssist.runners.api.agent;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        tags = "@positive",
        features = "src/test/resources/features/api/agent/LogIn.feature",
        glue = "com.insuranceAssist.stepDefinitions.api.agent"
)

public class LogInApiTestRunner extends AbstractTestNGCucumberTests {
}
