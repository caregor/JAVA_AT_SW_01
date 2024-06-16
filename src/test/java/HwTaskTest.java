import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class HwTaskTest {
    static WebDriver driver;
    String username = "orangemouse807";
    String password = "a4f8fb1d99";
    String title = "QAA_Title_Post";

    @BeforeAll
    static void init(){
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-maxized");
        driver = new ChromeDriver(chromeOptions);
        driver.get("https://test-stand.gb.ru/login");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    void addNewGroup() {
        String preparedXPath = String.format("//a/h2[contains(text(), '%s')]", title);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement inputLogin = driver.findElement(By.xpath("//input[@type='text']"));
        WebElement inputPasswd = driver.findElement(By.xpath("//input[@type='password']"));
        WebElement submitButton = driver.findElement(By.xpath("//button[@type='submit']"));
        inputLogin.sendKeys(username);
        inputPasswd.sendKeys(password);
        submitButton.click();

        WebElement createButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@id='create-btn']")));
        createButton.click();
        WebElement inputTitle = driver.findElement(By.xpath("//input[@type='text']"));
        WebElement saveButton = driver.findElement(By.xpath("//button[@type='submit']"));
        inputTitle.sendKeys(title);
        saveButton.click();

        driver.navigate().back();

        List<WebElement> searchElement = driver.findElements(By.xpath(preparedXPath));
        Assertions.assertEquals(1, searchElement.size());

    }

    @AfterAll
    static void closeApp(){
        driver.quit();
    }
}
