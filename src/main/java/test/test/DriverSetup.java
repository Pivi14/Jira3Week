package test;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.chrome.ChromeDriver;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class DriverSetup {
    ChromeDriver driver = new ChromeDriver();

    @BeforeAll
    void setUp() {
        driver.manage().window().maximize();
        driver.get("https://jira.codecool.codecanvas.hu/secure/Dashboard.jspa");
    }

    @AfterAll
    void close(){
        driver.close();
    }
}
