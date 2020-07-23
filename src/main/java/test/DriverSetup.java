package test;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.chrome.ChromeDriver;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public interface DriverSetup {
    ChromeDriver driver = new ChromeDriver();

    @BeforeAll
    default void setUp() {
        driver.manage().window().maximize();
        driver.get("https://jira.codecool.codecanvas.hu/secure/Dashboard.jspa");
    }

//    @AfterAll
//    default void close(){
//        driver.close();
//    }
}
