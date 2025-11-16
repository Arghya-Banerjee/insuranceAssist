package com.insuranceAssist.stepDefinitions.api.agent;


import com.example.insuranceAssist.dto.LoginRequestDTO;
import com.insuranceAssist.utils.ApiReq;
import com.insuranceAssist.utils.TokenManager;
import com.insuranceAssist.utils.UserIdManager;
import config.ApiEndpoints;
import io.cucumber.java.en.*;
import io.restassured.response.Response;
import org.testng.Assert;

import static io.restassured.RestAssured.given;


public class LogInApiStepDefinitions {

    Response response;
    LoginRequestDTO requestBody;

    @Given("I supply login data in json format")
    public void i_supply_login_data_in_json_format() {
        requestBody = new LoginRequestDTO();
    }
    @When("I give valid {string} and {string}")
    public void i_give_valid_and(String username, String password) {

        requestBody.setUsername(username);
        requestBody.setPassword(password);

        String contentType = "application/json";
        String url = ApiEndpoints.BASE_URL + ApiEndpoints.POST_LOGIN;

        response = ApiReq.postReqWithoutBearer(contentType, requestBody, url);

    }
    @Then("I get login req status code as {int}")
    public void i_get_login_req_status_code_as(Integer int1) {
        Assert.assertEquals(response.getStatusCode(), 200);

    }

    @Then("I do not get status code as {int}")
    public void i_do_not_get_status_code_as(Integer int1) {
        Assert.assertNotEquals(response.getStatusCode(), 200);

    }

    @Then("Store the jwt token and user id")
    public void store_the_jwt_token_and_user_id(){
        TokenManager.setToken(response.jsonPath().getString("token"));
        UserIdManager.setUserId(response.jsonPath().getString("userId"));

    }

}
