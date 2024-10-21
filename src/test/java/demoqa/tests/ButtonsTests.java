package demoqa.tests;

import demoqa.core.TestBase;
import demoqa.pages.ButtonsPage;
import demoqa.pages.HomePage;
import demoqa.pages.SidePage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ButtonsTests extends TestBase {

    @BeforeMethod
    public void precondition() {
        new HomePage(driver).getElements().hideAds();
        new SidePage(driver).selectButtons().hideAds();
    }

    @Test
    public void doubleClickButtonsTest(){
        new ButtonsPage(driver)
                .doubleClick()
                .verifyDoubleClickMessage("You have done a double click");
    }

    @Test
    public void rightClickButtonsTest() throws InterruptedException {
        new ButtonsPage(driver)
                .rightClickButton()
                .verifyRightClickMessage("You have done a right click");
    }

    @Test(invocationCount = 2)
    public void dynamicClickButtonTest(){
        new ButtonsPage(driver)
                .dynamicClickButton()
                .verifyDynamicClickMessage("You have done a dynamic click");
    }
}
