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

public class ContactPage {
    private WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new ChromeDriver();
        driver.get("https://ancabota09.wixsite.com/intern/contact");
        driver.manage().window().maximize();
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    @Test
    public void submitButtonTest() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

        String name = "Roxana";
        String email = "roxana@yahoo.com";
        String phone = "0123456789";
        String message = "I have a problem";

        WebElement nameLabel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("input_comp-jxbsa1e9")));
        nameLabel.sendKeys(name);

        WebElement emailLabel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("input_comp-jxbsa1em")));
        emailLabel.sendKeys(email);

        WebElement phoneLabel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("input_comp-jxbsa1ev")));
        phoneLabel.sendKeys(phone);

        WebElement messageLabel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("textarea_comp-jxbsa1f7")));
        messageLabel.sendKeys(message);

        softAssert.assertEquals(name, nameLabel.getAttribute("value"), "The name is incorrect");
        softAssert.assertEquals(email, emailLabel.getAttribute("value"), "The email is incorrect");
        softAssert.assertEquals(phone, phoneLabel.getAttribute("value"), "The phone is incorrect");
        softAssert.assertEquals(message, messageLabel.getAttribute("value"), "The message is incorrect");

        String initialText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[id=\"comp-jxbsa1fv\"] > p > span"))).getText();

        WebElement submitButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[id=\"comp-jxbsa1fi\"]")));
        submitButton.click();

        String afterSubmitText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[id=\"comp-jxbsa1fv\"] > p > span"))).getText();

        softAssert.assertNotEquals(initialText, afterSubmitText, "\"Thanks for submitting\" message was not received");

        softAssert.assertAll();
    }

    @Test
    public void fullscreenButtonTest(){
        SoftAssert softAssert = new SoftAssert();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

        WebElement mapFrame = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("iframe[title = \"Google Maps\"]")));
        driver.switchTo().frame(mapFrame);

        WebElement fullscreenButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button[title = \"Toggle fullscreen view\"]")));
        softAssert.assertTrue(fullscreenButton.isDisplayed(), "Fullscreen button is not displayed");

        fullscreenButton.click();

        String mapAttribute = fullscreenButton.getAttribute("aria-pressed");

        softAssert.assertTrue(mapAttribute == "true", "The fullscreen map is NOT loaded successfully");
    }


}
