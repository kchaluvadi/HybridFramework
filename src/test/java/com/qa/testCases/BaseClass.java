package com.qa.testCases;
import com.beust.jcommander.Parameter;
import com.qa.utilities.ReadConfig;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.IOException;

public class BaseClass extends ReadConfig {

    ReadConfig config = new ReadConfig();
    public String baseURL = config.getBaseUrl(); //"https://demo.guru99.com/v4/index.php";
    public String username = config.getUserName(); //"mngr26593";
    public String password = config.getPassword(); //"1234567@";
    public static WebDriver driver;
    public static Logger logger;
    @BeforeClass
    @Parameters("browser")
    public void setup(String br)
    {

        logger =  Logger.getLogger("TestLogger");
        PropertyConfigurator.configure("log4j.properties");

        if(br.equals("chrome")) {
            String userdir = System.getProperty("user.dir");
            System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
            driver = new ChromeDriver();
        }
        else if(br.equals("firefox"))
        {
            System.setProperty("webdriver.gecko.driver", "/usr/local/bin/firefoxdriver");
            driver = new FirefoxDriver();
        }
        else {
            String userdir = System.getProperty("user.dir");
            System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
            driver = new ChromeDriver();
        }
        driver.get(baseURL);

    }
    @AfterClass
    public void teardown()
    {
        driver.quit();
    }

    public void captureScreenshot(WebDriver driver, String tname)
    {
        File srcfile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            File target = new File(System.getProperty("user.dir")+"/Screenshots/"+tname+".jpg");
            FileHandler.copy(srcfile, target);
            System.out.println("Screenshot taken");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
