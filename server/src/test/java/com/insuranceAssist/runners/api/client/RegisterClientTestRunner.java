package com.insuranceAssist.runners.api.client;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features/api/client/RegisterClient.feature",
        glue = {
                "com.insuranceAssist.stepDefinitions.api.client"
        },
        plugin = {
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
        },
        monochrome = true,
        tags="@api-testing"
)
public class RegisterClientTestRunner extends AbstractTestNGCucumberTests {
}
