package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class ResizablePage {
    private WebDriver driver;
    private By frameLocator = By.xpath("//*[@id='content']/iframe");
    private By resizableHandle = By.xpath("//*[@id='resizable']/div[3]");

    public ResizablePage(WebDriver driver) {
        this.driver = driver;
    }

    public void switchToFrame() {
        WebElement frame = driver.findElement(frameLocator);
        driver.switchTo().frame(frame);
    }

    public void resizeElement(int xOffset, int yOffset) {
        WebElement resizeHandle = driver.findElement(resizableHandle);
        Actions action = new Actions(driver);
        action.dragAndDropBy(resizeHandle, xOffset, yOffset).perform();
    }
}
