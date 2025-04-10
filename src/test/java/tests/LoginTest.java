package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;
import utils.Log;


public class LoginTest extends BaseTest {
    @Test
    public void testValidLogin() {
        
        Log.info("Starting login test...");
        LoginPage loginPage = new LoginPage(driver);

        loginPage.enterUsername("admin@yourstore.com");
        loginPage.enterPassword("admin");
        loginPage.clickLogin();
        
        System.out.println("Title of the page is : "+driver.getTitle());
        Log.info("Verifying page title...");
        Assert.assertEquals(driver.getTitle(), "Just a moment...");
    }
}