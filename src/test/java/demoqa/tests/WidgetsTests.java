package demoqa.tests;

import demoqa.core.TestBase;
import demoqa.pages.HomePage;
import demoqa.pages.SidePage;
import demoqa.pages.WidgetsPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class WidgetsTests extends TestBase {

    @BeforeMethod
    public void precondition() {
        new HomePage(driver).getWidgets().hideAds();
        new SidePage(driver).selectSelectMenu().hideAds();
    }

    @Test
    public void oldStyleSelectMenuTest() {
        new WidgetsPage(driver).selectOldStyle("Indigo");
    }

    @Test
    public void multiSelectTest() {
        String[] colorsSelect = {"Green", "Blue"};
        new WidgetsPage(driver)
                .multiSelect(colorsSelect)
                .verifyColorSelected(colorsSelect);
    }

    //* 26 урок
    //? Выбор элемента по индексу
    @Test
    public void standardMultiSelectByIndexTest() {
        new WidgetsPage(driver)
                .standardMultiSelectByIndex(2)
                .verifyByIndex(2);
    }

    @Test
    public void standardMultiSelectByCarsTest() {
        String[] car = {"Volvo", "Opel", "Saab"};
        new WidgetsPage(driver)
                .standardMultiSelectByCars(car)
                .verifyMultiSelectByCars(car);
    }
}
