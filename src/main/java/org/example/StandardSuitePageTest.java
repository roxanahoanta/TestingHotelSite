package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

public class StandardSuitePageTest {
    private WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new ChromeDriver();
        driver.get("https://ancabota09.wixsite.com/intern/rooms");
        driver.manage().window().maximize();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

        WebElement bookFrame = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#i6klgqap_0 > iframe")));
        driver.switchTo().frame(bookFrame);

        WebElement moreInfoButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#content > div > div.content-body > div > ul > li:nth-child(1) > div > div.info > div.bottom > button > span")));

        moreInfoButton.click();
        driver.switchTo().defaultContent();

    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    @Test
    public void BackButtonTest(){

        SoftAssert softAssert = new SoftAssert();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

        WebElement bookFrame = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#i6klgqap_0 > iframe")));
        driver.switchTo().frame(bookFrame);

        WebElement backButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button.room-back")));

        // Verificare botonul este afisat
        softAssert.assertTrue(backButton.isDisplayed(), "Back Button is not displayed");
        backButton.click();
        driver.switchTo().defaultContent();

        // Verificare pagina incarcata
        String expected_URL = "https://ancabota09.wixsite.com/intern/rooms";
        String actual_URL = driver.getCurrentUrl();
        softAssert.assertTrue(actual_URL.contains(expected_URL), "The page is not loaded correctly");

        // Verificare text afisat pe pagina incarcata
        WebElement roomsPageText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#i6lwrp17 > p > span")));
        String roomsPageWrongText = "I'm a paragraph. Click here to add your own text and edit me. It’s easy. Just click “Edit Text” or double click me to add your own content and make changes to the font. Feel free to drag and drop me anywhere you like on your page. I’m a great place for you to tell a story and let your users know a little more about you.";

        softAssert.assertNotEquals(roomsPageText.getText(), roomsPageWrongText, "The text on the page is not suggestive");

        softAssert.assertAll();

    }

    @Test
    public void readOurPoliciesTest(){
        SoftAssert softAssert = new SoftAssert();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

        WebElement bookFrame = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#i6klgqap_0 > iframe")));
        driver.switchTo().frame(bookFrame);

        WebElement readOurPoliciesButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a.policies")));

        // Verificare botonul este afisat
        softAssert.assertTrue(readOurPoliciesButton.isDisplayed(), "Back Button is not displayed");
        readOurPoliciesButton.click();
        driver.switchTo().defaultContent();

        String originalWindowHandle = driver.getWindowHandle();
        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(originalWindowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }

        // Verificare pagina incarcata
        String expected_URL = "https://hotels.wixapps.net/";
        String actual_URL = driver.getCurrentUrl();
        System.out.println(actual_URL);
        softAssert.assertTrue( actual_URL.contains("https://hotels.wixapps.net/"), "The page is not loaded correctly");


        softAssert.assertAll();

    }

    @Test
    public void BookNowButtonTest() {
        SoftAssert softAssert = new SoftAssert();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

        WebElement bookFrame = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#i6klgqap_0 > iframe")));
        driver.switchTo().frame(bookFrame);

        WebElement bookNowButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.booknow > span")));

        // Verificare botonul este afisat
        softAssert.assertTrue(bookNowButton.isDisplayed(), "Back Button is not displayed");

        WebElement checkInDayButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("check-in")));
        checkInDayButton.click();

        // Găsirea calendarului activ (fără atributul hidden)
        WebElement activeCalendar = driver.findElement(By.xpath("//div[contains(@class, 'calendar-popup') and not(@hidden)]"));

        // Folosirea variabilei checkInDate pentru a selecta ziua din calendar
        LocalDate today = LocalDate.now();
        LocalDate checkInDate = today.plusDays(2);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d, EEEE MMMM yyyy", Locale.ENGLISH);
        String checkInDateLabel = checkInDate.format(formatter);

        // Găsirea si selectarea zilei corespunzătoare în calendarul activ
        WebElement checkInDay = activeCalendar.findElement(By.xpath(String.format(".//button[@aria-label='%s']", checkInDateLabel)));
        checkInDay.click();

        // Se cauta prima data de check-out disponibila
        LocalDate nextDate = checkInDate.plusDays(1);
        WebElement availableCheckOutDayButton = null;
        WebElement activeCalendarcheckout = driver.findElement(By.xpath("//div[contains(@class, 'calendar-popup') and not(@hidden)]"));

        while (availableCheckOutDayButton == null) {
            String nextDateLabel = nextDate.format(formatter);
            try {
                availableCheckOutDayButton = activeCalendarcheckout.findElement(By.xpath(String.format(".//button[@aria-label='%s' and not(@disabled)]", nextDateLabel)));
            } catch (Exception e) {
                // Dacă nu găsește elementul, trecem la următoarea zi
                nextDate = nextDate.plusDays(1);
            }
        }

        // Verific dacă data disponibilă este la o zi după check-in
        long daysDifference = java.time.temporal.ChronoUnit.DAYS.between(checkInDate, nextDate);
        softAssert.assertEquals(daysDifference, 1,
                "The first available date for check-out is not a day after the check-in date");

        // Selectează prima data de check-out găsită disponibila
        availableCheckOutDayButton.click();

        WebElement adultsButtonIncr = driver.findElement(By.cssSelector("#adults > .up"));
        adultsButtonIncr.click();

        bookNowButton.click();

        // Verificarea că URL-ul s-a schimbat corespunzător
        String expectedURL = "https://ancabota09.wixsite.com/intern/booknow";
        String actualURL = driver.getCurrentUrl();
        softAssert.assertEquals(actualURL, expectedURL, "The page is not loaded correctly");

        softAssert.assertAll();
    }
}


