package org.example;

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

import java.time.Duration;
import java.util.List;

public class RoomsPageTest {
    private WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new ChromeDriver();
        driver.get("https://ancabota09.wixsite.com/intern/rooms");
        driver.manage().window().maximize();
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }


    @Test
    public void MoreInfoButtonTest() throws InterruptedException {

        SoftAssert softAssert = new SoftAssert();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

        // Comutare catre iframe Book a Room
        WebElement bookFrame = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#i6klgqap_0 > iframe")));
        driver.switchTo().frame(bookFrame);

        // Găsirea tuturor butoanelor More Info
        List<WebElement> moreInfoButtons = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("div.bottom > button")));

        // Iterăm prin fiecare buton "More Info"
        for (WebElement moreInfoButton : moreInfoButtons) {
            // Găsirea titlului camerei părinte
            WebElement roomTitleElement = moreInfoButton.findElement(By.xpath("../preceding-sibling::div[@class='description']/h3/a/span"));
            String roomTitle = roomTitleElement.getText();
            System.out.println("Verificare pentru camera: " + roomTitle);

            moreInfoButton.click();

            // Verificarea că URL-ul s-a schimbat corespunzător
            String expectedURL = "https://ancabota09.wixsite.com/intern/rooms";
            String actualURL = driver.getCurrentUrl();
            softAssert.assertEquals(actualURL, expectedURL, "Pagina destinată nu s-a deschis corect pentru camera: " + roomTitle);

        }
    }

    @Test
    public void TitleButtonTest(){
        SoftAssert softAssert = new SoftAssert();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

        // Comutare catre iframe Book a Room
        WebElement bookFrame = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#i6klgqap_0 > iframe")));
        driver.switchTo().frame(bookFrame);

        // Găsirea tuturor elementelor care reprezintă titlurile camerelor
        List<WebElement> roomTitles = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".list > li.room > div > div.info > div.description > h3 > a > span")));

        // Iterăm prin fiecare titlu de cameră
        for (WebElement roomTitle : roomTitles) {
            String titleText = roomTitle.getText();
            System.out.println("Verificare pentru camera: " + titleText);

            roomTitle.click();

            // Verificarea că URL-ul s-a schimbat corespunzător
            String expectedURL = ("https://ancabota09.wixsite.com/intern/rooms");
            String actualURL = driver.getCurrentUrl();
            softAssert.assertEquals(actualURL, expectedURL, "Pagina destinată nu s-a deschis corect pentru camera: " + titleText);
        }

        softAssert.assertAll();
    }

    public void SearchButtonTest(){

    }
}
