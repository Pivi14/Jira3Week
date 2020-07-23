package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SearchPage extends WebPage {
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

    public void checkAvailableIssueTypes(){
        wait.until(ExpectedConditions.elementToBeClickable(issueType));
        issueType.click();
        wait.until(ExpectedConditions.visibilityOf(typeMultiSelect));
    }
}
