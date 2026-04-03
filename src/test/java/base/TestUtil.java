package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class TestUtil {

    public WebDriver driver;
    private String url; //= "https://www.saucedemo.com/";
    private String browser;
    private int implicitWait;

    @BeforeMethod // predi vseki test da pravi:
    public void setUp(){
        setupBrowserDriver();

    }

    private void loadUrl(String url) {
        driver.get(url);

    }

    public void setupBrowserDriver() {
        try(FileInputStream configFile = new FileInputStream("src/test/resources/config.properties")){
            Properties config = new Properties();
            config.load(configFile);
            url = config.getProperty("urlAddress");
            browser = config.getProperty("browser");
            implicitWait = Integer.parseInt(config.getProperty("implicitWait"));
        }catch (IOException e){
            System.out.println("Cannot read config file");
        }
        //btw trygna i bez nego

        switch (browser){
            case "chrome":
                 createChromeDriver(url, implicitWait);
                break;
            case "firefox":
                 createFirefoxDriver(url, implicitWait);
                break;
            default:
                throw new IllegalStateException("Unknown browser");
        }
      //  WebDriverManager.chromedriver().setup();//proverqva nashata versiq na Chrome i svalq nujniq driver za neq i nie mojem da go polzvame
      //  driver = new ChromeDriver();
    }

    private void createFirefoxDriver(String url, int imlicitWait) {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(imlicitWait));
        loadUrl(url);
    }
    private void createChromeDriver(String url, int imlicitWait) {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(imlicitWait));
        loadUrl(url);
    }


    @AfterMethod // kakvo da napravi kato mine testa, naprimer da zatvori otvoreniq prozorec
    public void tearDown(){
        driver.quit();// quit - direktno zachistva/zatvarq vsichko
        //driver.close(); ako iskame sled tova da prodyljim da vyrshim neshto togava go izpolzvame
    }

}
