package com.insuranceAssist.stepDefinitions.api.client;

import com.insuranceAssist.utils.ApiReq;
import com.insuranceAssist.utils.TokenManager;
import config.ApiEndpoints;
import io.cucumber.java.en.*;
import io.restassured.response.Response;
import org.testng.Assert;

public class GetHospitalApiStepDefinitions {

    Response response;

    @When("i make a request to get all hospitals")
    public void i_make_a_request_to_get_all_hospitals(){

        String token = TokenManager.getToken();
        String url = ApiEndpoints.BASE_URL + ApiEndpoints.GET_HOSPITALS;

        response = ApiReq.getReqWithBearer(token, url);
    }

    @When("the get hospital status code is {int}")
    public void the_get_hospital_status_code_is(Integer statusCode){
        Assert.assertEquals(statusCode, response.getStatusCode());
    }

    @When("i receive data containing name, address, email, rating")
    public void i_receive_data_containing_name_address_email_rating(){
        boolean name = response.jsonPath().getString("name") != null;
        boolean address = response.jsonPath().getString("address") != null;
        boolean email = response.jsonPath().getString("email") != null;
        boolean rating = response.jsonPath().getString("rating") != null;

        Assert.assertTrue(name && address && email && rating);

    }

}
