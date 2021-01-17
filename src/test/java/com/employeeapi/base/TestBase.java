package com.employeeapi.base;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeClass;


public class TestBase {
    public static RequestSpecification httpRequest;
    public static Response response;
    public String empID="9"; //Hard coded-Input for Get details of single Employee and update Employee

    public Logger logger;

    @BeforeClass
    public void setup() {
        logger=Logger.getLogger("EmployeesRestAPI"); //added Logger
        PropertyConfigurator.configure("log4j.properties"); //added logger
        logger.setLevel(Level.DEBUG);
        RestAssured.baseURI="http://dummy.restapiexample.com/api/v1";
        httpRequest=RestAssured.given();

    }
}
