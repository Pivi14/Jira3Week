package test;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import page.HomePage;
import page.LoginPage;
import page.MainPage;
import page.ProfilPage;

public class LogTest implements DriverSetup{
    MainPage mainPage;
    ProfilPage profilPage;
    HomePage homePage;
    LoginPage loginPage;

    @BeforeAll
    void pageSetup(){
        mainPage = new MainPage(driver);
        profilPage = new ProfilPage(driver);
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
    }

    @BeforeEach
    void loadMainPage(){
        mainPage.goToMainPage();
    }

    @Test
    void loginHappyWay(){
        mainPage.login(System.getenv("USER"), System.getenv("PASSWORD"));
        homePage.waitForLoad();
        profilPage.goProfilPage();
        Assertions.assertEquals(System.getenv("USER"), profilPage.getUserName());
        homePage.logout();
    }

    @Test
    void loginThroughLoginPage(){
        loginPage.goToPage();
        loginPage.login(System.getenv("USER"), System.getenv("PASSWORD"));
        homePage.waitForLoad();
        profilPage.goProfilPage();
        Assertions.assertEquals(System.getenv("USER"), profilPage.getUserName());
        homePage.logout();
    }

    @Test
    void loginFailWithInvalidPassword(){
        mainPage.login(System.getenv("USER"), "FakePassword");
        Assertions.assertTrue(mainPage.errorMessageAppears());
    }


}
