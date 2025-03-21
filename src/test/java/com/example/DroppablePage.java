package com.example;

/* import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DroppableTest {
    public static WebDriver driver;

    public static void main(String[] args) {
        // Set up WebDriver using WebDriverManager
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        try {
            // Buka url website
            driver.get("https://jqueryui.com/droppable/");
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
            driver.manage().window().maximize();

            driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@class='demo-frame']")));
            // Element droppable yang ingin ditest
            WebElement drag = driver.findElement(By.xpath("//p[normalize-space()='Drag me to my target']"));
            WebElement drop = driver.findElement(By.xpath("//div[@id='droppable']"));

            String initialColor = drop.getCssValue("background-color");
            System.out.println("Before drop, background color: " + initialColor);

            Actions action = new Actions(driver);
            action.dragAndDrop(drag, drop).perform();

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(100));
            wait.until(ExpectedConditions.textToBePresentInElement(drop, "Dropped!"));

            String actualText = drop.getText();
            if (actualText.equals("Dropped!")) {
                System.out.println("Text validation passed: " + actualText);
            } else {
                System.out.println("Text validation failed. Expected: 'Dropped!', but got: " + actualText);
            }

            wait.until(driver -> {
                String newColor = drop.getCssValue("background-color");
                return !newColor.equals(initialColor);
            });

            String newColor = drop.getCssValue("background-color");
            System.out.println("After drop, background color: " + newColor);

            String expectedColor = "rgba(255, 250, 144, 1)";

            if (newColor.equals(expectedColor)) {
                System.out.println("Color validation passed: " + newColor);
            } else {
                System.out.println("Color validation failed. Expected: " + expectedColor + ", but got: " + newColor);
            }

        } catch (Exception e) {
            System.err.println("Test failed due to an error: " + e.getMessage());
        }
    }
} */

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DroppablePage {
    private WebDriver driver;
    private WebDriverWait wait;
    private By iframe = By.xpath("//iframe[@class='demo-frame']");
    private By draggable = By.xpath("//p[normalize-space()='Drag me to my target']");
    private By droppable = By.id("droppable");

    public DroppablePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void openPage() {
        driver.get("https://jqueryui.com/droppable/");
        driver.manage().window().maximize();
        driver.switchTo().frame(driver.findElement(iframe));
    }

    public String getInitialColor() {
        return driver.findElement(droppable).getCssValue("background-color");
    }

    public void performDragAndDrop() {
        WebElement dragElement = driver.findElement(draggable);
        WebElement dropElement = driver.findElement(droppable);
        new Actions(driver).dragAndDrop(dragElement, dropElement).perform();
        wait.until(ExpectedConditions.textToBePresentInElement(dropElement, "Dropped!"));
    }

    public boolean isDropped() {
        return driver.findElement(droppable).getText().equals("Dropped!");
    }

    public boolean hasColorChanged(String initialColor) {
        return wait.until(driver -> !driver.findElement(droppable).getCssValue("background-color").equals(initialColor));
    }

    public String getNewColor() {
        return driver.findElement(droppable).getCssValue("background-color");
    }
}

