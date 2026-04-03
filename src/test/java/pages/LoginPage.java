package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    protected WebDriver driver;

    @FindBy(id = "user-name")
    protected WebElement userNameInput;

    @FindBy(css = "[placeholder=Password")
    protected WebElement passwordInput;

    @FindBy(xpath = "//input[@value='Login']")
    protected WebElement loginButton;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);//podavame mu drivera i tekushtata stranica
    }

    public ProductsPage login(String username, String password) {
//        userNameInput.click();
//        userNameInput.sendKeys(username);
//
//        passwordInput.click();
//        passwordInput.sendKeys(password);
//
//        loginButton.click();
//
//        return new ProductsPage(driver);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Wait for the username field to be present before interacting
        WebElement usernameField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("user-name"))
        );
        usernameField.sendKeys(username);

        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("login-button")).click();
             return new ProductsPage(driver);

    }

}
