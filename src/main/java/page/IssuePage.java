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
    @FindBy(id = "edit-issue-submit")
    WebElement updateIssueButton;
    @FindBy(id = "summary")
    WebElement summary;
    @FindBy(xpath = "//div[@class='navigator-content empty-results']")
    WebElement emptyIssues;
    @FindBy(xpath = "//*[@id=\"summary-form\"]/div[2]/button[1]")
    WebElement acceptEditSummaryLine;
    @FindBy(id = "summary-subnav-title")
    WebElement summaryIssueTitle;
    @FindBy(xpath = "//a[@href='#'][@class='cancel']")
    WebElement editIssueModalCancelButton;


    public boolean editButtonIsAvailable(){
        return driver.findElements(By.id("edit-issue")).size() != 0;
    }

    public void gotToIssueWithID(String url, String issueId){
        driver.get(url);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@data-issue-key='" + issueId + "']")));
    }

    public WebElement getSummary() {
        return summary;
    }

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
//        goToPageAndWait(url, issueContent);
        driver.get(url);
        wait.until(ExpectedConditions.visibilityOf(issueContent));
        return issueContent != null;
    }

    public void deleteIssue() {
        driver.findElement(By.id("opsbar-operations_more")).click();
        driver.findElement(By.xpath("//*[@id=\"delete-issue\"]/a")).click();
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("delete-issue-submit")));
        driver.findElement(By.id("delete-issue-submit")).click();
        driver.navigate().to("https://jira.codecool.codecanvas.hu/secure/Dashboard.jspa");
    }

    public void editIssueTitle(String issueTitleChange) {
        issueTitle.click();
        waitForElement(summaryEditForm);
        summary.sendKeys(issueTitleChange);
        acceptEditSummaryLine.click();
        wait.until(ExpectedConditions.attributeToBe(issueTitle, "class", "editable-field inactive"));
    }

    public void clickOnUpdateButtonAndWaitForModalDisappear(String name) {
        updateIssueButton.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(., '" + name + "')]")));
    }

    public void editIssueTitleThroughEditPage(String issueTitleChange) {
        summary.sendKeys(issueTitleChange);
    }

    public void clickOnCancelOnEditModal() {
        editIssueModalCancelButton.click();
        driver.switchTo().alert().accept();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("edit-issue-dialog")));
    }

    public void openEditModal() {
        driver.navigate().to("https://jira.codecool.codecanvas.hu/browse/MTP-1431");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("edit-issue")));
        driver.findElement(By.id("edit-issue")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("edit-issue-dialog")));
    }

    public boolean checkAvailableIssue(String url, String title) {
        driver.get(url);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@data-issue-key='" + title + "']")));
        return driver.findElement(By.xpath("//a[@data-issue-key='" + title + "']")) != null;
    }
}
