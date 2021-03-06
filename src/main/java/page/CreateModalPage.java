package page;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CreateModalPage extends WebPage {

    public CreateModalPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getCreateIssueSubmitButton() {
        return createIssueSubmitButton;
    }

    @FindBy(id = "summary")
    WebElement summaryField;
    @FindBy(id = "create-issue-submit")
    WebElement createIssueSubmitButton;
    @FindBy(id = "project-field")
    WebElement projectField;
    @FindBy(xpath = "//a[@class='cancel']")
    WebElement cancelIssueButton;
    @FindBy(xpath = "//div[@class='error']")
    WebElement errorMassage;


    public WebElement getErrorMassage() {
        return errorMassage;
    }


    public void chooseProject(String projectName) {
        projectField.sendKeys(projectName);
        wait.until(ExpectedConditions.attributeContains(projectField, "aria-activedescendant", projectName.toLowerCase()));
        projectField.sendKeys(Keys.ENTER);
    }

    public void addSummary(String summary) {
        wait.until(ExpectedConditions.elementToBeClickable(By.id("summary")));
        summaryField.sendKeys(summary);
    }

    public void submitIssue() {
        wait.until(ExpectedConditions.elementToBeClickable(createIssueSubmitButton));
        createIssueSubmitButton.click();
    }

    public void cancelCreateIssue() {
        cancelIssueButton.click();
        driver.switchTo().alert().accept();
    }

    public void catchPopupBox() {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[@id=\"aui-flag-container\"]/div/div/a")));
        driver.findElement(By.xpath("//*[@id=\"aui-flag-container\"]/div/div/a")).click();
    }

}
