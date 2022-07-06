package org.onefamily.service;

import com.typesafe.config.Config;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class EmployeeService {

    private final Config config;
    private static EmployeeService instance;

    private EmployeeService(final Config config) {
        this.config = config;
        RestAssured.baseURI = config.getString("employees.app.uri");
    }

    public static EmployeeService getInstance(final Config config){
        if(instance == null){
            instance =  new EmployeeService(config);
        }
        return instance;
    }

    public Response getEmployees(){
        return given()
                .when()
                .get("/employees");
    }

    public Response getEmployeeById(final int id){
        return given()
                .when()
                .get("/employee/" + id);
    }

}
