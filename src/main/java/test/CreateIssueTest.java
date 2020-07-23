package test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import page.CreateModalPage;
import page.HomePage;
import page.IssuePage;
import page.MainPage;

public class CreateIssueTest implements DriverSetup {
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
    void createIssueHappyWay(){
        mainPage.goToPageAndWait("https://jira.codecool.codecanvas.hu/projects/MTP/issues",issuePage.getOpenIssueTitle());
        homePage.clickOnCreateIssueButton();
        modalPage.waitForElement(modalPage.getCreateIssueSubmitButton());
        modalPage.addSummary("TestersOfPuppets CreateTest Issue");
        modalPage.submitIssue();
        modalPage.catchPopupBox();
        modalPage.waitForElement(issuePage.getIssueTitle());
        Assertions.assertEquals(modalPage.getTextOfElement(issuePage.getIssueTitle()),"TestersOfPuppets CreateTest Issue");
        issuePage.deleteIssue();
    }

    @Test
    void createIssueFail(){
        mainPage.goToPageAndWait("https://jira.codecool.codecanvas.hu/projects/EMPTY/summary",issuePage.getSummaryIssueTitle());
        homePage.clickOnCreateIssueButton();
        modalPage.waitForElement(modalPage.getCreateIssueSubmitButton());
        modalPage.submitIssue();
        modalPage.waitForElement(modalPage.getErrorMassage());
        modalPage.cancelCreateIssue();
        modalPage.goToPageAndWait("https://jira.codecool.codecanvas.hu/projects/EMPTY/issues/?filter=allissues",issuePage.getOpenIssueTitle());
        Assertions.assertNotNull(issuePage.getEmptyIssues());
    }

    @Test
    void createIssueCancel(){
        mainPage.goToPageAndWait("https://jira.codecool.codecanvas.hu/projects/EMPTY/summary",issuePage.getSummaryIssueTitle());
        homePage.clickOnCreateIssueButton();
        modalPage.waitForElement(modalPage.getCreateIssueSubmitButton());
        modalPage.addSummary("TestersOfPuppets CreateTest Issue");
        modalPage.cancelCreateIssue();
        mainPage.goToPageAndWait("https://jira.codecool.codecanvas.hu/projects/EMPTY/issues",issuePage.getOpenIssueTitle());
        Assertions.assertNotNull(issuePage.getEmptyIssues());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/createIssue.csv", numLinesToSkip = 1)
    void createIssue(String project, String summary, String openIssuesPage){
        modalPage.goToPageAndWait(openIssuesPage,issuePage.getOpenIssueTitle());
        homePage.clickOnCreateIssueButton();
        modalPage.waitForElement(modalPage.getCreateIssueSubmitButton());
        modalPage.chooseProject(project);
        modalPage.addSummary(summary);
        modalPage.submitIssue();
        modalPage.goToPageAndWait(openIssuesPage,issuePage.getOpenIssueTitle());
        Assertions.assertEquals(summary,issuePage.getTextOfElement(issuePage.getIssueTitle()));
        issuePage.deleteIssue();
        homePage.goToPage();
    }



    @AfterEach
    void goToMainPage(){
        mainPage.goToPageAndWait("https://jira.codecool.codecanvas.hu/secure/Dashboard.jspa",homePage.getHomeTitle());
    }
}