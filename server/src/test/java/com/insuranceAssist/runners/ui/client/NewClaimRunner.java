package com.insuranceAssist.runners.ui.client;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        tags = "@NewClaim",
        features = "src/test/resources/features/ui/client/insuranceClaim.feature",
        glue = {"com.insuranceAssist.stepDefinitions.ui.client"}, //try giving the exact path of runner
        plugin = {
                "pretty",
                "html:target/TestsReport/cucumber-reports.html",
        },
        monochrome = true,
        publish = true

)
public class NewClaimRunner extends AbstractTestNGCucumberTests {
}
