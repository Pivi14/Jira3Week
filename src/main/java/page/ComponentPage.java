package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ComponentPage extends WebPage{
    String componentId;

    @FindBy(id = "components-page")
    WebElement componentPage;

    public String getComponentId() {
        return componentId;
    }

    public ComponentPage(WebDriver driver) {
        super(driver);
    }

    public void goToPage(){
        goToPageAndWait("https://jira.codecool.codecanvas.hu/plugins/servlet/project-config/PP1/administer-components", componentPage);
    }

    public void clickMenuButton(String name){
        componentId = driver.findElement(By.xpath("//a[contains(., '" + name + "')]/ancestor::tr")).getAttribute("data-component-id");
        driver.findElement(By.xpath("//a[@href='#component-actions-" + componentId + "']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='component-actions-" + componentId + "']")));
    }

    public void clickEdit(){
        driver.findElement(By.xpath("//a[@id='editcomponent_" + componentId + "']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//form[@id='component-edit']")));
    }

    public void clickDelete(){
        driver.findElement(By.xpath("//a[@id='deletecomponent_" + componentId + "']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("component-" + componentId + "-delete-dialog")));
    }

    public void editComponent(String newName){
        driver.findElement(By.id("component-name")).sendKeys(newName);
        driver.findElement(By.id("component-save-submit")).submit();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//form[@id='component-edit']")));
    }


}
