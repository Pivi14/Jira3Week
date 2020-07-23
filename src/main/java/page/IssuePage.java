package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class IssuePage extends WebPage {
    @FindBy(id = "issue-content")
    WebElement issueContent;
    @FindBy(id = "issues-subnavigation-title")
    WebElement openIssueTitle;
    @FindBy(id = "summary-val")
    WebElement issueTitle;
    @FindBy(id = "summary-form")
    WebElement summaryEditForm;
    @FindBy(id = "summary")
    WebElement summary;
    @FindBy(xpath = "//div[@class='navigator-content empty-results']")
    WebElement emptyIssues;
    @FindBy(xpath = "//*[@id=\"summary-form\"]/div[2]/button[1]")
    WebElement acceptEditSummaryLine;
    @FindBy(id = "summary-subnav-title")
    WebElement summaryIssueTitle;

    public WebElement getSummaryIssueTitle() {
        return summaryIssueTitle;
    }

    public WebElement getOpenIssueTitle() {
        return openIssueTitle;
    }
    public WebElement getEmptyIssues() {
        return emptyIssues;
    }

    public WebElement getIssueTitle() {
        return issueTitle;
    }

    public IssuePage(WebDriver driver) {
        super(driver);
    }

    public boolean checkBrowseIssue(String url) {
        goToPageAndWait(url, issueContent);
        return issueContent != null;
    }

    public void editIssueTitle(String issueTitleChange){
        issueTitle.click();
        waitForElement(summaryEditForm);
        summary.sendKeys(issueTitleChange);
        acceptEditSummaryLine.click();
        wait.until(ExpectedConditions.attributeToBe(issueTitle,"class", "editable-field inactive"));

    }

    public boolean checkAvailableIssue(String url, String title) {
        driver.get(url);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@data-issue-key='" + title + "']")));
        return driver.findElement(By.xpath("//a[@data-issue-key='" + title + "']")) != null;
    }
}
