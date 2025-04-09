package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private final WebDriver driver;

    private final By usernameTextBox = By.id("email");
    private final By passwordTextBox = By.id("Password");
    private final By loginButton = By.xpath("//*[@id=\"main\"]/div/div/div/div[2]/div[1]/div/form/div[3]/button");
    
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterUsername(String username) {
        driver.findElement(usernameTextBox).clear();
        driver.findElement(usernameTextBox).sendKeys(username);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordTextBox).clear();
        driver.findElement(passwordTextBox).sendKeys(password);
    }

    public void clickLogin() {
        driver.findElement(loginButton).click();
    }
} 
