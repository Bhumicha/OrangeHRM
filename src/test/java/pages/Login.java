package pages;

import PageObject.LoginPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Login {
    private WebDriver driver;
    private LoginPage lp;

    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("−−incognito");
        driver = new ChromeDriver(options);
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
        lp = new LoginPage(driver);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void verifyWithCorrectUserNameAndPassword() {
        lp.loginToApplication("Admin", "admin123");
        String actualTextFromApp = driver.findElement(lp.dashboardText).getText();
        Assertions.assertEquals("Dashboard", actualTextFromApp);
    }

    @Test
    public void verifyWithCorrectUserNameButWrongPassword() {
        lp.loginToApplication("Admin", "admin");
        String actualInvalidText = driver.findElement(lp.InvalidText).getText();
        Assertions.assertEquals("Invalid credentials", actualInvalidText);
    }

    @Test
    public void verifyWithBlankUserNameAndPassword() {
        lp.loginToApplication("", "");
        Assertions.assertEquals(driver.findElement(By.xpath("//body/div[@id='app']/div[1]/div[1]/div[1]/div[1]/div[2]/div[2]/form[1]/div[1]/div[1]/span[1]")).getText(), "Required");
        Assertions.assertEquals(driver.findElement(By.xpath("//body/div[@id='app']/div[1]/div[1]/div[1]/div[1]/div[2]/div[2]/form[1]/div[2]/div[1]/span[1]")).getText(), "Required");

    }
    @Test
    public void verifyWithIncorrectCaseUsername()
    {
        lp.loginToApplication("admin", "Admin123");
    }

    @Test
    public void verifyForgotPasswordFunctionality()
    {
        driver.findElement(By.xpath("//p[@class='oxd-text oxd-text--p orangehrm-login-forgot-header']")).click();
        String ActualForgotPasswordURL = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/requestPasswordResetCode";
        String ExpectedForgotPasswordURL = driver.getCurrentUrl();
        Assertions.assertEquals(ExpectedForgotPasswordURL,ActualForgotPasswordURL);
    }

}


