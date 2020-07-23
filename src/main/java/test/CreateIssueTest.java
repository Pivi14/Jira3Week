package test;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import page.*;

public class CreateIssueTest implements DriverSetup {
    IssuePage issuePage;
    MainPage mainPage;
    HomePage homePage;
    CreateModalPage modalPage;
    SearchPage searchPage;

    @BeforeAll
    void pageSetup() {
        issuePage = new IssuePage(driver);
        mainPage = new MainPage(driver);
        homePage = new HomePage(driver);
        modalPage = new CreateModalPage(driver);
        searchPage = new SearchPage(driver);
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

    @ParameterizedTest
    @CsvFileSource(resources = "/issueTypes.csv", numLinesToSkip = 1)
    void availableIssueTypes(String projectID) throws InterruptedException {
        searchPage.goToPage();
        searchPage.setDefaultFilter();
        searchPage.clickOnCheckBoxByProjectID(projectID);
        Assertions.assertNotNull(searchPage.getBug()); // bug
        Assertions.assertNotNull(searchPage.getStory()); // story
        Assertions.assertNotNull(searchPage.getTask()); // task
        Assertions.assertNotNull(searchPage.getSubTask()); // sub-task
    }



    @BeforeEach
    void goToHomePage(){
        homePage.goToPage();
    }

    @AfterEach
    void goToHomePageAfter(){
        homePage.goToPage();
    }

}