package com.example;

import java.time.Duration;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DroppableTest {
    private WebDriver driver;
    private DroppablePage droppablePage;

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        droppablePage = new DroppablePage(driver);
    }

    @Test
    public void testDragAndDrop() {
        droppablePage.openPage();
        String initialColor = droppablePage.getInitialColor();
        droppablePage.performDragAndDrop();
        Assert.assertTrue(droppablePage.isDropped());
        Assert.assertTrue(droppablePage.hasColorChanged(initialColor));
        Assert.assertEquals("rgba(255, 250, 144, 1)", droppablePage.getNewColor());
    }
}