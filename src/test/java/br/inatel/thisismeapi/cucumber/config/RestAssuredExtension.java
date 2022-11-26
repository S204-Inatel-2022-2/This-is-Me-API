package br.inatel.thisismeapi.cucumber.config;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import io.restassured.specification.RequestSpecification;

public class RestAssuredExtension {

    public static final String BASE_URL = "https://timeapibyredfoxghs.herokuapp.com";
    public static RequestSpecification REQUEST;

    public RestAssuredExtension() {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.setBaseUri(BASE_URL);
        requestSpecBuilder.setContentType("application/json");
        var requestSpec = requestSpecBuilder.build();
        REQUEST = RestAssured.given().spec(requestSpec);
    }

    public static ResponseOptions<Response> GetOps(String url) {
        try {
            return REQUEST.get(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
