package test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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

    @Test
    void browseMainProject(){
        projectPage.goToPage();
        Assertions.assertTrue(projectPage.mainPageAppears());
    }

}
