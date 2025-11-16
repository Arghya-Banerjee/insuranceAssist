package com.insuranceAssist.runners.api.client;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features/api/client/GetProfile.feature",
        glue = "com.insuranceAssist.stepDefinitions.api.client"
)

public class GetProfileApiTestRunner extends AbstractTestNGCucumberTests {
}
