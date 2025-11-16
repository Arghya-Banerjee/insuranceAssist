package com.insuranceAssist.stepDefinitions.api.agent;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.insuranceAssist.utils.ApiReq;
import com.insuranceAssist.utils.TokenManager;
import config.ApiEndpoints;
import io.cucumber.java.en.*;
import io.restassured.response.Response;
import org.antlr.v4.runtime.Token;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PostHospitalApiStepDefinitions {
    Response response;

    @When("I provide details {string} to add hospital")
    public void i_provide_details_to_add_hospital(String filePath) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> reqBody = mapper.readValue(
                new File(filePath),
                new TypeReference<Map<String, String>>() {}
        );

        String token = TokenManager.getToken();
        String url = ApiEndpoints.BASE_URL + ApiEndpoints.POST_HOSPITAL_CREATE;
        String contentType = "application/json";

        response = ApiReq.postReqWithBearer(token, contentType, reqBody, url);

    }

    @When("I get the status code as {int}")
    public void i_get_the_status_code_as(Integer statusCode)
    {
        Assert.assertEquals(statusCode, 200);
    }



}
