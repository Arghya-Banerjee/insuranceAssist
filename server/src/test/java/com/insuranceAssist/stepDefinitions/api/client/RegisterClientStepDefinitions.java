package com.insuranceAssist.stepDefinitions.api.client;

import com.example.insuranceAssist.dto.RegistrationRequestDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.insuranceAssist.utils.ApiReq;
import config.ApiEndpoints;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class RegisterClientStepDefinitions {

    @Then("Call register api and verify response details with data {string}")
    public void registerClientAndVerify(String testType) throws IOException {

        String registerUrl = ApiEndpoints.BASE_URL + ApiEndpoints.POST_REGISTER;
        String filePath;
        if(testType.equals("positive")) filePath = "src/test/resources/testData/client/clientRegisterPositive.json";
        else filePath = "src/test/resources/testData/client/clientRegisterNegative.json";

        ObjectMapper mapper = new ObjectMapper();
        List<Map<String, Object>> registerData = mapper.readValue(
                new File(filePath),
                new TypeReference<List<Map<String, Object>>>() {}
        );

        for(Map<String, Object> data: registerData){
            RegistrationRequestDTO body = new RegistrationRequestDTO(
                    (String) data.get("name"),
                    (String) data.get("gender"),
                    LocalDate.parse((String) data.get("dob")),
                    (Long) data.get("phone"),
                    (String) data.get("address"),
                    (String) data.get("email"),
                    (String) data.get("password")
            );
            Response response = ApiReq.postReqWithoutBearer("application/json", body, registerUrl);

            if(testType.equals("positive")) Assert.assertEquals(response.getStatusCode(), 201);
            else Assert.assertEquals(response.getStatusCode(), 400);
        }
    }

}
