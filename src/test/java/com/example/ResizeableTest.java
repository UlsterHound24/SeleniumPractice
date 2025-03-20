package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ResizeableTest {
    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        ChromeDriver driver=new ChromeDriver();
        // Buka url website
        driver.get("https://jqueryui.com/resizable/");
        driver.manage().window().maximize();
        // Element yang ingin di-test
        WebElement frame1 = driver.findElement(By.xpath("//*[@id=\"content\"]/iframe"));
        driver.switchTo().frame(frame1);
        
        WebElement drag = driver.findElement(By.xpath("//*[@id=\"resizable\"]/div[3]"));
        Actions action = new Actions(driver);
        action.dragAndDropBy(drag, 100, 100);
        action.perform();
    }
}

