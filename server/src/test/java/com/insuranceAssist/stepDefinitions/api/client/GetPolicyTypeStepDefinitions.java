package com.insuranceAssist.stepDefinitions.api.client;

import com.example.insuranceAssist.dto.PolicyTypeResponseDTO;
import com.insuranceAssist.utils.ApiReq;
import com.insuranceAssist.utils.TokenManager;
import config.ApiEndpoints;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.testng.Assert;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GetPolicyTypeStepDefinitions {

    Response response;

    @When("I send request to GET policy Type")
    public void i_send_request_to_get_policy_type(){
        String token = TokenManager.getToken();
        String url = ApiEndpoints.BASE_URL + ApiEndpoints.GET_POLICY_TYPE;
        response = ApiReq.getReqWithBearer(token, url);
    }

    @When("I get policy type api request status code as {int}")
    public void i_get_policy_type_api_request_status_code_as(Integer statusCode){
        Assert.assertEquals(statusCode, response.getStatusCode());
    }

    @When("I get the available policy details")
    public void i_get_the_available_policy_details(){
        List<PolicyTypeResponseDTO> res = response.jsonPath().getList("", PolicyTypeResponseDTO.class);
        Set<String> policyType = new HashSet<>();
        policyType.add("Gold");
        policyType.add("Silver");
        policyType.add("Bronze");
        policyType.add("Basic");
        policyType.add("Platinum");

        for (PolicyTypeResponseDTO x: res){
            if (!policyType.contains(x.getTier())) Assert.fail();
        }
        Assert.assertTrue(true);

    }

}
