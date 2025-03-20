package com.example;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SelectableTest {
    public static WebDriver driver;

    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        try {
            // Buka url website
            driver.get("https://jqueryui.com/selectable/#display-grid");
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.manage().window().maximize();

            driver.switchTo().frame(driver.findElement(By.cssSelector(".demo-frame")));

            // Test Case 1: Click beberapa element
            testClickMultipleElements(new int[]{1, 3, 5, 7});

            // Test Case 2: Pakai swipe untuk seleksi element
            testSwipeToSelect(1, 1, 2, 3); // Example: Select a range from row 1, column 1 to row 2, column 3

        } catch (Exception e) {
            System.err.println("Test failed because of this error!: " + e.getMessage());
        } 
    }

    // Metode untuk click beberapa element
    public static void testClickMultipleElements(int[] elementIndexes) {
        Actions actions = new Actions(driver);
        
        for (int index : elementIndexes) {
            WebElement element = driver.findElement(By.xpath("//ol[@id='selectable']/li[" + index + "]"));
            actions.keyDown(Keys.CONTROL).click(element).keyUp(Keys.CONTROL).perform();
        }

        // Validasi perubahan warna
        validateColorChange(elementIndexes);
    }

    // Metode swiping untuk seleksi
    public static void testSwipeToSelect(int startRow, int startCol, int endRow, int endCol) {
        List<WebElement> elements = driver.findElements(By.xpath("//ol[@id='selectable']/li"));
        int columns = 3; // Assuming the grid has 3 columns

        int startIndex = (startRow - 1) * columns + startCol;
        int endIndex = (endRow - 1) * columns + endCol;

        WebElement startElement = elements.get(startIndex - 1);
        WebElement endElement = elements.get(endIndex - 1);

        Actions actions = new Actions(driver);
        actions.clickAndHold(startElement).moveToElement(endElement).release().perform();

        // Validasi perubahan warna
        validateColorChangeInRange(startIndex, endIndex);
    }

    // Metode untuk validasi perubahan warna tc1
    public static void validateColorChange(int[] elementIndexes) {
        for (int index : elementIndexes) {
            WebElement element = driver.findElement(By.xpath("//ol[@id='selectable']/li[" + index + "]"));
            String color = element.getCssValue("background-color");

            if (!color.equals("rgba(230,156,59,255)")) {
                System.out.println("Color validation passed for element " + index);
            } else {
                System.out.println("Color validation failed for element " + index);
            }
        }
    }

    // Metode untuk validasi perubahan warna tc2
    public static void validateColorChangeInRange(int startIndex, int endIndex) {
        List<WebElement> elements = driver.findElements(By.xpath("//ol[@id='selectable']/li"));

        for (int i = startIndex - 1; i < endIndex; i++) {
            String color = elements.get(i).getCssValue("background-color");

            if (!color.equals("rgba(230,156,59,255)")) {
                System.out.println("Color validation passed for element " + (i + 1));
            } else {
                System.out.println("Color validation failed for element " + (i + 1));
            }
        }
    }
}
