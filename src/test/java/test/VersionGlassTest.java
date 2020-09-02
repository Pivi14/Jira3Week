package test;

import org.junit.jupiter.api.*;
import page.*;

public class VersionGlassTest extends DriverSetup{
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
        versionPage.editVersionTitle("ToP " + uniqueID);
        glassPage.goToPage();
        glassPage.clickVersionsButton();
        Assertions.assertEquals("ToP " + uniqueID, glassPage.getVersionTitle(versionPage.getVersionId()));
        versionPage.goToPage();
        versionPage.clickMenuButton("ToP " + uniqueID);
        versionPage.clickEditButton();
        versionPage.editVersionTitle("ToP test version");
        glassPage.goToPage();
        glassPage.clickVersionsButton();
        Assertions.assertEquals("ToP test version", glassPage.getVersionTitle(versionPage.getVersionId()));
    }

    @Test
    void testCreateVersionWithoutName(){
        versionPage.goToPage();
        versionPage.typeVersionDescription("Description");
        Assertions.assertTrue(versionPage.getAddButtonIsDisable());
    }

    @Test
    void testCreateVersionWithMinimalData(){
        versionPage.goToPage();
        versionPage.typeVersionName("ToP Test create version " + uniqueID);
        versionPage.clickAddNewVersion("ToP Test create version " + uniqueID);
        glassPage.goToPage();
        glassPage.clickVersionsButton();
        Assertions.assertEquals("ToP Test create version " + uniqueID, glassPage.getVersionTitle(versionPage.getVersionId()));
        versionPage.goToPage();
        versionPage.clickMenuButton("ToP Test create version " + uniqueID);
        versionPage.clickDeleteButton();
        glassPage.goToPage();
        glassPage.clickVersionsButton();
        Assertions.assertEquals(0, glassPage.getSearchedVersionsNumber(versionPage.getVersionId()));
    }

    @Test
    void testCreateAndDeleteVersionFullData(){
        versionPage.goToPage();
        versionPage.typeVersionName("ToP Test version full data " + uniqueID);
        versionPage.typeVersionStartDate("01/Jun/20");
        versionPage.typeVersionReleaseDate("10/Jun/20");
        versionPage.typeVersionDescription("Description");
        versionPage.clickAddNewVersion("ToP Test version full data " + uniqueID);
        glassPage.goToPage();
        glassPage.clickVersionsButton();
        Assertions.assertEquals("ToP Test version full data " + uniqueID, glassPage.getVersionTitle(versionPage.getVersionId()));
        Assertions.assertEquals("01/Jun/20", glassPage.getVersionStartDate(versionPage.getVersionId()));
        Assertions.assertEquals("10/Jun/20", glassPage.getVersionReleaseDate(versionPage.getVersionId()));
        Assertions.assertEquals("Description", glassPage.getVersionDescription(versionPage.getVersionId()));
        versionPage.goToPage();
        versionPage.clickMenuButton("ToP Test version full data " + uniqueID);
        versionPage.clickDeleteButton();
        glassPage.goToPage();
        glassPage.clickVersionsButton();
        Assertions.assertEquals(0, glassPage.getSearchedVersionsNumber(versionPage.getVersionId()));
    }


}
