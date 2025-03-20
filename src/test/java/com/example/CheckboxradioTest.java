package com.example;

import java.time.Duration;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CheckboxradioTest {

    @Test
    public void testCheckboxRadioSelection() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        // Buka url website
        driver.get("https://jqueryui.com/checkboxradio/");

        driver.switchTo().frame(driver.findElement(By.cssSelector(".demo-frame")));

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        // Ini untuk tombol radionya
        String radioText = "New York";
        WebElement radioButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[contains(text(), '" + radioText + "')]")));
        radioButton.click();

        // Validasi warnanya setelah ada seleksi
        String selectedRadioColor = radioButton.getCssValue("background-color");
        String hexRadioColor = Color.fromString(selectedRadioColor).asHex();
        System.out.println("Selected radio button color: " + hexRadioColor);
        Assert.assertEquals("#007fff", hexRadioColor); 

        // Ini untuk tombol checkbox nya
        String checkboxText = "5 Star"; 
        WebElement checkbox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[contains(text(), '" + checkboxText + "')]")));
        checkbox.click();

        // Validasi warnanya setelah ada seleksi
        String selectedCheckboxColor = checkbox.getCssValue("background-color");
        String hexCheckboxColor = Color.fromString(selectedCheckboxColor).asHex();
        System.out.println("Selected checkbox color: " + hexCheckboxColor);
        Assert.assertEquals("#007fff", hexCheckboxColor); 

        // Ini untuk tombol checkbox yang nested
        String nestedCheckboxText = "2 Double"; 
        WebElement nestedCheckbox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[contains(text(), '" + nestedCheckboxText + "')]")));
        nestedCheckbox.click();

        // Validasi warnanya setelah ada seleksi
        String selectedNestedCheckboxColor = nestedCheckbox.getCssValue("background-color");
        String hexNestedCheckboxColor = Color.fromString(selectedNestedCheckboxColor).asHex();
        System.out.println("Selected nested checkbox color: " + hexNestedCheckboxColor);
        Assert.assertEquals("#007fff", hexNestedCheckboxColor);

        driver.quit();

    }
}
