package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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

    public ProductsPage login(String username, String password){
        userNameInput.click();
        userNameInput.sendKeys(username);

        passwordInput.click();
        passwordInput.sendKeys(password);

        loginButton.click();

        return new ProductsPage(driver);
    }
}
