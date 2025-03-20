package com.example;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AccordionTest {
    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://jqueryui.com/accordion/");
        driver.manage().window().maximize();

        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@class='demo-frame']")));

        // Pencet header 2 (contoh)
        WebElement sectionHeader = driver.findElement(By.xpath("//div[@id='accordion']/h3[contains(text(),'Section 2')]"));
        sectionHeader.click();

        // Tunggu kontennya terlihat
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement sectionContent = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='accordion']/div[2]/p")));

        // Validasi apakah teks nya benar yang di dalam konten yang terlihat
        String expectedText = "Sed non urna. Donec et ante. Phasellus eu ligula. Vestibulum sit amet purus. Vivamus hendrerit, dolor at aliquet laoreet, mauris turpis porttitor velit, faucibus interdum tellus libero ac justo. Vivamus non quam. In suscipit faucibus urna.";
        String actualText = sectionContent.getText();

        if (actualText.equals(expectedText)) {
            System.out.println("Accordion validation passed!");
        } else {
            System.out.println("Accordion validation failed!");
            System.out.println("Expected: " + expectedText);
            System.out.println("Actual: " + actualText);
        }
    }
}