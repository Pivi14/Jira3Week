package test;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import page.HomePage;
import page.MainPage;
import page.ProjectPage;

public class ProjectTest implements DriverSetup{
    MainPage mainPage;
    HomePage homePage;
    ProjectPage projectPage;

    @BeforeAll
    void pageSetup(){
        mainPage = new MainPage(driver);
        homePage = new HomePage(driver);
        projectPage = new ProjectPage(driver);
        mainPage.goToMainPage();
        mainPage.login(System.getenv("USER"), System.getenv("PASSWORD"));
        homePage.waitForLoad();
    }

    @BeforeEach
    void goToHomePage(){
        homePage.goToPage();
    }

    @AfterEach
    void goToHomePageAfter(){
        homePage.goToPage();
    }

    @Test
    void browseMainProject(){
        projectPage.goToPage();
        Assertions.assertTrue(projectPage.mainPageAppears());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/availableProjects.csv", numLinesToSkip = 1)
    void availableProjects(String url, String title){
        Assertions.assertTrue(projectPage.checkAvailableProject(url, title));
    }


}
