package demoqa.tests;

import demoqa.core.TestBase;

import demoqa.pages.HomePage;
import demoqa.pages.SidePage;
import demoqa.pages.TextBoxPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.awt.*;

public class TextBoxTests extends TestBase {
    @BeforeMethod
    public void precondition() {
        new HomePage(driver).getElements().hideAds();
        new SidePage(driver).selectTextBox().hideAds();
    }

    @Test
    public void keyboardEventTest() throws AWTException {
        new TextBoxPage(driver)
                .enterPersonalData("Name", "eva@gmail.com","Germany, Berlin")
                .keyboardEvent()
                .verifyCopyPasteAddress()
        ;
    }
}
