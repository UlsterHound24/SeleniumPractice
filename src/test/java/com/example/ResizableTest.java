package com.example;
import java.time.Duration;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ResizableTest {
    private WebDriver driver;
    private ResizablePage resizablePage;

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://jqueryui.com/resizable/");
        resizablePage = new ResizablePage(driver);
    }

    @Test
    public void testResizeElement() {
        resizablePage.switchToFrame();
        
        int initialWidth = driver.findElement(By.id("resizable")).getSize().getWidth();
        int initialHeight = driver.findElement(By.id("resizable")).getSize().getHeight();
        
        resizablePage.resizeElement(100, 100);
        
        int newWidth = driver.findElement(By.id("resizable")).getSize().getWidth();
        int newHeight = driver.findElement(By.id("resizable")).getSize().getHeight();
        
        Assert.assertTrue("Width did not change", newWidth > initialWidth);
        Assert.assertTrue("Height did not change", newHeight > initialHeight);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
