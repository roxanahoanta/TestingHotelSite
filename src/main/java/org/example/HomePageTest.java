package org.example;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class HomePageTest {
    private WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new ChromeDriver();
        driver.get("https://ancabota09.wixsite.com/intern");
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }


    @Test
    public void verifyBookNowButton() {

//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        WebElement bookNowButton = driver.findElement(By.className("wixui-button__label"));

        bookNowButton.click();

        String expected_URL = "https://ancabota09.wixsite.com/intern/booknow";
        String actual_URL = driver.getCurrentUrl();

        Assert.assertEquals(actual_URL, expected_URL, "Book Now page is not loading");

        //String text = search_button.getAttribute("value");

        //Assert.assertEquals(text, search_text, "Text not found!");

    }

    @Test
    public void verifyExploreButton(){

        WebElement ExploreButton = driver.findElement(By.id("i6kl732v1label"));

        ExploreButton.click();

        String expected_URL = "https://ancabota09.wixsite.com/intern/explore";
        String actual_URL = driver.getCurrentUrl();

        Assert.assertEquals(actual_URL, expected_URL, "Explore page is not loading");
    }

    @Test
    public void verifyRoomsButton(){

        WebElement RoomsButton = driver.findElement(By.id("i6kl732v2label"));

        RoomsButton.click();

        String expected_URL = "https://ancabota09.wixsite.com/intern/rooms";
        String actual_URL = driver.getCurrentUrl();

        Assert.assertEquals(actual_URL, expected_URL, "Rooms page is not loading");
    }

    @Test
    public void verifyContactButton(){

        WebElement ContactButton = driver.findElement(By.id("i6kl732v3label"));

        ContactButton.click();

        String expected_URL = "https://ancabota09.wixsite.com/intern/contact";
        String actual_URL = driver.getCurrentUrl();

        Assert.assertEquals(actual_URL, expected_URL, "Rooms page is not loading");
    }

    @Test
    public void verifyHomeButton(){

        WebElement HomeButton = driver.findElement(By.id("i6kl732v0label"));

        HomeButton.click();

        String expected_URL = "https://ancabota09.wixsite.com/intern";
        String actual_URL = driver.getCurrentUrl();

        Assert.assertEquals(actual_URL, expected_URL, "Home page is not loading");
    }

    @Test
    public  void verifySearchButton(){

    }

}
