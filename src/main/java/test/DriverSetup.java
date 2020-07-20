package test;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.chrome.ChromeDriver;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public interface DriverSetup {
    String driverPath = System.getenv("WEB_DRIVER");
    ChromeDriver driver = new ChromeDriver();

    @BeforeAll
    default void setUp() {
        System.setProperty("webdriver.chrome.driver", driverPath);
        driver.manage().window().maximize();
        driver.get(System.getenv("MAIN_PAGE"));
    }

    @AfterAll
    default void close(){
        driver.close();
    }
}
