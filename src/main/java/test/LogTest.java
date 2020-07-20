package test;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import page.MainPage;
import page.ProfilPage;

public class LogTest implements DriverSetup{
    MainPage mainPage;
    ProfilPage profilPage;

    @BeforeAll
    void pageSetup(){
        if(mainPage == null){
            mainPage = new MainPage(driver);
        }
        if (profilPage == null){
            profilPage = new ProfilPage(driver);
        }
    }

    @Test
    void loginHappyWay(){
        mainPage.login(System.getenv("USER"), System.getenv("PASSWORD"));

    }
}
