package com.insuranceAssist.runners.api.agent;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features/api/agent/GetProfile.feature",
        glue = "com.insuranceAssist.stepDefinitions.api.agent"
)

public class GetProfileApiTestRunner extends AbstractTestNGCucumberTests {
}
