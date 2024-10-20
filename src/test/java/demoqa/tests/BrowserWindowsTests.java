package demoqa.tests;

import demoqa.core.TestBase;
import demoqa.pages.BrowserWindowsPage;
import demoqa.pages.HomePage;
import demoqa.pages.SidePage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class BrowserWindowsTests extends TestBase {

    @BeforeMethod
    public void precondition() {
        new HomePage(driver).getAlertsFrameWindows().hideAds();
        new SidePage(driver).selectBrowserWindows().hideAds();
    }

    @Test
    public void neaTabWindowTest() {
        new BrowserWindowsPage(driver)
                .switchToNewTab(1)
                .verifyTabText("sample page");
    }

}
