package com.qa.testCases;

import com.qa.pageObjects.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseClass {

    @Test
    public void logintest()
    {

        logger.info("Opened the url..");

        LoginPage login = new LoginPage(driver);
        login.setUserName(username);
        logger.info("Entered Username..");
        login.setPassword(password);

        login.clickLogin();

        if(driver.getTitle().contains("Guru99 Bank Manager HomePage"))
        {
            Assert.assertTrue(true);
            logger.info("Login Test Passed");
        }
        else {
            Assert.assertTrue(false);
            logger.info("Login Test Failed");
        }
    }
}
