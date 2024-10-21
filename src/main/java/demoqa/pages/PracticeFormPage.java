package demoqa.pages;

import demoqa.core.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PracticeFormPage extends BasePage {
    public PracticeFormPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "firstName")
    WebElement firstName;
    @FindBy(id = "lastName")
    WebElement lastName;
    @FindBy(id = "userEmail")
    WebElement userEmail;
    @FindBy(id = "userNumber")
    WebElement userNumber;

    public PracticeFormPage enterPersonalData(String name, String surName, String email, String number) {
        //firstName.sendKeys(name);
        type(firstName, name);
        type(lastName, surName);
        type(userEmail, email);
        type(userNumber, number);
        return this;
    }

    //    //label[contains(text(),'Other')]
    //    //label[contains(text(),'Male')]
    //    //label[contains(text(),'Female')]
    public PracticeFormPage selectGender(String gender) {
        try {
            String xpathGender = "//label[contains(text(),'" + gender + "')]";
            WebElement genderLocator = driver.findElement(By.xpath(xpathGender));
            click(genderLocator);
        } catch (NoSuchElementException e) {
            System.out.println("Gender element not found: [" + gender + "]");
            throw new NoSuchElementException(e.getMessage());
        } catch (Exception e) {
            System.out.println("Error selecting gender: [" + gender + "]");
            throw new RuntimeException(e);
        }
        return this;
    }

    @FindBy(id = "dateOfBirthInput")
    WebElement dateOfBirthInput;
    @FindBy(css = ".react-datepicker__month-select")
    WebElement monthSelect;
    @FindBy(css = ".react-datepicker__year-select")
    WebElement yearSelect;

    public PracticeFormPage chooseDate(String day, String month, String year) {
        click(dateOfBirthInput);
        new Select(monthSelect).selectByVisibleText(month);
        new Select(yearSelect).selectByVisibleText(year);
//div[@class='react-datepicker__week']//div[.='4']
        driver.findElement(By.xpath("//div[@class='react-datepicker__week']//div[.='" + day + "']")).click();
        return this;
    }

    @FindBy(id = "subjectsInput")
    WebElement subjectsInput;

    public PracticeFormPage enterSubject(String[] subjects) {
        for (String subject : subjects) {
            if (subject != null) {
                type(subjectsInput, subject);
                subjectsInput.sendKeys(Keys.ENTER);
            }
        }
        return this;
    }

    //label[.='Reading']
    public PracticeFormPage chooseHobbies(String[] hobbies) {
        for (String hobby : hobbies) {
            try {
                driver.findElement(By.xpath("//label[.='" + hobby + "']")).click();
            } catch (Exception e) {
                System.out.println("Error selecting hobbies: [" + hobby + "]");
                throw new RuntimeException(e);
            }
        }
        return this;
    }

    @FindBy(id = "uploadPicture")
    WebElement uploadPicture;

    public PracticeFormPage uploadPicture(String imgPath) {
        uploadPicture.sendKeys(imgPath);
        return this;
    }

    @FindBy(id = "currentAddress")
    WebElement currentAddress;

    public PracticeFormPage enterCurrentAddress(String address) {
        type(currentAddress, address);
        return this;
    }

    @FindBy(id = "state")
    WebElement stateContainer;

    @FindBy(id = "react-select-3-input")
    WebElement stateInput;

    public PracticeFormPage enterState(String state) {
        click(stateContainer);
        stateInput.sendKeys(state);
        stateInput.sendKeys(Keys.ENTER);
        return this;
    }

    public PracticeFormPage chooseDateAsString(String date) {
        String[] dateParts = date.split(" ");

         if (dateParts.length != 3) {
            throw new IllegalArgumentException("Некорректный формат даты. Ожидается 'день месяц год', например '04 May 1965'.");
        }

        String day = dateParts[0];
        String month = dateParts[1];
        String year = dateParts[2];

        // Убираем ведущий ноль, если он есть
        if (day.startsWith("0")) {
            day = day.substring(1);
        }

        // Открываем поле выбора даты
        click(dateOfBirthInput);

        // Явное ожидание появления выпадающего списка месяца
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(monthSelect));

        // Выбираем месяц
        new Select(monthSelect).selectByVisibleText(month);

        // Выбираем год
        new Select(yearSelect).selectByVisibleText(year);

        String dayXpath = String.format("//div[contains(@class, 'react-datepicker__day') and text()='%s' and not(contains(@class, 'outside-month'))]", day);
        WebElement dayElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(dayXpath)));
        dayElement.click();

        return this;
    }


}

