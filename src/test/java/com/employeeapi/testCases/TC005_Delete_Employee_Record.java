package com.employeeapi.testCases;

import com.employeeapi.base.TestBase;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import org.junit.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TC005_Delete_Employee_Record  extends TestBase {
    @BeforeClass
    void deleteEmployee() throws InterruptedException {
        logger.info("*******Started TC005_Delete_Employee_Record*******");
        //baseURI & RestAssured.given moved to TestBase
        //JSONObject is a class that represents a simple JSON. we can add Key-Value pairs using the put method

        response=httpRequest.request(Method.GET,"/employees");
        //First get the JsonPath object instance from the Response interface
        JsonPath jsonPathEvaluator=response.jsonPath();

        //capture id
        String empID=jsonPathEvaluator.get("[0].id");
        response=httpRequest.request(Method.DELETE,"/delete/"+empID);//Pass ID to delete record

        Thread.sleep(5000);
    }

    @Test
    void checkResponseBody(){
        String responseBody=response.getBody().asString();
        Assert.assertTrue(responseBody.contains("Successfully! Record has been deleted"));
      }

    @Test
    void checkStatusCode(){
        int statusCode=response.getStatusCode();
        Assert.assertEquals(200,statusCode);
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
        logger.info("****Finished TC005_Delete_Employee_Record *****");
    }
}
