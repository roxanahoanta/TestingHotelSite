package org.example;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import java.util.concurrent.TimeUnit;
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

public class HomePageTest {
    private WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new ChromeDriver();
        driver.get("https://ancabota09.wixsite.com/intern");
        driver.manage().window().maximize();
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }


    @Test
    public void verifyBookNowButton() {

//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        WebElement bookNowButton = driver.findElement(By.className("wixui-button__label"));

        Assert.assertTrue(bookNowButton.isDisplayed());

        bookNowButton.click();

        String expected_URL = "https://ancabota09.wixsite.com/intern/booknow";
        String actual_URL = driver.getCurrentUrl();

        Assert.assertEquals(actual_URL, expected_URL, "Book Now page is not loading");

        //String text = search_button.getAttribute("value");

    }

    @Test
    public void verifyExploreButton(){

        WebElement ExploreButton = driver.findElement(By.id("i6kl732v1label"));

        Assert.assertTrue(ExploreButton.isDisplayed());

        ExploreButton.click();

        String expected_URL = "https://ancabota09.wixsite.com/intern/explore";
        String actual_URL = driver.getCurrentUrl();

        Assert.assertEquals(actual_URL, expected_URL, "Explore page is not loading");
    }

    @Test
    public void verifyRoomsButton(){

        WebElement RoomsButton = driver.findElement(By.id("i6kl732v2label"));

        Assert.assertTrue(RoomsButton.isDisplayed(), "The Book Now button is not displayed.");

        RoomsButton.click();

        String expected_URL = "https://ancabota09.wixsite.com/intern/rooms";
        String actual_URL = driver.getCurrentUrl();

        Assert.assertEquals(actual_URL, expected_URL, "Rooms page is not loading");
    }

    @Test
    public void verifyContactButton(){

        WebElement ContactButton = driver.findElement(By.id("i6kl732v3label"));

        Assert.assertTrue(ContactButton.isDisplayed());

        ContactButton.click();

        String expected_URL = "https://ancabota09.wixsite.com/intern/contact";
        String actual_URL = driver.getCurrentUrl();

        Assert.assertEquals(actual_URL, expected_URL, "Rooms page is not loading");
    }

    @Test
    public void verifyHomeButton(){

        WebElement HomeButton = driver.findElement(By.id("i6kl732v0label"));

        Assert.assertTrue(HomeButton.isDisplayed());

        HomeButton.click();

        String expected_URL = "https://ancabota09.wixsite.com/intern";
        String actual_URL = driver.getCurrentUrl();

        Assert.assertEquals(actual_URL, expected_URL, "Home page is not loading");
    }

    @Test
    public  void verifySearchButton(){
        SoftAssert softAssert = new SoftAssert();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement searchFrame = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#i6kppi75 > iframe")));
        driver.switchTo().frame(searchFrame);

        WebElement SearchButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button.s-button")));

        softAssert.assertTrue(SearchButton.isDisplayed());

        WebElement checkInButton = driver.findElement(By.id("check-in"));
        checkInButton.click();

        driver.switchTo().defaultContent();

        WebElement checkInFrame = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("U73P_q")));
        driver.switchTo().frame(checkInFrame);

        // Folosirea variabilei checkInDate pentru a selecta ziua din calendar
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d, EEEE MMMM yyyy", Locale.ENGLISH);
        String checkInDateLabel = today.format(formatter);

        // Construirea XPath-ului în funcție de formatul `aria-label`
        String xpathForCheckInDate = String.format("//button[@aria-label='%s']", checkInDateLabel);

        // Selectarea zilei de check-in
        WebElement checkInDayButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathForCheckInDate)));
        checkInDayButton.click();

        driver.switchTo().defaultContent(); // Revenire la conținutul principal

        // Asigurarea că data au fost setata corect
        driver.switchTo().frame(searchFrame);
        String checkInValue =  driver.findElement(By.id("check-in-value")).getText();
        softAssert.assertTrue(checkInValue.contains(checkInDateLabel.split(",")[0]), "Check-in date is not correct");
        driver.switchTo().defaultContent();

        // Comutare la iframe-ul calendarului de check-out
        WebElement checkOutFrame = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("U73P_q")));
        driver.switchTo().frame(checkOutFrame);

        // Se cauta prima data de check-out disponibila
        LocalDate nextDate = today.plusDays(1);
        WebElement availableCheckOutDayButton = null;

        while (availableCheckOutDayButton == null) {
            String nextDateLabel = nextDate.format(formatter);
            try {
                availableCheckOutDayButton = driver.findElement(By.xpath(
                        String.format("//button[@aria-label='%s' and not(@disabled)]", nextDateLabel)));
            } catch (Exception e) {
                // Dacă nu găsește elementul, trecem la următoarea zi
                nextDate = nextDate.plusDays(1);
            }
        }

        // Verific dacă data disponibilă este la o zi după check-in
        long daysDifference = java.time.temporal.ChronoUnit.DAYS.between(today, nextDate);
        softAssert.assertEquals(daysDifference, 1,
                "The first available date for check-out is not a day after the check-in date");

        // Selectează prima data de check-out găsită disponibila
        availableCheckOutDayButton.click();

        driver.switchTo().defaultContent(); // Revenire la contextul principal

        driver.switchTo().frame(searchFrame);
        WebElement adultsButtonIncr = driver.findElement(By.cssSelector("#adults > .up"));
        adultsButtonIncr.click();

        WebElement childrenButtonIncr = driver.findElement(By.cssSelector("#children > .up"));
        childrenButtonIncr.click();

        SearchButton.click();
        driver.switchTo().defaultContent(); // Revenire la contextul principal

        // Verificare pagina incarcata
        String expected_URL = "https://ancabota09.wixsite.com/intern/rooms";
        String actual_URL = driver.getCurrentUrl();
        softAssert.assertTrue( actual_URL.contains(expected_URL), "The page is not loaded correctly");

        // Verificare text afisat pe pagina incarcata
        WebElement roomsPageText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#i6lwrp17 > p > span")));
        String roomsPageWrongText = "I'm a paragraph. Click here to add your own text and edit me. It’s easy. " +
                "Just click “Edit Text” or double click me to add your own content and make changes to the font. " +
                "Feel free to drag and drop me anywhere you like on your page. " +
                "I’m a great place for you to tell a story and let your users know a little more about you.";

        softAssert.assertNotEquals(roomsPageText.getText(), roomsPageWrongText, "The text on the page is not suggestive");

        // Verificare butonul Rooms din navbar este evidentiat
        WebElement roomsButtonNavbar = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("i6kl732v2")));
        String expected_result = "menu selected  link";

        softAssert.assertEquals(roomsButtonNavbar.getAttribute("data-state"), expected_result, "Rooms button is not highlighted");

        softAssert.assertAll();

    }


}
