package demoqa.tests;

import demoqa.core.TestBase;
import demoqa.pages.HomePage;
import demoqa.pages.PracticeFormPage;
import demoqa.pages.SidePage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class PracticeFormTests extends TestBase {

    @BeforeMethod
    public void precondition() {
        new HomePage(driver).getForms().hideAds();
        new SidePage(driver).selectPracticeForm().hideAds();
    }

    @Test
    public void practiceFormTest() {
        new PracticeFormPage(driver)
                .enterPersonalData(
                        "Ben", "Gibbons", "max@gmail.com", "109876567890")
                .selectGender("Female")
                .chooseDateAsString("4 May 1965")
                .chooseDate("4", "May", "1965")
                .enterSubject(new String[]{"Maths", "English"})
                .chooseHobbies(new String[]{"Sports", "Music"})
                .uploadPicture("C:/Users/ovash/Desktop/сайт hr/4.png")
                .enterCurrentAddress("Portishead, Bristol, UK")
                .enterState("NCR")
//                .enterCity("Delhi")
//                .submitForm()
//                .verifySuccessRegistration("Thanks for submitting the form")
        ;
    }
}
