package page;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CreateModalPage extends WebPage {

    public CreateModalPage(WebDriver driver) {
        super(driver);
    }
    @FindBy(id="summary")
    WebElement summaryField;

    public WebElement getCreateIssueSubmitButton() {
        return createIssueSubmitButton;
    }

    public WebElement getOpenIssueTitle() {
        return openIssueTitle;
    }

    @FindBy (id="issues-subnavigation-title")
    WebElement openIssueTitle;
    @FindBy(id="create-issue-submit")
    WebElement createIssueSubmitButton;
    @FindBy(id="issuetype-field")
    WebElement issueTypeField;
    @FindBy(id="project-field")
    WebElement projectField;
    @FindBy(xpath = "//a[@class='cancel']")
    WebElement cancelIssueButton;

    public void waitForCreateModal(){
        wait.until(ExpectedConditions.visibilityOf(createIssueSubmitButton));
    }

    public void chooseProject(String projectName){
        projectField.sendKeys(projectName);
        projectField.sendKeys(Keys.ENTER);
    }

    public void addSummary(String summary){
        summaryField.sendKeys(summary);
    }

    public void chooseIssueType(String issueType){
        issueTypeField.sendKeys(issueType);
        issueTypeField.sendKeys(Keys.ENTER);
    }

    public void submitIssue(){
        createIssueSubmitButton.click();
    }

    public void cancelCreateIssue(){
        cancelIssueButton.click();
    }


}
