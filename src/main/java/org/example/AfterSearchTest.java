package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

public class AfterSearchTest {
    private WebDriver driver;

    @BeforeMethod
    public void beforeClass() {
        driver = new ChromeDriver();
        driver.get("https://ancabota09.wixsite.com/intern");
        driver.manage().window().maximize();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

        // Comutare la iframe-ul de search
        WebElement searchFrame = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#i6kppi75 > iframe")));
        driver.switchTo().frame(searchFrame);

        WebElement checkInButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("check-in")));
        checkInButton.click();
        driver.switchTo().defaultContent();

        // Comutare la iframe-ul calendarului de check-in
        WebElement checkInFrame = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("U73P_q")));
        driver.switchTo().frame(checkInFrame);

        // Folosirea variabilei checkInDate pentru a selecta ziua din calendar
        LocalDate checkInDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d, EEEE MMMM yyyy", Locale.ENGLISH);
        String checkInDateLabel = checkInDate.format(formatter);
        String xpathForCheckInDate = String.format("//button[@aria-label='%s']", checkInDateLabel);

        // Selectarea zilei de check-in
        WebElement checkInDayButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathForCheckInDate)));
        checkInDayButton.click();
        driver.switchTo().defaultContent();

        // Comutare la iframe-ul calendarului de check-out
        WebElement checkOutFrame = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("U73P_q")));
        driver.switchTo().frame(checkOutFrame);

        // Folosirea variabilei checkOutDate pentru a selecta ziua din calendar
        LocalDate checkOutDate = checkInDate.plusDays(3);
        String checkOutDateLabel = checkOutDate.format(formatter);
        String xpathForCheckOutDate = String.format("//button[@aria-label='%s']", checkOutDateLabel);

        // Selectarea zilei de check-out
        WebElement checkOutDayButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathForCheckOutDate)));
        checkOutDayButton.click();
        driver.switchTo().defaultContent();

        // Click buton Search
        driver.switchTo().frame(searchFrame);
        WebElement SearchButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button.s-button")));
        SearchButton.click();
        driver.switchTo().defaultContent();
    }

    @AfterMethod
    public void afterClass() {
        driver.quit();
    }


    @Test
    public void BookNowButtonTest_AllRooms(){
        SoftAssert softAssert = new SoftAssert();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

        // Comutare catre iframe Book a Room
        WebElement bookFrame = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#i6klgqap_0 > iframe")));
        driver.switchTo().frame(bookFrame);

        // Găsirea tuturor elementelor care reprezintă butoanele "Book Now"
        List<WebElement> bookNowButtons = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//span[contains(text(),'Book Now')]/ancestor::button")));

        // Iterăm prin fiecare buton "Book Now"
        for (WebElement bookNowButton : bookNowButtons) {
            // Găsirea titlului camerei părinte
            WebElement roomTitleElement = bookNowButton.findElement(By.xpath("../../../preceding-sibling::div[@class='description']/h3/a/span"));
            String roomTitle = roomTitleElement.getText();
            System.out.println("Verificare pentru camera: " + roomTitle);

            bookNowButton.click();

            // Verificarea că URL-ul s-a schimbat corespunzător
            String expectedURL = "https://ancabota09.wixsite.com/intern/booknow";
            String actualURL = driver.getCurrentUrl();
            softAssert.assertEquals(actualURL, expectedURL, "Pagina destinată nu s-a deschis corect pentru camera: " + roomTitle);
        }

        softAssert.assertAll();
    }

    @Test
    public void BookNowButtonTest_StandardSuite(){
        SoftAssert softAssert = new SoftAssert();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

        // Comutare catre iframe Book a Room
        WebElement bookFrame = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#i6klgqap_0 > iframe")));
        driver.switchTo().frame(bookFrame);

        //Click buton Book Now
        WebElement bookNowButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#content > div > div.content-body > div > ul > li:nth-child(1) > div > div.info > div.bottom > span > span > button")));
        bookNowButton.click();
        driver.switchTo().defaultContent(); // Revenire la contextul principal

        //Verificare URL
        String expected_URL = "https://ancabota09.wixsite.com/intern/booknow";
        String actual_URL = driver.getCurrentUrl();
        softAssert.assertEquals(actual_URL, expected_URL, "Book Now page for Standard Suite room is not loading");

        softAssert.assertAll();
    }

    @Test
    public void BookNowButtonTest_Cottage(){
        SoftAssert softAssert = new SoftAssert();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

        // Comutare catre iframe Book a Room
        WebElement bookFrame = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#i6klgqap_0 > iframe")));
        driver.switchTo().frame(bookFrame);

        //Click buton Book Now
        WebElement bookNowButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#content > div > div.content-body > div > ul > li:nth-child(2) > div > div.info > div.bottom > span > span > button")));
        bookNowButton.click();
        driver.switchTo().defaultContent(); // Revenire la contextul principal

        //Verificare URL
        String expected_URL = "https://ancabota09.wixsite.com/intern/booknow";
        String actual_URL = driver.getCurrentUrl();
        softAssert.assertEquals(actual_URL, expected_URL, "Book Now page for Cottage room is not loading");

        softAssert.assertAll();
    }

    @Test
    public void BookNowButtonTest_ClassicApp(){
        SoftAssert softAssert = new SoftAssert();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

        // Comutare catre iframe Book a Room
        WebElement bookFrame = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#i6klgqap_0 > iframe")));
        driver.switchTo().frame(bookFrame);

        //Click buton Book Now
        WebElement bookNowButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#content > div > div.content-body > div > ul > li:nth-child(3) > div > div.info > div.bottom > span > span > button")));
        bookNowButton.click();
        driver.switchTo().defaultContent(); // Revenire la contextul principal

        //Verificare URL
        String expected_URL = "https://ancabota09.wixsite.com/intern/booknow";
        String actual_URL = driver.getCurrentUrl();
        softAssert.assertEquals(actual_URL, expected_URL, "Book Now page for Classic App room is not loading");

        softAssert.assertAll();
    }

    @Test
    public void ClearButtonTest(){
        SoftAssert softAssert = new SoftAssert();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

        // Comutare catre iframe Book a Room
        WebElement bookFrame = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#i6klgqap_0 > iframe")));
        driver.switchTo().frame(bookFrame);

        //Valori initiale in "change dates" section
        WebElement initialCheckIn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("check_in-value")));
        WebElement initialCheckOut = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("check_out-value")));

        //Verificare buton Clear este afisat
        WebElement clearButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a.back")));
        softAssert.assertTrue(clearButton.isDisplayed(), "Book Now Button is not displayed");

        clearButton.click();

        //Verificare lista este afisata si contine toate camerele
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("list")));
        List<WebElement> rooms = driver.findElements(By.cssSelector(".list > li"));
        softAssert.assertEquals(rooms.size(), 3, "Numărul de camere afişate nu este corect.");

        // Verificare fiecare cameră are un preț afișat
        for (WebElement room : rooms) {
            WebElement priceElement = room.findElement(By.cssSelector(".list > li > div > div.info > div.price > span.value"));
            softAssert.assertTrue(priceElement.isDisplayed(), "Prețul nu este afișat pentru una dintre camere.");
        }

        //Verificare valorile din "change dates" section au fost resetate
        softAssert.assertFalse(initialCheckIn.isDisplayed(), "Check-in date was not reseted");
        softAssert.assertFalse(initialCheckOut.isDisplayed(), "Check-out date was not reseted");
        driver.switchTo().defaultContent(); // Revenire la contextul principal

        softAssert.assertAll();
    }

    @Test
    public void SearchAgainButtonTest(){
        SoftAssert softAssert = new SoftAssert();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

        // Comutare catre iframe Book a Room
        WebElement bookFrame = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#i6klgqap_0 > iframe")));
        driver.switchTo().frame(bookFrame);

        //Verificare buton Search Again este afisat
        WebElement searchAgainButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button[type='submit'] > span[stranslate='rooms.widget.SEARCH_AFTER']")));
        softAssert.assertNotEquals(searchAgainButton.getClass(),"ng-hide","Search Again Button is not displayed");

        WebElement checkInDayButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("check-in")));
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

        searchAgainButton.click();
        driver.switchTo().defaultContent(); // Revenire la contextul principal

        // Verificare pagina incarcata
        String expected_URL = "https://ancabota09.wixsite.com/intern/rooms";
        String actual_URL = driver.getCurrentUrl();
        softAssert.assertTrue( actual_URL.contains(expected_URL), "The page is not loaded correctly");
        softAssert.assertAll();
    }


}

