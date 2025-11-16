package com.insuranceAssist.utils;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ApiReq {

    public static Response getReqWithoutBearer(String url){
        return given()
                .when()
                .get(url);
    }

    public static Response getReqWithBearer(String token, String url){
        return given()
                .header("Authorization", "Bearer "+token)
                .when()
                .get(url);
    }

    public static Response postReqWithoutBearer(String contentType, Object reqBody, String url){
        return given()
                .contentType(contentType)
                .body(reqBody)
                .when()
                .post(url);
    }

    public static Response postReqWithBearer(String token, String contentType, Object reqBody, String url){
        return given()
                .header("Authorization", "Bearer "+token)
                .contentType(contentType)
                .body(reqBody)
                .when()
                .post(url);
    }

    public static Response putReqWithoutBearer(String contentType, Object reqBody ,String url){
        return given()
                .contentType(contentType)
                .body(reqBody)
                .when()
                .put(url);
    }

    public static Response putReqWithBearer(String token, String contentType, Object reqBody ,String url){
        return given()
                .contentType(contentType)
                .header("Authorization", "Bearer "+token)
                .body(reqBody)
                .when()
                .put(url);
    }

    public static Response deleteReqWithBearer(String token, String url){
        return given()
                .header("Authorization", "Bearer "+token)
                .when()
                .delete(url);
    }



}
