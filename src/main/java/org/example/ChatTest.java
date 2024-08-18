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

public class ChatTest {
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

    //functie de verificare a prezentei unui WebElement
    private boolean isElementVisible(WebDriverWait wait, By locator) {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    @Test
    public void ChatButtonTest() {
        SoftAssert softAssert = new SoftAssert();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

        // Comutare catre iframe Chat
        WebElement chatFrame = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#comp-jr4sqg2g > iframe")));
        driver.switchTo().frame(chatFrame);

        WebElement chatButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("minimized-chat")));
        softAssert.assertTrue(chatButton.isDisplayed(), "Chat button is not displayed");

        chatButton.click();

        // Se verifică aparitia ChatBox-ului
        boolean isChatBoxVisible = isElementVisible(wait, By.cssSelector("div[data-hook=\"input-wrapper\"]"));
        softAssert.assertTrue(isChatBoxVisible, "The Chat Box is not loading");

        softAssert.assertAll();
    }

    @Test
    public void sendButtonTest_firstTime() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

        WebElement chatFrame = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#comp-jr4sqg2g > iframe")));
        driver.switchTo().frame(chatFrame);

        WebElement chatButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("minimized-chat")));
        chatButton.click();

        //Tastarea unui mesaj in ChatBox
        String message = "Hello";
        WebElement textarea = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("textarea[data-hook=\"input\"]")));
        textarea.sendKeys(message);

        //Se verifica afisarea mesajului tastat
        String messageDisplayed = textarea.getText();
        softAssert.assertEquals(messageDisplayed, message, "The message is not correctly displayed");

        // Se verifică aparitia butonului Send
        boolean isSendButtonVisible = isElementVisible(wait, By.cssSelector("button[data-hook=\"send-button\"]"));
        softAssert.assertTrue(isSendButtonVisible, "Send Button is not displayed");

        // Click Send Button
        WebElement sendButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button[data-hook=\"send-button\"]")));
        sendButton.click();

        //Se verifica aparitia mesajului "Send" si prezenta mesajului trimis printre elementele "message-list-item"
        boolean isIndicationVisible = isElementVisible(wait, By.cssSelector("div[data-hook=\"indication-warpper\"]"));
        softAssert.assertTrue(isIndicationVisible, "\"Send\" indication is not displayed");

        boolean isMessageVisible = isElementVisible(wait, By.xpath("//*[ text() = \"Hello\"]"));
        softAssert.assertTrue(isMessageVisible, "The message is not send");

        Thread.sleep(5000);

        //Se verifica primirea unui raspuns de la robot prin numarul elementelor "message-list-item" din "message-list"
        List<WebElement> listItems = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("div[data-hook=\"message-list-item\"]")));
        softAssert.assertEquals(listItems.size(), 3, "Nu s-a primit un raspuns de la robot");

        softAssert.assertAll();
    }

    @Test
    public void sendButtonTest_secondTime() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

        sendButtonTest_firstTime();

        //Tastarea unui mesaj in ChatBox
        String message = "I have a problem";
        WebElement textarea = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("textarea[data-hook=\"input\"]")));
        textarea.sendKeys(message);

        //Se verifica afisarea mesajului tastat
        String messageDisplayed = textarea.getText();
        softAssert.assertEquals(messageDisplayed, message, "The message is not correctly displayed");

        // Se verifică aparitia butonului Send
        boolean isSendButtonVisible = isElementVisible(wait, By.cssSelector("button[data-hook=\"send-button\"]"));
        softAssert.assertTrue(isSendButtonVisible, "Send Button is not displayed");

        // Click Send Button
        WebElement sendButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button[data-hook=\"send-button\"]")));
        sendButton.click();

        //Se verifica aparitia mesajului "Send" si prezenta mesajului trimis printre elementele "message-list-item"
        boolean isIndicationVisible = isElementVisible(wait, By.cssSelector("div[data-hook=\"indication-warpper\"]"));
        softAssert.assertTrue(isIndicationVisible, "\"Send\" indication is not displayed");

        boolean isMessageVisible = isElementVisible(wait, By.xpath("//*[ text() = \"I have a problem\"]"));
        softAssert.assertTrue(isMessageVisible, "The message is not send");

        Thread.sleep(3000);

        //Se verifica primirea unui raspuns de la robot prin numarul elementelor "message-list-item" din "message-list"
        List<WebElement> listItems = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("div[data-hook=\"message-list-item\"]")));
        softAssert.assertEquals(listItems.size(), 5, "Nu s-a primit un raspuns de la robot pentru al doilea mesaj");

        softAssert.assertAll();
    }

    @Test
    public void submitButtonTest() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

        sendButtonTest_firstTime();

        //Completarea formularului
        String name = "Roxana";
        String email = "roxana@yahoo.com";
        String textMessage = "I have a problem";

        WebElement nameLabel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name")));
        nameLabel.sendKeys(name);

        WebElement emailLabel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
        emailLabel.sendKeys(email);

        WebElement messageLabel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
        messageLabel.sendKeys(textMessage);

        //Trimiterea formularului
        WebElement submitButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button[type=\"submit\"]")));
        submitButton.click();

        Thread.sleep(4000);

        //Se verifica aparitia unei notificari de trimitere cu succes a formularului
        boolean isIndicationVisible = isElementVisible(wait, By.xpath("//*[ text() = \"Thanks! Message us here.\"]"));
        softAssert.assertTrue(isIndicationVisible, "Form success notification is not displayed");

        //
    }
}
