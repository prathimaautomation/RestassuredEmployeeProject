package com.employeeapi.testCases;

import com.employeeapi.base.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import org.junit.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TC002_Get_Single_Employee_Record extends TestBase {

    @BeforeClass
    void getEmployeeData() throws InterruptedException {
        logger.info("**********Started TC002_Get_Single_Employee_Record*********");
        response= httpRequest.request(Method.GET,"/employee/"+empID);

        Thread.sleep(3000);
    }

    @Test
    void checkResponseBody(){
        String responseBody=response.getBody().asString();
        Assert.assertEquals(true, responseBody.contains(empID));
    }

    @Test
    void checkStatusCode(){
        int statusCode=response.getStatusCode();
        Assert.assertEquals(200,statusCode);
    }

    @Test
    void checkResponseTime(){
        long responseTime=response.getTime();
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

    @Test
    void checkServerType(){
        String serverType=response.header("Server");
        Assert.assertEquals("nginx/1.16.0", serverType);
    }

    @Test
    void checkContentLength(){
        String contentLength=response.header("Content-Length");
        Assert.assertTrue(Integer.parseInt(contentLength)>100);
    }

    @AfterClass
    void tearDown(){
        logger.info("****** Finished TC002_Get_Single_Employee_Record ******");
    }
}
