package qa.automation;

import base.TestUtil;
import com.opencsv.exceptions.CsvException;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductsPage;
import utils.CsvHelper;

import java.io.IOException;
import java.util.List;

public class LoginTest extends TestUtil {

  //  private WebDriver driver;

//    @BeforeTest // predi vseki test da pravi:
//    public void initializeDriver(){
//        //btw trygna i bez nego
//        WebDriverManager.chromedriver().setup();//proverqva nashata versiq na Chrome i svalq nujniq driver za neq i nie mojem da go polzvame
//
//        driver = new ChromeDriver();
//
//    }
//
//    @AfterClass // kakvo da napravi kato mine testa, naprimer da zatvori otvoreniq prozorec
//    public void tearDown(){
//        driver.quit();// quit - direktno zachistva/zatvarq vsichko
//        //driver.close(); ako iskame sled tova da prodyljim da vyrshim neshto togava go izpolzvame
//    }

    @DataProvider(name = "wrongUserList") //ot kyde shte vzemem dannite
    public Object [][] getWrongUsers(){
        return new Object[][]{
                {"standardUser7", "secret_sauce"},
                {"standard_user", "wrong password"},
                {"blah", "blah"}
        };
    }

    @Test(dataProvider ="wrongUserList" )
    public void unsuccessfulLogin(String userName, String password){
//        driver.get("https://www.saucedemo.com/");

        WebElement username = driver.findElement(By.id("user-name"));
        username.click();
        username.sendKeys(userName); // da pishe v poleto username

        WebElement passwordInput = driver.findElement(By.xpath("(//input[@class= 'input_error form_input'])[2]"));
        passwordInput.click();
        passwordInput.sendKeys(password);

        WebElement loginBtn= driver.findElement(By.cssSelector("[value=Login]"));
        loginBtn.click();

        WebElement errorLoginLabel= driver.findElement(By.xpath("//*[text()='Epic sadface: Username and password do not match any user in this service']")
        );
        Assert.assertTrue(errorLoginLabel.isDisplayed());


    }

    @DataProvider(name = "csvUserList")
    public static Object[][] reatUsersFromCsvFile() throws IOException, CsvException {

        return   CsvHelper.readCsvFile("src/test/resources/users.csv");
            }

    @Test(dataProvider = "csvUserList")
    public void successfulLogin(String userName, String password){
   //     driver.get("https://www.saucedemo.com/");

//        WebElement username = driver.findElement(By.id("user-name"));
//        username.click();
//        username.sendKeys(userName); // da pishe v poleto username
//
//        WebElement passwordInput = driver.findElement(By.xpath("(//input[@class= 'input_error form_input'])[2]"));
//        passwordInput.click();
//        passwordInput.sendKeys(password);
//
//        WebElement loginBtn= driver.findElement(By.cssSelector("[value=Login]"));
//        loginBtn.click();
//
//        WebElement userAllPagesBtn = driver.findElement(By.id("react-burger-menu-btn"));
//
//        Assert.assertTrue(userAllPagesBtn.isDisplayed(), "This should be visible only after successful login");

        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage= loginPage.login(userName, password);

    }
}
