package test;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import page.MainPage;

public class LogTest {
    private static final String driverPath = System.getenv("WEB_DRIVER");
    private static ChromeDriver driver;

    @BeforeAll
    static void setUp() {
        System.setProperty("webdriver.chrome.driver", driverPath);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(System.getenv("MAIN_PAGE"));
    }

    @Test
    public void loginHappyWay(){
        MainPage mainPage = new MainPage(driver);
        mainPage.login(System.getenv("USER"), System.getenv("PASSWORD"));
    }
}
