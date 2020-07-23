package test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import page.CreateModalPage;
import page.HomePage;
import page.IssuePage;
import page.MainPage;

public class EditIssueTest implements DriverSetup {
    IssuePage issuePage;
    MainPage mainPage;
    HomePage homePage;
    CreateModalPage modalPage;

    @BeforeAll
    void pageSetup() {
        issuePage = new IssuePage(driver);
        mainPage = new MainPage(driver);
        homePage = new HomePage(driver);
        modalPage = new CreateModalPage(driver);
        mainPage.login(System.getenv("USER"),System.getenv("PASSWORD"));
        homePage.waitForLoad();
    }

    @Test
    void editIssueInline(){
        mainPage.goToPageAndWait("https://jira.codecool.codecanvas.hu/browse/MTP-1431",issuePage.getIssueTitle());
        issuePage.editIssueTitle("Test Top");
        Assertions.assertEquals("Test Top",mainPage.getTextOfElement(issuePage.getIssueTitle()));
        issuePage.editIssueTitle("Can we edit issue? ToP");
    }

    @AfterEach
    void goToMainPage(){
        mainPage.goToPageAndWait("https://jira.codecool.codecanvas.hu/secure/Dashboard.jspa",homePage.getHomeTitle());
    }
}