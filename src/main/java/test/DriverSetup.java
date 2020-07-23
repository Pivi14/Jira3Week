package test;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.chrome.ChromeDriver;

// Jelenleg nem zárja be az lapot, mert egy drivert használ az összes class. Nyithatok külön drivot mindegyiknek, de ahhoz abstract classnak kell lennie.
// Ebben az esetben valamiért "500-as hiba, valami baj történt a folyamat sorár" hibára futottam többször is. Túl instabil volt.
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public interface DriverSetup {
//    ChromeDriver driver;
    ChromeDriver driver = new ChromeDriver();

    @BeforeAll
    default void setUp() {
//        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://jira.codecool.codecanvas.hu/secure/Dashboard.jspa");
    }

//    @AfterAll
//    default void close(){
//        driver.close();
//    }
}
