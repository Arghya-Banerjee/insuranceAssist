package com.insuranceAssist.stepDefinitions.api.agent;

import com.example.insuranceAssist.dto.HospitalCreateRequestDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.insuranceAssist.utils.ApiReq;
import com.insuranceAssist.utils.TokenManager;
import config.ApiEndpoints;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class PutHospitalUpdateStepDefinitions {

    @Then("I call update hospital by hospitalId API and verify the response with data from {}")
    public void updateAndVerifyHospitalData(String filePath) throws IOException {

        String getUrl = ApiEndpoints.BASE_URL + ApiEndpoints.GET_HOSPITALS;
        String putUrl = ApiEndpoints.BASE_URL + ApiEndpoints.PUT_HOSPITAL_UPDATE;

        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> hospitalData = mapper.readValue(
                new File("src/test/resources/testData/agent/putHospitalData.json"),
                new TypeReference<Map<String, Object>>() {}
        );

        String hospitalId = (String) hospitalData.get("id");
        HospitalCreateRequestDTO body = new HospitalCreateRequestDTO(
                (String) hospitalData.get("name"),
                (String) hospitalData.get("address"),
                (String) hospitalData.get("email"),
                (Double) hospitalData.get("rating"),
                (String) hospitalData.get("client_contact_email"),
                (Long) hospitalData.get("client_contact_number"),
                (int) hospitalData.get("network")
        );

        Response putResponse = ApiReq.putReqWithBearer(TokenManager.getToken(), "application/json", body, putUrl + hospitalId);
        Response getResponse = ApiReq.getReqWithBearer(TokenManager.getToken(), getUrl + hospitalId);

        getResponse.prettyPrint();

        Assert.assertEquals(putResponse.getStatusCode(), 200);
        Assert.assertEquals(getResponse.jsonPath().getString("name"), body.getName());
        Assert.assertEquals(getResponse.jsonPath().getString("address"), body.getAddress());
        Assert.assertEquals(getResponse.jsonPath().getString("email"), body.getEmail());
        Assert.assertEquals(getResponse.jsonPath().getDouble("rating"), body.getRating());
        Assert.assertEquals(getResponse.jsonPath().getString("clientContactEmail"), body.getClientContactEmail());
        Assert.assertEquals(getResponse.jsonPath().getLong("clientContactNumber"), body.getClientContactNumber());
        Assert.assertEquals(getResponse.jsonPath().getInt("network"), body.getNetwork());

    }

}
