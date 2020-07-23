package test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import page.*;

public class GlassTest implements DriverSetup{
    HomePage homePage;
    MainPage mainPage;
    GlassPage glassPage;
    PermissionPage permissionPage;
    IssueTypePage issueTypePage;

    @BeforeAll
    void pageSetup(){
        mainPage = new MainPage(driver);
        homePage = new HomePage(driver);
        glassPage = new GlassPage(driver);
        permissionPage = new PermissionPage(driver);
        issueTypePage = new IssueTypePage(driver);
        mainPage.goToMainPage();
        mainPage.login(System.getenv("USER"), System.getenv("PASSWORD"));
        homePage.waitForLoad();
    }

    @BeforeEach
    void goToHomePage(){
        homePage.goToPage();
    }

    @Test
    void testVerifyPermissionMatrix(){
        permissionPage.goToPage();
        permissionPage.savePermissionData();
        glassPage.goToPage();
        glassPage.clickPermissionsButton();
        Assertions.assertEquals(permissionPage.getPermissionFromProjectPage().get("Browse Projects"), glassPage.getPermissionsInGlass("Browse Projects"));
        Assertions.assertEquals(permissionPage.getPermissionFromProjectPage().get("Create Issues"), glassPage.getPermissionsInGlass("Create Issues"));
        Assertions.assertEquals(permissionPage.getPermissionFromProjectPage().get("Edit Issues"), glassPage.getPermissionsInGlass("Edit Issues"));
    }

    @Test
    void testIssueTypeSchemaInGlass(){
        issueTypePage.goToPage();
        issueTypePage.saveExceptIssueTypes();
        glassPage.goToPage();
        glassPage.saveActuallyIssueTypes();
        Assertions.assertEquals(issueTypePage.getExceptIssueTypes(), glassPage.getActualIssueTypes());
    }
}
