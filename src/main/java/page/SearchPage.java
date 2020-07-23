package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.concurrent.TimeUnit;

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

    public Integer checkIssueType(String issueTypeName){
        switch (issueTypeName){
            case "Bug":
                return driver.findElements(By.xpath("//li[contains(@id, 10004)]")).size();
            case "Story":
                return driver.findElements(By.xpath("//li[contains(@id, 10001)]")).size();
            case "Task":
                return driver.findElements(By.xpath("//li[contains(@id, 10002)]")).size();
            case "Sub-task":
                return driver.findElements(By.xpath("//li[contains(@id, 10003)]")).size();
        }
        return 0;
    }

    public void goToPage(String url){
        driver.get(url);
        wait.until(ExpectedConditions.visibilityOf(projectDowndrop));
    }

    public void checkAvailableIssueTypes() throws InterruptedException {
//        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        wait.until(ExpectedConditions.elementToBeClickable(issueType));
        issueType.click();
        wait.until(ExpectedConditions.visibilityOf(typeMultiSelect));
    }
}
