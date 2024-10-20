package demoqa.tests;

import demoqa.core.TestBase;
import demoqa.pages.HomePage;
import demoqa.pages.LoginPage;
import demoqa.pages.SidePage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests extends TestBase {

    @BeforeMethod
    public void precondition() {
        new HomePage(driver).getBookStore().hideAds();
        new SidePage(driver).selectLogin().hideAds();
    }

    @Test
    public void loginPositiveTest() {
        new LoginPage(driver)
                .enterPersonalData("olga_786","qwerty1!")
                .clickOnLoginButton()
                .verifyUserName("olga_786");
    }
}
