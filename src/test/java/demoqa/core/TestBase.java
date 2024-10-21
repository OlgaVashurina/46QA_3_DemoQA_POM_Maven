package demoqa.core;

import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class TestBase {
    public WebDriver driver;
    //public WebDriverWait wait;

    @BeforeMethod
    public void init(){
        driver = new ChromeDriver();
      //  driver.manage().window().setPosition(new Point(2500,0));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2)); // неявное
        //wait = new WebDriverWait(driver, Duration.ofSeconds(5)); // явное
        driver.get("https://demoqa.com/");
    }

    @AfterMethod(enabled = false)
    public void tearDown() {
        if (driver != null) {
            driver.quit(); // Остановка драйвера после всех тестов
        }
    }
}
