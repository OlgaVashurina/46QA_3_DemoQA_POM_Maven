package demoqa.tests;

import demoqa.core.TestBase;
import demoqa.pages.AlertsPage;
import demoqa.pages.HomePage;
import demoqa.pages.SidePage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AlertsTests extends TestBase {

    @BeforeMethod
    public void precondition() {
        new HomePage(driver).getAlertsFrameWindows().hideAds();
        new SidePage(driver).selectAlerts().hideAds();
    }

    @Test
    public void waitAlertsTest(){
        new AlertsPage(driver).clickAlertWithTimer();
    }

    @Test
    public void alertWithSelectText(){
        new AlertsPage(driver)
                .clickOnConfirmButton()
                .selectResult("OK")
                .verifyResult("Ok");
    }

    @Test
    public void sendMessageToAlert(){
        new AlertsPage(driver)
                .clickOnPromptButton()
                .sendTextToAlert("Hello")
                .verifyAlertText("Hello");
    }
}
