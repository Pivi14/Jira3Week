package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.HashMap;

public class GlassPage extends WebPage{
    @FindBy(id = "glass-general-panel")
    WebElement glassPage;
    @FindBy(xpath = "//a[@data-target='permissions']")
    WebElement permissionsButton;

    public GlassPage(WebDriver driver) {
        super(driver);
    }

    public void goToPage(){
        goToPageAndWait("https://jira.codecool.codecanvas.hu/projects/PP1", glassPage);
    }

    public String getComponentTitle(String componentId){
        return driver.findElement(By.xpath("//tr[@data-component-id='" + componentId + "']/td[@class='components-table__name']/div/a")).getText();
    }

    public String getComponentLeader(String componentId){
        return driver.findElement(By.xpath("//tr[@data-component-id='" + componentId + "']/td[@class='components-table__lead']/div/a")).getAttribute("rel");
    }

    public String getComponentDescription(String componentId){
        return driver.findElement(By.xpath("//tr[@data-component-id='" + componentId + "']/td[@class='glass-components-table__description']/div")).getText();
    }

    public boolean checkUnassignedType(String componentId){
        return driver.findElement(By.xpath("//tr[@data-component-id='" + componentId + "']/td[@class='components-table__assignee']/div")).getText().equals("Unassigned");
    }

    public String checkUser1LeadAssignee(String componentId){
        return driver.findElement(By.xpath("//tr[@data-component-id='" + componentId + "']/td[@class='components-table__assignee']/a")).getAttribute("rel");
    }

    public boolean checkDefaultUnassignedType(String componentId){
        return driver.findElement(By.xpath("//tr[@data-component-id='" + componentId + "']/td[@class='components-table__assignee']/span")).getText().equals("Unassigned");
    }

    public boolean checkAdminLeadAssigneeType(String componentId){
        return driver.findElement(By.xpath("//tr[@data-component-id='" + componentId + "']/td[@class='components-table__assignee']/a")).getAttribute("rel").equals("Administrator");
    }

    public boolean checkComponentLeadUnassignedType(String componentId){
        boolean noLead = driver.findElement(By.xpath("//tr[@data-component-id='" + componentId + "']/td[@class='components-table__assignee']")).getText().contains("No lead");
        boolean adminLead = driver.findElement(By.xpath("//tr[@data-component-id='" + componentId + "']/td[@class='components-table__assignee']/div/a")).getAttribute("rel").equals("Administrator");
        return noLead && adminLead;
    }

    public Integer checkComponentsWithId(String componentId){
        return driver.findElements(By.xpath("//tr[@data-component-id='" + componentId + "']/td[@class='components-table__name']/div/a")).size();
    }

    public void clickPermissionsButton(){
        permissionsButton.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("glass-permissions-panel")));
    }

    public boolean getPermissionsInGlass(String key){
        return driver.findElement(By.xpath("//b[text()='" + key + "']/ancestor::tr/td[3]/div")).getAttribute("class").equals("glass-true-icon");
    }
}
