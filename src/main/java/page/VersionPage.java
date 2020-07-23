package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class VersionPage extends WebPage{
    String versionId;
    @FindBy(id = "release-page")
    WebElement versionPage;
    @FindBy(xpath = "//input[@name='name']")
    WebElement versionName;
    @FindBy(xpath = "//input[@name='startDate']")
    WebElement versionStartDate;
    @FindBy(xpath = "//input[@name='releaseDate']")
    WebElement versionReleaseDate;
    @FindBy(xpath = "//input[@name='description']")
    WebElement versionDescription;
    @FindBy(xpath = "//button[contains(., 'Add')]")
    WebElement versionAddButton;

    public VersionPage(WebDriver driver) {
        super(driver);
    }

    public String getVersionId() {
        return versionId;
    }

    public void goToPage(){
        goToPageAndWait("https://jira.codecool.codecanvas.hu/plugins/servlet/project-config/PP1/administer-versions", versionPage);
    }

    public void clickMenuButton(String name){
        versionId = driver.findElement(By.xpath("//a[contains(., '" + name + "')]/ancestor::tr")).getAttribute("data-version-id");
        driver.findElement(By.xpath("//a[@href='#version-actions-" + versionId + "']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("version-actions-" + versionId)));
    }

    public void clickEditButton(){
        driver.findElement(By.xpath("//a[@href='/secure/VersionEdit!default.jspa?versionId=" + versionId + "']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("version-edit")));
    }

    public void clickDeleteButton(){
        driver.findElement(By.xpath("//div[@id='version-actions-" + versionId + "']/ul/li/a[@class='project-config-operations-delete']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("version-" + versionId + "-delete-dialog")));
        driver.findElement(By.id("submit")).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("version-" + versionId + "-delete-dialog")));
    }

    public void editVersionTitle(String newName){
        driver.findElement(By.id("version-name")).sendKeys(newName);
        driver.findElement(By.id("version-save-submit")).submit();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("version-edit")));
    }

    public void typeVersionName(String name){
        versionName.sendKeys(name);
    }

    public void typeVersionStatDate(String startDate){
        versionStartDate.sendKeys(startDate);
    }

    public void typeVersionReleaseDate(String releaseDate){
        versionReleaseDate.sendKeys(releaseDate);
    }

    public void typeVersionDescription(String description){
        versionDescription.sendKeys(description);
    }

    public void clickAddNewVersion(String name){
        wait.until(ExpectedConditions.elementToBeClickable(versionAddButton));
        versionAddButton.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(., '" + name + "')]/ancestor::tr")));
        versionId = driver.findElement(By.xpath("//a[contains(., '" + name + "')]/ancestor::tr")).getAttribute("data-version-id");
    }

    public boolean getAddButtonIsDisable(){
        return versionAddButton.getAttribute("disabled").equals("true");
    }
}
