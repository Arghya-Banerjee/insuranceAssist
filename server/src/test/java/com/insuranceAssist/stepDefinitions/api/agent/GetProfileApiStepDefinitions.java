package com.insuranceAssist.stepDefinitions.api.agent;

import com.insuranceAssist.utils.ApiReq;
import com.insuranceAssist.utils.TokenManager;
import com.insuranceAssist.utils.UserIdManager;
import config.ApiEndpoints;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.testng.Assert;

public class GetProfileApiStepDefinitions {

    Response response;

    @When("I send request to GET my profile")
    public void i_send_request_to_get_my_profile(){

        String token = TokenManager.getToken();
        String url = ApiEndpoints.BASE_URL + ApiEndpoints.GET_PROFILE + UserIdManager.getUserId();

        response = ApiReq.getReqWithBearer(token, url);
    }

    @When("{string} matches the login username")
    public void matches_the_login_username(String username){
        Assert.assertEquals(username, response.jsonPath().getString("username"));
    }
}
