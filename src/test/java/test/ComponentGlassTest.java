package test;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import page.ComponentPage;
import page.GlassPage;
import page.HomePage;
import page.MainPage;

public class ComponentGlassTest extends DriverSetup {
    MainPage mainPage;
    HomePage homePage;
    ComponentPage componentPage;
    GlassPage glassPage;

    @BeforeAll
    void pageSetup() {
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
    void editComponentTest() {
        componentPage.goToPage();
        componentPage.clickMenuButton("ToP test component");
        componentPage.clickEdit();
        componentPage.editComponent("ToP " + uniqueID);
        glassPage.goToPage();
        Assertions.assertEquals("ToP " + uniqueID, glassPage.getComponentTitle(componentPage.getComponentId()));
        componentPage.goToPage();
        componentPage.clickMenuButton("ToP " + uniqueID);
        componentPage.clickEdit();
        componentPage.editComponent("ToP test component");
        glassPage.goToPage();
        Assertions.assertEquals("ToP test component", glassPage.getComponentTitle(componentPage.getComponentId()));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/Components.csv", numLinesToSkip = 1)
    void createComponent(String name, String leader, String description, String assigneeType) {
        componentPage.goToPage();
        if (name != null) {
            name = name + uniqueID;
            componentPage.typeComponentName(name);
        } else {
            Assertions.assertTrue(componentPage.checkAddButtonIsDisable());
            return;
        }
        if (leader != null) {
            componentPage.typeComponentLeader(leader);
        }
        if (description != null) {
            componentPage.typeComponentDescription(description);
        }
        componentPage.typeComponentAssigneeType(assigneeType);
        componentPage.addNewComponent(name);

        glassPage.goToPage();
        Assertions.assertEquals(name, glassPage.getComponentTitle(componentPage.getComponentId()));
        if (leader != null) {
            Assertions.assertEquals(leader, glassPage.getComponentLeader(componentPage.getComponentId()));
        }
        if (description != null) {
            Assertions.assertEquals(description, glassPage.getComponentDescription(componentPage.getComponentId()));
        }
        switch (assigneeType) {
            case "Unassigned":
                Assertions.assertTrue(glassPage.checkUnassignedType(componentPage.getComponentId()));
                break;
            case "Component lead (User 1)":
                Assertions.assertEquals(glassPage.checkUser1LeadAssignee(componentPage.getComponentId()), leader);
                break;
            case "Component lead (Unassigned)":
                Assertions.assertTrue(glassPage.checkComponentLeadUnassignedType(componentPage.getComponentId()));
                break;
            case "Project default (Unassigned)":
                Assertions.assertTrue(glassPage.checkDefaultUnassignedType(componentPage.getComponentId()));
                break;
            case "Project lead (Administrator)":
                Assertions.assertTrue(glassPage.checkAdminLeadAssigneeType(componentPage.getComponentId()));
                break;
        }
        componentPage.goToPage();
        componentPage.clickMenuButton(name);
        componentPage.clickDelete();
        glassPage.goToPage();
        Assertions.assertEquals(glassPage.checkComponentsWithId(componentPage.getComponentId()), 0);
    }

    @Test
    void createComponentWithoutTitle(){
        componentPage.goToPage();
        componentPage.typeComponentDescription("description");
        componentPage.typeComponentAssigneeType("Unassigned");
        Assertions.assertTrue(componentPage.checkAddButtonIsDisable());
    }
}
