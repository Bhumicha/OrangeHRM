package pages;

import PageObject.LoginPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;


import java.time.Duration;

public class Search {
    private WebDriver driver;
    private LoginPage lp;

    @BeforeEach
    public void setUp() throws InterruptedException {
        driver = new ChromeDriver();
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
        lp = new LoginPage(driver);
        lp.loginToApplication("Admin", "admin123");
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }


    @Test
    public void VerifySearchElement() throws InterruptedException {
        driver.findElement(By.xpath("//i[@class='oxd-icon bi-list oxd-topbar-header-hamburger']"));
        driver.findElement(By.xpath("//input[@placeholder='Search']")).sendKeys("Admin");
        driver.findElement(By.xpath("//span[@class='oxd-text oxd-text--span oxd-main-menu-item--name'][normalize-space()='Admin']")).click();
        Thread.sleep(5000);
        String actualUrl="https://opensource-demo.orangehrmlive.com/web/index.php/admin/viewSystemUsers";
        String expectedUrl= driver.getCurrentUrl();
        Assertions.assertEquals(expectedUrl,actualUrl);
    }
    @Test
    public void a()
    {
        lp = new LoginPage(driver);
    }




}
