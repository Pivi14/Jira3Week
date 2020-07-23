package test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import page.*;

public class VersionGlassTest implements DriverSetup{
    HomePage homePage;
    MainPage mainPage;
    GlassPage glassPage;
    VersionPage versionPage;

    @BeforeAll
    void pageSetup(){
        mainPage = new MainPage(driver);
        homePage = new HomePage(driver);
        glassPage = new GlassPage(driver);
        versionPage = new VersionPage(driver);
        mainPage.goToMainPage();
        mainPage.login(System.getenv("USER"), System.getenv("PASSWORD"));
        homePage.waitForLoad();
    }

    @BeforeEach
    void goToHomePage(){
        homePage.goToPage();
    }

    @Test
    void editVersionTest(){
        versionPage.goToPage();
        versionPage.clickMenuButton("ToP test version");
        versionPage.clickEditButton();
        versionPage.editVersionTitle("ToP");
        glassPage.goToPage();
        glassPage.clickVersionsButton();
        Assertions.assertEquals("ToP", glassPage.getVersionTitle(versionPage.getVersionId()));
        versionPage.goToPage();
        versionPage.clickMenuButton("ToP");
        versionPage.clickEditButton();
        versionPage.editVersionTitle("ToP test version");
        glassPage.goToPage();
        glassPage.clickVersionsButton();
        Assertions.assertEquals("ToP test version", glassPage.getVersionTitle(versionPage.getVersionId()));
    }
}
