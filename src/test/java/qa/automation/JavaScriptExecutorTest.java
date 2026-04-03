package qa.automation;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class JavaScriptExecutorTest {

    @Test
    public void LoginWithJSAction(){
        WebDriver driver=new ChromeDriver();
        driver.get("https://www.saucedemo.com/");

        WebElement username = driver.findElement(By.id("user-name"));
        username.click();
        username.sendKeys("standard_user"); // da pishe v poleto username

        WebElement password = driver.findElement(By.xpath("(//input[@class= 'input_error form_input'])[2]"));
        password.click();
        password.sendKeys("secret_sauce");

        WebElement loginBtn= driver.findElement(By.cssSelector("[value=Login]"));
        loginBtn.click();



        JavascriptExecutor script = (JavascriptExecutor) driver;
        script.executeScript("arguments[0].scrollIntoView()",
                driver.findElement((By.xpath("//a[@href='https://www.linkedin.com/company/sauce-labs/']"))));

    }
}
