package com.insuranceAssist.stepDefinitions.api.client;

import com.insuranceAssist.utils.ApiReq;
import com.insuranceAssist.utils.TokenManager;
import com.insuranceAssist.utils.UserIdManager;
import config.ApiEndpoints;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import org.testng.Assert;

public class GetPolicyByClientStepDefinitions {

    @Then("I call get policy by clientId API and verify the response")
    public void iCallGetPolicyByClientIdAPIAndVerifyTheResponse() {

        String url = ApiEndpoints.BASE_URL + ApiEndpoints.GET_POLICY;

        Response response = ApiReq.getReqWithBearer(TokenManager.getToken(), url + UserIdManager.getUserId());

        int responseCode = response.statusCode();
        Assert.assertEquals(responseCode, 200, "Status code should be 200 OK");

        String policyId = response.jsonPath().getString("policyId");
        String tier = response.jsonPath().getString("tier");
        String startDate = response.jsonPath().getString("startDate");
        String endDate = response.jsonPath().getString("endDate");
        Integer premium = response.jsonPath().getInt("premium");
        Integer remainingCoverage = response.jsonPath().getInt("remainingCoverage");

        Assert.assertNotNull(policyId, "Policy ID should not be null");
        Assert.assertNotNull(tier, "Tier should not be null");
        Assert.assertNotNull(startDate, "Start Date should not be null");
        Assert.assertNotNull(endDate, "End Date should not be null");
        Assert.assertNotNull(premium, "Premium should not be null");
        Assert.assertNotNull(remainingCoverage, "Remaining Coverage should not be null");


    }
}
