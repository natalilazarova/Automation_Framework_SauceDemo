package qa.automation;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class OnlySuccessfulLogin {
    private WebDriver driver;

    @BeforeTest // predi vseki test da pravi:
    public void initializeDriver(){
        //btw trygna i bez nego
        WebDriverManager.chromedriver().setup();//proverqva nashata versiq na Chrome i svalq nujniq driver za neq i nie mojem da go polzvame

        driver = new ChromeDriver();

    }

    @AfterClass // kakvo da napravi kato mine testa, naprimer da zatvori otvoreniq prozorec
    public void tearDown(){
        driver.quit();// quit - direktno zachistva/zatvarq vsichko
        //driver.close(); ako iskame sled tova da prodyljim da vyrshim neshto togava go izpolzvame
    }


    @Test //idva ot Test NG
    public void successfulLoginTest(){
       // driver.get("https://www.saucedemo.com/");

        WebElement username = driver.findElement(By.id("user-name"));
        username.click();
        username.sendKeys("standard_user"); // da pishe v poleto username

        WebElement password = driver.findElement(By.xpath("(//input[@class= 'input_error form_input'])[2]"));
        password.click();
        password.sendKeys("secret_sauce");

        WebElement loginBtn= driver.findElement(By.cssSelector("[value=Login]"));
        loginBtn.click();

        WebElement userAllPagesBtn = driver.findElement(By.id("react-burger-menu-btn"));

        Assert.assertTrue(userAllPagesBtn.isDisplayed(), "This should be visible only after successful login");



    }
}
