package test;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import page.HomePage;
import page.IssuePage;
import page.MainPage;

public class BrowseIssueTest extends DriverSetup{
    MainPage mainPage;
    HomePage homePage;
    IssuePage issuePage;

    @BeforeAll
    void pageSetup(){
        mainPage = new MainPage(driver);
        homePage = new HomePage(driver);
        issuePage = new IssuePage(driver);
        mainPage.goToMainPage();
        mainPage.login(System.getenv("USER"), System.getenv("PASSWORD"));
        homePage.waitForLoad();
    }
    @BeforeEach
    void goToHomePage(){
        homePage.goToPage();
    }

    @Test
    void browseIssue(){
        Assertions.assertTrue(issuePage.checkBrowseIssue("https://jira.codecool.codecanvas.hu/projects/MTP/issues"));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/checkIssues.csv", numLinesToSkip = 1)
    void checkAvailableIssues(String url, String title){
        Assertions.assertTrue(issuePage.checkAvailableIssue(url, title));
    }

//    @AfterAll
//    void logout(){
//        homePage.logout();
//    }
}
