package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends WebPage{
    @FindBy(id="create_link")
    WebElement createButton;
    @FindBy(id="header-details-user-fullname")
    WebElement profileMenu;
    @FindBy(id="log_out")
    WebElement logoutButton;
    @FindBy(xpath = "//h1[contains(., 'System Dashboard')]")
    WebElement homeTitle;
    @FindBy(xpath = "//h3[contains(., 'Assigned to Me')]")
    WebElement AssignedMessage;
    @FindBy(xpath = "//a[@class='issue-created-key issue-link']")
    WebElement newIssueLink;

    public void checkLoggedOut(){
        acceptAlertBox();
        if (driver.findElements(By.id("create_link")).size() > 0){
            logout();
        }
    }


    public HomePage(WebDriver driver){
        super(driver);
    }

    public void waitForLoad(){
        wait.until(ExpectedConditions.visibilityOf(AssignedMessage));
    }

    public void clickOnCreateIssueButton(){
        createButton.click();
    }

    public void goToPage(){
        driver.get("https://jira.codecool.codecanvas.hu/secure/Dashboard.jspa");
        acceptAlertBox();
        wait.until(ExpectedConditions.visibilityOf(AssignedMessage));
    }

    public void openNewIssue(){
        wait.until(ExpectedConditions.visibilityOf(newIssueLink));
        newIssueLink.click();
    }

    public void logout(){
        profileMenu.click();
        wait.until(ExpectedConditions.visibilityOf(logoutButton));
        logoutButton.click();
    }
}
