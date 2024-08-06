package org.example;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Locale;
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

        WebElement searchFrame = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("iframe.nKphmK")));
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
        softAssert.assertTrue(checkInValue.contains(checkInDateLabel.split(",")[0]), "Data de check-in nu a fost setată corect.");
        driver.switchTo().defaultContent();

        // Comutare la iframe-ul calendarului de check-out
        WebElement checkOutFrame = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("U73P_q")));
        driver.switchTo().frame(checkOutFrame);

        // Folosirea variabilei checkOutDate pentru a selecta ziua din calendar
        LocalDate checkOutDate = today.plusDays(3);
        String checkOutDateLabel = checkOutDate.format(formatter);

        // Construirea dinamică a XPath-ului în funcție de formatul `aria-label`
        String xpathForCheckOutDate = String.format("//button[@aria-label='%s']", checkOutDateLabel);

        // Selectarea zilei de check-out
        WebElement checkOutDayButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathForCheckOutDate)));
        checkOutDayButton.click();

        driver.switchTo().defaultContent(); // Revenire la contextul principal

        // Asigurarea că datele au fost setate corect
        driver.switchTo().frame(searchFrame);
        String CheckOutValue = driver.findElement(By.id("check-out-value")).getText();
        softAssert.assertTrue(CheckOutValue.contains(checkOutDateLabel.split(",")[0]), "Data de check-out nu a fost setată corect.");

        WebElement adultsButtonIncr = driver.findElement(By.cssSelector("#adults > .up"));
        adultsButtonIncr.click();


        driver.switchTo().defaultContent(); // Revenire la contextul principal

        softAssert.assertAll();


    }

}
