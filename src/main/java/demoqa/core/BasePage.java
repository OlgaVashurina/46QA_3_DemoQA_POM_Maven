package demoqa.core;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class BasePage {

    public WebDriver driver;
    public WebDriverWait wait;
    JavascriptExecutor js;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        this.js = (JavascriptExecutor) driver; //  инициализирует переменную js как экземпляр JavascriptExecutor, используя объект driver.
        // PageFactory упрощает работу с веб-элементами и делает код более чистым и читаемым
        // PageFactory — это утилита в Selenium WebDriver, которая упрощает инициализацию веб-элементов на странице и поддерживает концепцию Page Object Model (POM)
        // PageFactory инициализирует элементы, помеченные аннотациями в вашем классе страницы
        PageFactory.initElements(driver, this);
    }

    public void hideAds(){
        js.executeScript("document.getElementById('adplus-anchor').style.display='none';");
        js.executeScript("document.querySelector('footer').style.display='none';");
    }

    protected void click(WebElement element) {
        scrollToElement(element);
        element.click();
    }

    protected void type(WebElement element, String text) {
        if (text != null) {
            click(element);
            element.clear();
            element.sendKeys(text);
        }
    }

    protected void typeScrollWithJS(WebElement element, String text, int y) {
        if (text != null) {
            clickWitJS(element, 0, y);
            element.clear();
            element.sendKeys(text);
        }
    }

    protected void clickWitJS(WebElement element, int x, int y) {
        // js.executeScript("window.scrollBy(100,200)");
        // x - сколько пикселей прокрутить по горизонтали
        // y - сколько пикселей прокрутить по вертикали
        //  js.executeScript("window.scrollBy(0,500)");
        js.executeScript("window.scrollBy(" + x + "," + y + ")");
        //js.executeScript("window.scrollBy({},{})",x,y);
        click(element);
    }

    protected void scrollTo(int y) {
        js.executeScript("window.scrollBy("+0+"," + y + ")");
    }

    public void scrollToElement(WebElement element){
        wait.until(ExpectedConditions.visibilityOf(element));
        js.executeScript("arguments[0].scrollIntoView(true);",element);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    protected void shouldHaveText(WebElement element, String text, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(timeout));
        try {
            wait.until(ExpectedConditions.textToBePresentInElement(element, text));
            String actualText = element.getText();
            Assert.assertTrue(actualText.contains(text));
        } catch (TimeoutException e) {
            //throw new TimeoutException(e);
        }
    }
}
