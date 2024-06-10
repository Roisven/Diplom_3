import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import PageObjects.*;

import java.time.Duration;

public class LoginTests {
    private WebDriver driver;
    private LoginPage loginPage;
    @Before
    public void setUp(){
        // ChromeOptions options = new ChromeOptions();
        // options.setBinary("C:/Users/slik-/AppData/Local/Yandex/YandexBrowser/Application/browser.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }
    @Test
    @Step("Open site and login")
    @DisplayName("Login from HomePage")
    public void loginFromHomePage() {
        driver.get("https://stellarburgers.nomoreparties.site");
        HomePage homePage = new HomePage(driver);
        homePage.clickButtonPersonalAccount();

        loginPage = new LoginPage(driver);
        loginPage.login("aloxart@yandex.ru", "12345678");
    }
    @Test
    @Step("Open site and login")
    @DisplayName("Login from PersonalAccountPage")
    public void loginFromPersonalAccountPage() {
        driver.get("https://stellarburgers.nomoreparties.site");
        HomePage homePage = new HomePage(driver);
        homePage.clickButtonPersonalAccount();
        PersonalAccountPage personalAccountPage = new PersonalAccountPage(driver);

        loginPage = new LoginPage(driver);
        loginPage.login("aloxart@yandex.ru", "12345678");
    }
    //вход через страницу регистрации
    @Test
    @Step("Open site and login")
    @DisplayName("Login from RegistrationPage")
    public void loginfromRegistrationPage(){
        driver.get("https://stellarburgers.nomoreparties.site/register");
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.clickButtonEnter();

        loginPage = new LoginPage(driver);
        loginPage.login("aloxart@yandex.ru", "12345678");
    }
    @After
    public void tearDown() {
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isDisplayedCheckoutButton());
        driver.quit();
    }
}