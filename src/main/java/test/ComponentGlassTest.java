package test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import page.ComponentPage;
import page.GlassPage;
import page.HomePage;
import page.MainPage;

public class ComponentGlassTest implements DriverSetup{
    MainPage mainPage;
    HomePage homePage;
    ComponentPage componentPage;
    GlassPage glassPage;

    @BeforeAll
    void pageSetup(){
        mainPage = new MainPage(driver);
        homePage = new HomePage(driver);
        componentPage = new ComponentPage(driver);
        glassPage = new GlassPage(driver);
        mainPage.goToMainPage();
        mainPage.login(System.getenv("USER"), System.getenv("PASSWORD"));
        homePage.waitForLoad();
    }

    @BeforeEach
    void goToHomePage(){
        homePage.goToPage();
    }

    @Test
    void editComponentTest(){
        componentPage.goToPage();
        componentPage.clickMenuButton("ToP test component");
        componentPage.clickEdit();
        componentPage.editComponent("ToP");
        glassPage.goToPage();
        Assertions.assertEquals("ToP", glassPage.getComponentTitle(componentPage.getComponentId()));
        componentPage.goToPage();
        componentPage.clickMenuButton("ToP");
        componentPage.clickEdit();
        componentPage.editComponent("ToP test component");
        glassPage.goToPage();
        Assertions.assertEquals("ToP test component", glassPage.getComponentTitle(componentPage.getComponentId()));
    }

}
