package org.example;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
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
        SoftAssert softAssert = new SoftAssert();

        WebElement bookNowButton = driver.findElement(By.className("wixui-button__label"));
        softAssert.assertTrue(bookNowButton.isDisplayed(), "Book Now button is not displayed");

        bookNowButton.click();

        String expected_URL = "https://ancabota09.wixsite.com/intern/booknow";
        String actual_URL = driver.getCurrentUrl();
        softAssert.assertEquals(actual_URL, expected_URL, "Book Now page is not loading");

        softAssert.assertAll();
    }

    @Test
    public void verifyExploreButton(){
        SoftAssert softAssert = new SoftAssert();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

        WebElement ExploreButton = driver.findElement(By.id("i6kl732v1label"));
        softAssert.assertTrue(ExploreButton.isDisplayed(), "Explore button is not displayed");

        ExploreButton.click();

        String expected_URL = "https://ancabota09.wixsite.com/intern/explore";
        String actual_URL = driver.getCurrentUrl();
        softAssert.assertEquals(actual_URL, expected_URL, "Explore page is not loading");

        // Verificare text afisat pe pagina incarcata
        WebElement explorePageText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#i6kvh3dl > p > span")));
        String explorePageWrongText = "I'm a paragraph. Click here to add your own text and edit me. It’s easy. " +
                "Just click “Edit Text” or double click me to add your own content and make changes to the font. " +
                "Feel free to drag and drop me anywhere you like on your page. " +
                "I’m a great place for you to tell a story and let your users know a little more about you.";
        softAssert.assertNotEquals(explorePageText.getText(), explorePageWrongText, "The text on the page is not suggestive");

        softAssert.assertAll();
    }

    @Test
    public void verifyRoomsButton(){
        SoftAssert softAssert = new SoftAssert();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

        WebElement roomsButton = driver.findElement(By.id("i6kl732v2label"));
        softAssert.assertTrue(roomsButton.isDisplayed(), "The Rooms Button is not displayed.");

        roomsButton.click();

        String expected_URL = "https://ancabota09.wixsite.com/intern/rooms";
        String actual_URL = driver.getCurrentUrl();
        softAssert.assertEquals(actual_URL, expected_URL, "Rooms page is not loading");

        // Verificare text afisat pe pagina incarcata
        WebElement roomsPageText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#i6lwrp17 > p > span")));
        String roomsPageWrongText = "I'm a paragraph. Click here to add your own text and edit me. It’s easy. " +
                "Just click “Edit Text” or double click me to add your own content and make changes to the font. " +
                "Feel free to drag and drop me anywhere you like on your page. " +
                "I’m a great place for you to tell a story and let your users know a little more about you.";
        softAssert.assertNotEquals(roomsPageText.getText(), roomsPageWrongText, "The text on the page is not suggestive");

        softAssert.assertAll();
    }

    @Test
    public void verifyContactButton(){
        SoftAssert softAssert = new SoftAssert();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

        WebElement ContactButton = driver.findElement(By.id("i6kl732v3label"));
        softAssert.assertTrue(ContactButton.isDisplayed(), "Contact button is not displayed");

        ContactButton.click();

        String expected_URL = "https://ancabota09.wixsite.com/intern/contact";
        String actual_URL = driver.getCurrentUrl();
        softAssert.assertEquals(actual_URL, expected_URL, "Rooms page is not loading");

        // Verificare text afisat pe pagina incarcata
        WebElement contactPageText1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"i6ly3ckc_0\"]/p[1]")));
        WebElement contactPageText2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"i6ly3ckc_0\"]/p[2]")));
        String contactPageCorrectText1 = "If you have any questions, please contact us by telephone or email and we'll get back to you as soon as possible.";
        String contactPageCorrectText2 = "We look forward to hearing from you.";
        softAssert.assertEquals(contactPageText1.getText(), contactPageCorrectText1, "The text on the page is not suggestive");
        softAssert.assertEquals(contactPageText2.getText(), contactPageCorrectText2, "The text on the page is not suggestive");

        softAssert.assertAll();
    }

    @Test
    public void verifyHomeButton(){
        SoftAssert softAssert = new SoftAssert();

        WebElement HomeButton = driver.findElement(By.id("i6kl732v0label"));
        softAssert.assertTrue(HomeButton.isDisplayed(), "Home Button is not displayed");

        HomeButton.click();

        String expected_URL = "https://ancabota09.wixsite.com/intern";
        String actual_URL = driver.getCurrentUrl();
        softAssert.assertEquals(actual_URL, expected_URL, "Home page is not loading");

        softAssert.assertAll();
    }

    @Test
    public void verifyHomeAndAwayButton(){
        SoftAssert softAssert = new SoftAssert();

        WebElement HomeAndAwayButton = driver.findElement(By.cssSelector("#i6ksxrtk > h1 > a"));
        softAssert.assertTrue(HomeAndAwayButton.isDisplayed(), "Home And Away Button is not displayed");

        HomeAndAwayButton.click();

        String expected_URL = "https://ancabota09.wixsite.com/intern";
        String actual_URL = driver.getCurrentUrl();
        softAssert.assertEquals(actual_URL, expected_URL, "Home page is not loading");

        softAssert.assertAll();
    }

    @Test
    public  void verifySearchButton(){
        SoftAssert softAssert = new SoftAssert();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement searchFrame = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#i6kppi75 > iframe")));
        driver.switchTo().frame(searchFrame);

        WebElement SearchButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button.s-button")));
        softAssert.assertTrue(SearchButton.isDisplayed(), "Search Button is not displayed");

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

        driver.switchTo().defaultContent();

        driver.switchTo().frame(searchFrame);
        WebElement adultsButtonIncr = driver.findElement(By.cssSelector("#adults > .up"));
        adultsButtonIncr.click();

        WebElement childrenButtonIncr = driver.findElement(By.cssSelector("#children > .up"));
        childrenButtonIncr.click();

        SearchButton.click();
        driver.switchTo().defaultContent();

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

    @Test
    public void verifyFacebookButton(){
        SoftAssert softAssert = new SoftAssert();

        WebElement facebookButton = driver.findElement(By.id("i0odz-i6rlbitx"));
        softAssert.assertTrue(facebookButton.isDisplayed(), "Facebook Button is not displayed");

        facebookButton.click();

        String originalWindowHandle = driver.getWindowHandle();
        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(originalWindowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }

        String expected_URL = "https://www.facebook.com/wix";
        String actual_URL = driver.getCurrentUrl();
        softAssert.assertEquals(actual_URL, expected_URL, "Facebook page is not loading");

        softAssert.assertAll();
    }

    @Test
    public void verifyTwitterButton(){
        SoftAssert softAssert = new SoftAssert();

        WebElement twitterButton = driver.findElement(By.id("i220sc-i6rlbitx"));
        softAssert.assertTrue(twitterButton.isDisplayed(), "Twitter Button is not displayed");

        twitterButton.click();

        String originalWindowHandle = driver.getWindowHandle();
        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(originalWindowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }

        String expected_URL = "https://x.com/wix";
        String actual_URL = driver.getCurrentUrl();
        softAssert.assertEquals(actual_URL, expected_URL, "Twitter page is not loading");

        softAssert.assertAll();
    }

    @Test
    public void verifyPinterestButton(){
        SoftAssert softAssert = new SoftAssert();

        WebElement pinterestButton = driver.findElement(By.id("i3175p-i6rlbitx"));
        softAssert.assertTrue(pinterestButton.isDisplayed(), "Pinterest Button is not displayed");

        pinterestButton.click();

        String originalWindowHandle = driver.getWindowHandle();
        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(originalWindowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }

        String expected_URL = "https://www.pinterest.com/wixcom/";
        String actual_URL = driver.getCurrentUrl();
        softAssert.assertEquals(actual_URL, expected_URL, "Pinterest page is not loading");

        softAssert.assertAll();
    }

    @Test
    public void verifyWixButton(){
        SoftAssert softAssert = new SoftAssert();

        WebElement wixButton = driver.findElement(By.linkText("Wix.com"));
        softAssert.assertTrue(wixButton.isDisplayed(), "Wix Button is not displayed");

        wixButton.click();

        String originalWindowHandle = driver.getWindowHandle();
        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(originalWindowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }

        String expected_URL = "https://www.wix.com/?utm_campaign=vir_created_with";
        String actual_URL = driver.getCurrentUrl();
        softAssert.assertEquals(actual_URL, expected_URL, "Wix page is not loading");

        softAssert.assertAll();
    }

    @Test
    public void verifyMailToButton(){
        SoftAssert softAssert = new SoftAssert();

        WebElement mailToButton = driver.findElement(By.linkText("info@mysite.com"));
        softAssert.assertTrue(mailToButton.isDisplayed(), "Mail To Button is not displayed");

        String expected_href = "mailto:info@mysite.com";
        String actual_href = mailToButton.getAttribute("href");
        softAssert.assertEquals(actual_href, expected_href, "Mail to page is not loading");

        softAssert.assertAll();
    }

}
