package com.example;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SortableTest {
    public static WebDriver driver;

    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        // Buka url website
        driver.get("https://jqueryui.com/sortable/");
        driver.manage().window().maximize();
        
        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@class='demo-frame']")));

        List<WebElement> listItems = driver.findElements(By.xpath("//ul[@id='sortable']/li"));
        
        List<String> initialOrder = listItems.stream().map(WebElement::getText).collect(Collectors.toList());
        System.out.println("Initial Order: " + initialOrder);
        
        Actions actions = new Actions(driver);

        WebElement source = listItems.get(listItems.size() - 1);  
        WebElement target = listItems.get(0);  
        actions.clickAndHold(source)
               .moveToElement(target)
               .release()
               .perform();

        WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(5));
        wait.until(ExpectedConditions.textToBePresentInElement(target, source.getText()));

        listItems = driver.findElements(By.xpath("//ul[@id='sortable']/li"));
        List<String> finalOrder = listItems.stream().map(WebElement::getText).collect(Collectors.toList());
        System.out.println("Final Order: " + finalOrder);
        // Validasi setelah sorting
        if (!initialOrder.equals(finalOrder)) {
            System.out.println("Sorting successfully validated.");
        } else {
            System.out.println("Sorting validation failed.");
        }
    }
}
