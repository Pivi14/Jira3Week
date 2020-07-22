package test;

import org.junit.jupiter.api.AfterEach;
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
        mainPage.login(System.getenv("USER"),System.getenv("PASSWORD"));
        homePage.waitForLoad();
    }

    @Test
    void createIssueHappyWay(){
        mainPage.goToPageAndWait("https://jira.codecool.codecanvas.hu/projects/MTP",modalPage.getOpenIssueTitle());
        homePage.clickOnCreateIssueButton();
        modalPage.waitForElement(modalPage.getCreateIssueSubmitButton());
        modalPage.addSummary("TestersOfPuppets CreateTest Issue");
        modalPage.submitIssue();
        modalPage.catchPopupBox();
        modalPage.waitForElement(modalPage.getIssueTitle());
        Assertions.assertEquals(modalPage.getTextOfElement(modalPage.getIssueTitle()),"TestersOfPuppets CreateTest Issue");
        modalPage.deleteIssue();
    }

    @Test
    void createIssueFail(){
        mainPage.goToPageAndWait("https://jira.codecool.codecanvas.hu/projects/EMPTY/summary",modalPage.getSummaryTitle());
        homePage.clickOnCreateIssueButton();
        modalPage.waitForElement(modalPage.getCreateIssueSubmitButton());
        modalPage.submitIssue();
        modalPage.waitForElement(modalPage.getErrorMassage());
        modalPage.cancelCreateIssue();
        modalPage.goToPageAndWait("https://jira.codecool.codecanvas.hu/projects/EMPTY/issues/?filter=allissues",modalPage.getOpenIssueTitle());
        Assertions.assertNotNull(modalPage.getEmptyIssues());
    }

    @AfterEach
    void goToMainPage(){
        mainPage.goToPageAndWait("https://jira.codecool.codecanvas.hu/secure/Dashboard.jspa",homePage.getHomeTitle());
    }
}