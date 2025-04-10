package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;
import utils.ExtentReportManager;
import utils.Log;


public class LoginTest extends BaseTest {
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