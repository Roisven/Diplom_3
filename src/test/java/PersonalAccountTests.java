import io.qameta.allure.Step;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import io.qameta.allure.junit4.DisplayName;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import PageObjects.HomePage;
import PageObjects.LoginPage;
import PageObjects.PersonalAccountPage;

import java.time.Duration;


public class PersonalAccountTests {
    private WebDriver driver;
    private LoginPage loginPage;

    @Before
    @Step("Open site and login")
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();

        driver.get("https://stellarburgers.nomoreparties.site/");

        HomePage homePage = new HomePage(driver);
        homePage.clickButtonPersonalAccount();

        loginPage = new LoginPage(driver);
        loginPage.login("aaa1@ya.ru", "aaaaaa1");

        homePage.clickButtonPersonalAccount();
    }
    @Test
    @DisplayName("Logout")
    public void logOutOfAccount() {
        PersonalAccountPage personalAccountPage = new PersonalAccountPage(driver);
        personalAccountPage.clickButtonGetOutOfAccount();
        loginPage = new LoginPage(driver);
        Assert.assertTrue(loginPage.isDisplayedTextEnter());
    }
    @Test
    @DisplayName("Transition from Personal account to Constructor across click to \"Constructor\"")
    public void transitionAcrossConstructor(){
        PersonalAccountPage personalAccountPage = new PersonalAccountPage(driver);
        personalAccountPage.clickHeaderConstructor();
        Assert.assertTrue(personalAccountPage.isDisplayedTextAssembleBurger());
    }
    @Test
    @DisplayName("Transition from Personal account to Constructor across click to logo")
    public void transitionAcrossLogo(){
        PersonalAccountPage personalAccountPage = new PersonalAccountPage(driver);
        personalAccountPage.clickLogo();
        Assert.assertTrue(personalAccountPage.isDisplayedTextAssembleBurger());
    }
    @After
    public void tearDown() {
        driver.quit();
    }
}