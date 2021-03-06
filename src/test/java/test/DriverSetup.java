package test;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.sonatype.inject.Parameters;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.UUID;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class DriverSetup {
    WebDriver driver;
    String uniqueID = UUID.randomUUID().toString();

    @BeforeAll
    void setUp() throws MalformedURLException {
        DesiredCapabilities capability;
        if (System.getenv("BROWSER").equals("firefox")){
            capability = DesiredCapabilities.firefox();
            capability.setBrowserName("firefox");
        } else {
            capability = DesiredCapabilities.chrome();
            capability.setBrowserName("chrome");
        }
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
