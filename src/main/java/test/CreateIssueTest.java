package test;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import page.CreateModalPage;
import page.HomePage;
import page.MainPage;

public class CreateIssueTest implements DriverSetup {
    MainPage mainPage;
    HomePage homePage;
    CreateModalPage modalPage;

    @BeforeAll
    void pageSetup() {
        mainPage = new MainPage(driver);
        homePage = new HomePage(driver);
        modalPage = new CreateModalPage(driver);
        mainPage.login(System.getenv("USERNAME"),System.getenv("PASSWORD"));
    }

    @Test
    void createIssueHappyWay(){
        mainPage.goToPageAndWait("https://jira.codecool.codecanvas.hu/projects/MTP/issues/MTP-288?filter=allopenissues",modalPage.getOpenIssueTitle());
        homePage.clickOnCreateIssueButton();
        modalPage.waitForCreateModal();
        modalPage.addSummary("TestersOfPuppets CreateTest Issue");
        modalPage.submitIssue();
    }

}