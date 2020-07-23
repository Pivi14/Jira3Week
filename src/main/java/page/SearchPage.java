package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SearchPage extends WebPage {


    @FindBy(xpath = "//li[contains(@id, 10004)]")
    WebElement bug;
    @FindBy(xpath = "//li[contains(@id, 10001)]")
    WebElement story;
    @FindBy(xpath = "//li[contains(@id, 10002)]")
    WebElement task;
    @FindBy(xpath = "//li[contains(@id, 10003)]")
    WebElement subTask;
    @FindBy(xpath = "//div[@id='navigator-sidebar']/span")
    WebElement navigationSidebarButton;
    @FindBy(id = "navigator-sidebar")
    WebElement navigationSidebar;
    @FindBy(xpath = "//a[@title='Create a new search filter']")
    WebElement newSearchFilterButton;
    @FindBy(xpath = "//div[@data-id='project']")
    WebElement projectDowndrop;
    @FindBy(xpath = "//div[@data-id='issuetype']")
    WebElement issueType;
    @FindBy(id = "searcher-type-multi-select")
    WebElement typeMultiSelect;

    public SearchPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getBug() {
        return bug;
    }

    public WebElement getStory() {
        return story;
    }

    public WebElement getTask() {
        return task;
    }

    public WebElement getSubTask() {
        return subTask;
    }


//    private void checkAvailableIssueTypes() throws InterruptedException {
//        Thread.sleep(1000);
//        wait.until(ExpectedConditions.elementToBeClickable(issueTypeField));
//        issueTypeField.click();
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("searcher-type-multi-select")));
//    }

    public void goToPage(){
        goToPageAndWait("https://jira.codecool.codecanvas.hu/browse/WEAKS-72?jql=",projectDowndrop);
    }

    public void setDefaultFilter() throws InterruptedException {
        driver.navigate().to("https://jira.codecool.codecanvas.hu/browse/WEAKS-72?jql=");
        if (navigationSidebar.getAttribute("class").contains("collapsed")) {
            navigationSidebarButton.click();
        }
        newSearchFilterButton.click();
        Thread.sleep(1000);
        wait.until(ExpectedConditions.elementToBeClickable(projectDowndrop));
        projectDowndrop.click();
    }

    public void clickOnCheckBoxByProjectID(String ID) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='checkbox'][@value='" + ID + "']")));
        driver.findElement(By.xpath("//input[@type='checkbox'][@value='" + ID + "']")).click();
    }

    public void checkAvailableIssueTypes() throws InterruptedException {
        Thread.sleep(1000);
        wait.until(ExpectedConditions.elementToBeClickable(issueType));
        issueType.click();
        wait.until(ExpectedConditions.visibilityOf(typeMultiSelect));
    }
}
