package qa.automation;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductsPage;

import java.time.Duration;
import java.util.Collections;
import java.util.NoSuchElementException;

public class ProductTest  {
    private WebDriver driver;
    @BeforeTest
    public void initializeDriver(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @AfterClass
    public void tearDown(){
        driver.quit();// quit - direktno zachistva/zatvarq vsichko
    }

    @Test
    public void selectDifferentOrder() throws InterruptedException {
         driver.get("https://www.saucedemo.com/");
        WebElement username = driver.findElement(By.id("user-name"));
        username.click();
        username.sendKeys("standard_user");


        WebElement password = driver.findElement(By.id("password"));
        password.click();
        password.sendKeys("secret_sauce");

        WebElement loginBtn = driver.findElement(By.cssSelector("[value= Login]"));
        loginBtn.click();

     //   Explicit wait
        //ako iskame da uvelichim chakaneto samo na end konkretno mqsto -  t.e samo tuk da e razlichno ot 5 sek.
      //  WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement dropDownSortingOptions = driver.findElement(By.xpath("//select[@class='product_sort_container']"));

       //dropDownSortingOptions.wait();
        dropDownSortingOptions.click();

        FluentWait fluentWait = new FluentWait(driver)
                .withTimeout(Duration.ofSeconds(20)) //max vreme za koeto elementa trqbva da e tam
                .pollingEvery(Duration.ofSeconds(2))//prez 2 sekundi proverqva dali elementa se e poqvil i taka do 20 sekundi ako minat 20 sekundi i elementa oshte go nqma shte hvyrli exceptiona
                .ignoreAll(Collections.singleton(NoSuchElementException.class));

        WebElement lowToHighPriceOption = driver.findElement(By.cssSelector("[value=lohi]"));
        fluentWait.until(ExpectedConditions.elementToBeClickable(lowToHighPriceOption));
        lowToHighPriceOption.click();
       // Thread.sleep(3000);

        }

    @Test
    public void addItemToCart(){
        driver.get("https://www.saucedemo.com/");
        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productPage = loginPage.login("standard_user","secret_sauce");

}
    }


