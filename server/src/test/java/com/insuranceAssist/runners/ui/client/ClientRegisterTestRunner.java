package com.insuranceAssist.runners.ui.client;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        glue = {"com.insuranceAssist.stepDefinitions.ui.client"},
        features = {"src/test/resources/features/ui/client/clientRegister.feature"},
        plugin = {
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
        },
        monochrome = true
)
public class ClientRegisterTestRunner extends AbstractTestNGCucumberTests {
}
