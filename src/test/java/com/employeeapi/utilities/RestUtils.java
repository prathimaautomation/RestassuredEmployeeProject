package com.employeeapi.utilities;

import org.apache.commons.lang3.RandomStringUtils;

public class RestUtils {
    static String generateString;
    public static String empName(){
        generateString= RandomStringUtils.randomAlphabetic(1);
        return ("John"+generateString);
    }
    public static String empSal(){
        generateString= RandomStringUtils.randomNumeric(5);
        return (generateString);
    }
    public static String empAge(){
        generateString= RandomStringUtils.randomNumeric(2);
        return (generateString);
    }
}
