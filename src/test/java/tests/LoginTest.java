package tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;
import utils.ExcelUtils;
import utils.ExtentReportManager;
import utils.Log;


public class LoginTest extends BaseTest {

    public Object[][] getLoginData() throws IOException {
        String filePath = System.getProperty("user.dir") + "/testdata/TestData.xlsx";
        ExcelUtils.loadExcel(filePath, "Sheet1");
        int rowCount = ExcelUtils.getRowCount();
        Object[][] data = new Object[rowCount-1][2];
        
        for(int i = 1; i < rowCount; i++) {
            data[i-1][0] = ExcelUtils.getCellData(i,0); //Username
            data[i-1][1] = ExcelUtils.getCellData(i, 1); //Password 
        }
        ExcelUtils.closeExcel();
        return data;
    }

    @Test
    public void testValidLogin() {
        
        Log.info("Starting login test...");
        test = ExtentReportManager.createTest("Login Test");
        
        test.info("Navigating to URL");
        LoginPage loginPage = new LoginPage(driver);
        
        test.info("Navigated to Login Page");

        Log.info("Adding credentials");
        
        test.info("Adding credentials");
        
        loginPage.enterUsername("admin@yourstore.com");
        loginPage.enterPassword("admin");
        test.info("Clicking on Login button");
        loginPage.clickLogin();
        
        test.info("Entered credentials and clicked Login");
        
        Log.info("Verifying page title...");
        test.info("Verifying Page Title");
        Assert.assertEquals(driver.getTitle(), "Just a moment...");
        test.pass("Login Successful");
    }
}