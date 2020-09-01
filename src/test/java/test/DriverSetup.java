package test;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class DriverSetup {
    WebDriver driver;

    @BeforeAll
    void setUp() throws MalformedURLException {
        DesiredCapabilities capability = DesiredCapabilities.chrome();
        capability.setBrowserName("chrome");
        capability.setPlatform(Platform.LINUX);
        driver = new RemoteWebDriver(new URL(System.getenv("GRID_URL")),capability);
        driver.manage().window().maximize();
        driver.get(System.getenv("BASE_URL") + "/secure/Dashboard.jspa");
    }

    @AfterAll
    void close(){
        driver.quit();
    }
}
