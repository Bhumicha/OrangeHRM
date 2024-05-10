package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private WebDriver driver;
    private String UserNameLocator = "username";
    private String PasswordLocator ="password";
    public By LoginbuttonLocator = By.xpath("//button[@type='submit']");
    public By dashboardText = By.cssSelector(".oxd-text--h6");
    public By InvalidText = By.xpath("//p[@class='oxd-text oxd-text--p oxd-alert-content-text']");

    public LoginPage(WebDriver mydriver) {
        driver = mydriver;
    }
    public void loginToApplication(String username, String password)
    {
        driver.findElement(By.name(UserNameLocator)).sendKeys(username);
        driver.findElement(By.name(PasswordLocator)).sendKeys(password);
        driver.findElement(LoginbuttonLocator).click();
    }
}
