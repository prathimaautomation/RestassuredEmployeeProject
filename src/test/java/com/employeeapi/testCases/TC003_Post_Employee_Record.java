package com.employeeapi.testCases;

import com.employeeapi.base.TestBase;
import com.employeeapi.utilities.RestUtils;
import io.restassured.http.Method;
import org.json.simple.JSONObject;
import org.junit.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TC003_Post_Employee_Record extends TestBase {

    String empName= RestUtils.empName();
    String empSalary=RestUtils.empSal();
    String empAge=RestUtils.empAge();

    @BeforeClass
    void createEmployee() throws InterruptedException {
        logger.info("*******Started TC003_Post_Employee_Record*******");
        //baseURI & RestAssured.given moved to TestBase
        //JSONObject is a class that represents a simple JSON. we can add Key-Value pairs using the put method
        //{"name":"John123X", "salary":"123","age":"123"}
        JSONObject requestParams=new JSONObject();
        requestParams.put("name",empName);
        requestParams.put("salary",empSalary);
        requestParams.put("age",empAge);

        //Add a header stating the Request body is a JSON
        httpRequest.header("Content-Type","application/json");

        //Add the Json to the body of the request
        httpRequest.body(requestParams.toJSONString());

        response=httpRequest.request(Method.POST,"/create");

        Thread.sleep(5000);
    }

    @Test
    void checkResponseBody(){
        String responseBody=response.getBody().asString();
        Assert.assertTrue(responseBody.contains(empName));
        Assert.assertTrue(responseBody.contains(empSalary));
        Assert.assertTrue(responseBody.contains(empAge));
    }

    @Test
    void checkStatusCode(){
        int statusCode=response.getStatusCode();
        Assert.assertEquals(200,statusCode);
    }

    @Test
    void checkResponseTime(){
        long responseTime=response.getTime();
        if(responseTime>2000)
            logger.warn("Response Time is greater than 2000");
        Assert.assertTrue(responseTime<2000);
    }

    @Test
    void checkStatusLine(){
        String statusLine=response.getStatusLine();
        Assert.assertEquals("HTTP/1.1 200 OK", statusLine);
    }

    @Test
    void checkContentType(){
        String contentType=response.header("Content-Type");
        Assert.assertEquals("application/json", contentType);
    }

    @Test void checkServerType(){
        String serverType=response.header("Server");
        Assert.assertEquals("nginx/1.16.0", serverType);
    }

    @AfterClass
    void tearDown(){
        logger.info("****Finished TC003_Post_Employee_Record *****");
    }
}
