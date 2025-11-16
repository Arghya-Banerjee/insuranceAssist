package com.insuranceAssist.runners.api.agent;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features/api/agent/UpdateHospital.feature",
        glue = {
                "com.insuranceAssist.stepDefinitions.api.agent"
        },
        plugin = {
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
        },
        monochrome = true,
        tags="@api-testing"
)
public class PutHospitalUpdateTestRunner extends AbstractTestNGCucumberTests {
}
