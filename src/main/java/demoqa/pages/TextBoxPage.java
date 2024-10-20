package demoqa.pages;

import demoqa.core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

public class TextBoxPage extends BasePage {

    @FindBy(id = "userName")
    WebElement fullName;

    @FindBy(id = "userEmail")
    WebElement email;

    @FindBy(id = "currentAddress")
    WebElement currentAddress;

    @FindBy(id = "permanentAddress")
    WebElement permanentAddress;

    @FindBy(id = "submit")
    WebElement submitButton;

    public TextBoxPage(WebDriver driver) {
        super(driver);
    }

    public TextBoxPage enterPersonalData(String name, String emailAddress, String address) {
        fullName.sendKeys(name);
        email.sendKeys(emailAddress);
        currentAddress.sendKeys(address);
        return this;
    }

    public TextBoxPage keyboardEvent() {
        try {
            currentAddress.click();
            Robot robot = new Robot();

            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_A);
            robot.keyRelease(KeyEvent.VK_A);
            robot.keyRelease(KeyEvent.VK_CONTROL);

            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_C);
            robot.keyRelease(KeyEvent.VK_C);
            robot.keyRelease(KeyEvent.VK_CONTROL);

            permanentAddress.click();

            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);

        } catch (AWTException e) {
            e.printStackTrace();
        }
        return this;
    }

    public TextBoxPage verifyCopyPasteAddress() {
        String currentAddressText = currentAddress.getAttribute("value");
        String permanentAddressText = permanentAddress.getAttribute("value");

        if (!currentAddressText.equals(permanentAddressText)) {
            throw new AssertionError("The Permanent Address does not match the Current Address");
        }
        return this;
    }

    public TextBoxPage selectTextBox() {

        WebElement textBoxMenu = driver.findElement(By.xpath("//span[.='Text Box']"));
        textBoxMenu.click();
        return this;
    }
}
