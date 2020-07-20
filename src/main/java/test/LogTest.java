package test;

import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import page.MainPage;

public class LogTest {
    String driverPath = System.getenv("WEB_DRIVER");
    WebDriver driver;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", driverPath);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(System.getenv("MAIN_PAGE"));
    }

    @Test
    public void loginHappyWay(){
        MainPage mainPage = new MainPage(driver);
        mainPage.login(System.getenv("USER_NAME"), System.getenv("PASSWORD"));
    }
}
