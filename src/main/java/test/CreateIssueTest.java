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
        mainPage.login(System.getenv("USER"),System.getenv("PASSWORD"));
    }

    @Test
    void createIssueHappyWay(){
        homePage.waitForLoad();
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

}