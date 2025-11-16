package com.insuranceAssist.runners.api.client;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features/api/client/GetPolicyType.feature",
        glue = "com.insuranceAssist.stepDefinitions.api.client"
)

public class GetPolicyTypeApiTestRunner extends AbstractTestNGCucumberTests {
}
