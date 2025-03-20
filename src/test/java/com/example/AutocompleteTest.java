package com.example;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AutocompleteTest {
    public static void main(String[] args) {
        // Setup WebDriver
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Buka url website
        driver.get("https://jqueryui.com/autocomplete/");
        driver.manage().window().maximize();

        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@class='demo-frame']")));

        // Mencari lokasi kotak input untuk autocomplete
        WebElement textbox = driver.findElement(By.id("tags"));
        textbox.sendKeys("J");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='ui-id-1']/li")));

        // Ada berapa suggestion ketika muncul dropdown untuk autocomplete nya?
        List<WebElement> listItems = driver.findElements(By.xpath("//*[@id='ui-id-1']/li"));
        System.out.println("Number of suggestions: " + listItems.size());

        // Coba memilih JavaScript
        for (WebElement webElement : listItems) {
            if (webElement.getText().equals("JavaScript")) {
                webElement.click();
                System.out.println("'JavaScript' selected!");
                break;
            }
        }
    }
}
