package page;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ComponentPage extends WebPage{
    String componentId;
    @FindBy(id = "components-page")
    WebElement componentPage;
    @FindBy(xpath = "//input[@placeholder='Component name']")
    WebElement componentName;
    @FindBy(id = "leadUserName-field")
    WebElement componentLeader;
    @FindBy(xpath = "//input[@placeholder='Description (optional)']")
    WebElement componentDescription;
    @FindBy(id = "assigneeType-field")
    WebElement componentAssigneeType;
    @FindBy(xpath = "//div[@class='components-add__confirm']/button")
    WebElement componentAddButton;

    public String getComponentId() {
        return componentId;
    }

    public ComponentPage(WebDriver driver) {
        super(driver);
    }

    public void goToPage(){
        driver.get("https://jira.codecool.codecanvas.hu/plugins/servlet/project-config/PP1/administer-components");
        acceptAlertBox();
        wait.until(ExpectedConditions.visibilityOf(componentPage));
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
        driver.findElement(By.id("submit")).click();
    }

    public void editComponent(String newName){
        driver.findElement(By.id("component-name")).sendKeys(newName);
        driver.findElement(By.id("component-save-submit")).submit();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//form[@id='component-edit']")));
    }

    public void typeComponentName(String name){
        componentName.sendKeys(name);
    }

    public void typeComponentLeader(String leader){
        componentLeader.sendKeys(leader);
        wait.until(ExpectedConditions.attributeToBe(By.xpath("//div[@id='leadUserName-single-select']"), "data-query", leader));
        componentLeader.sendKeys(Keys.ENTER);
    }

    public void typeComponentDescription(String description){
        componentDescription.sendKeys(description);
    }

    public void typeComponentAssigneeType(String assigneeType){
        componentAssigneeType.sendKeys(assigneeType);
        componentAssigneeType.sendKeys(Keys.ENTER);
    }

    public void addNewComponent(String name){
        componentAddButton.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(., '" + name + "')]/ancestor::tr")));
        componentId = driver.findElement(By.xpath("//a[contains(., '" + name + "')]/ancestor::tr")).getAttribute("data-component-id");
    }

    public boolean checkAddButtonIsDisable(){
        return componentAddButton.getAttribute("disabled").equals("true");
    }


}
