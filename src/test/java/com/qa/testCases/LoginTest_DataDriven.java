package com.qa.testCases;

import com.qa.pageObjects.LoginPage;
import com.qa.utilities.ExcelUtils;
import org.openqa.selenium.NoAlertPresentException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTest_DataDriven extends BaseClass {

    @Test(dataProvider = "empdataprovider")
    public void logintest(String user,String pwd)
    {
        //driver.get(baseURL);
        logger.info("Opened the url..");

        LoginPage login = new LoginPage(driver);
        login.setUserName(user);
        logger.info("Entered Username..");
        login.setPassword(pwd);
        logger.info("Entered Password..");
        login.clickLogin();

        if(isAlertPresent() == true)
        {
            driver.switchTo().alert().accept(); //close alert
            driver.switchTo().defaultContent();
            Assert.assertTrue(false);
            logger.warn("Login Failed");
        }
        else {
            Assert.assertTrue(true);
            logger.info("Login Passed");
            driver.getTitle().contains("Guru99 Bank Manager HomePage");
            login.clickLogout();
            driver.switchTo().alert().accept(); //close logout alert
            driver.switchTo().defaultContent();
        }
    }

    private static boolean isAlertPresent()
    {
        try{
            driver.switchTo().alert();
            return true;
        }
        catch (NoAlertPresentException e)
        {
            return false;
        }
    }

    @DataProvider(name="empdataprovider")
    String[][] getEmpData() throws Exception {


        String empdata1[][] = { {"mngr26593","1234567@"},
                {"morpheus2","leader2"},
                {"morpheus3","leader3"}
        };

        String path = System.getProperty("user.dir")+"/src/test/java/com/qa/testData/empdata.xlsx";
        String sheetname = "Sheet1";

        //Use Apache poi api and create XLutil file with methods
        //to get row count and col count, getcelldata & setcelldata
        //Populate the data from excel to the empdata[][] with 2 for loops

        int rownum = ExcelUtils.getRowCount(path,sheetname);
        int colnum = ExcelUtils.getColumnCount(path,sheetname);
        String empdata[][] = new String[rownum][colnum];

        for(int i=1;i<=rownum;i++)
        {
            for(int j=0;j<colnum;j++)
            {
                empdata[i-1][j] = ExcelUtils.getCellData(i,j);
            }
        }
        return empdata;
    }
}
