package test;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import page.CreateModalPage;
import page.HomePage;
import page.IssuePage;
import page.MainPage;

public class EditIssueTest extends DriverSetup {
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
        mainPage.login(System.getenv("USER"), System.getenv("PASSWORD"));
        homePage.waitForLoad();
    }

    @BeforeEach
    void goToHomePage(){
        homePage.goToPage();
    }

    @Test
    void editIssueInline() {
        mainPage.goToPageAndWait("https://jira.codecool.codecanvas.hu/browse/MTP-1431", issuePage.getIssueTitle());
        issuePage.editIssueTitle("Test Top");
        Assertions.assertEquals("Test Top", mainPage.getTextOfElement(issuePage.getIssueTitle()));
        issuePage.editIssueTitle("Can we edit issue? ToP");
    }

    @Test
    public void testEditThroughEditPage() {
        mainPage.goToPageAndWait("https://jira.codecool.codecanvas.hu/browse/MTP-1431", issuePage.getIssueTitle());
        issuePage.openEditModal();
        mainPage.waitForElement(issuePage.getSummary());
        issuePage.editIssueTitleThroughEditPage("Test Top");
        issuePage.clickOnUpdateButtonAndWaitForModalDisappear("Test Top");
        Assertions.assertEquals("Test Top", mainPage.getTextOfElement(issuePage.getIssueTitle()));
        issuePage.openEditModal();
        mainPage.waitForElement(issuePage.getSummary());
        issuePage.editIssueTitleThroughEditPage("Can we edit issue? ToP");
        issuePage.clickOnUpdateButtonAndWaitForModalDisappear("Can we edit issue? ToP");
    }

    @Test
    public void testEditIssueCancellation() {
        mainPage.goToPageAndWait("https://jira.codecool.codecanvas.hu/browse/MTP-1431", issuePage.getIssueTitle());
        issuePage.openEditModal();
        mainPage.waitForElement(issuePage.getIssueTitle());
        mainPage.waitForElement(issuePage.getSummary());
        issuePage.editIssueTitleThroughEditPage("Test Top");
        issuePage.clickOnCancelOnEditModal();
        Assertions.assertEquals("Can we edit issue? ToP", mainPage.getTextOfElement(issuePage.getIssueTitle()));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/resources/editableIssueData.csv", numLinesToSkip = 1)
    void isIssueEditable(String issuePagePath,String issueID) {
        issuePage.gotToIssueWithID(issuePagePath, issueID);
        Assertions.assertTrue(issuePage.editButtonIsAvailable());
    }
}