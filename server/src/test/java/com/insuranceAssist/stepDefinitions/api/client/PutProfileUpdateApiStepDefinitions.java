package com.insuranceAssist.stepDefinitions.api.client;

import com.example.insuranceAssist.dto.UserDetailsUpdateRequestDTO;
import com.insuranceAssist.utils.ApiReq;
import com.insuranceAssist.utils.TokenManager;
import com.insuranceAssist.utils.UserIdManager;
import config.ApiEndpoints;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.testng.Assert;

public class PutProfileUpdateApiStepDefinitions {

    Response response;

    @When("I send request to update {string} and {string}")
    public void i_send_request_to_update_and(String address, String phoneno){

        String token = TokenManager.getToken();
        String url = ApiEndpoints.BASE_URL + ApiEndpoints.PUT_PROFILE_UPDATE + UserIdManager.getUserId();
        String contentType = "application/json";
        UserDetailsUpdateRequestDTO reqBody = new UserDetailsUpdateRequestDTO(address, Long.parseLong(phoneno));

        response = ApiReq.putReqWithBearer(token, contentType, reqBody, url);
    }

    @When("I check the profile is updated correctly with {string} and {string}")
    public void i_check_the_profile_is_updated_correctly(String address, String phoneno){
        Assert.assertTrue(response.jsonPath().getString("address").equals(address) && response.jsonPath().getString("phone").equals(phoneno));

    }

}
