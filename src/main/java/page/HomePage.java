package page;

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

    public HomePage(WebDriver driver){
        super(driver);
    }

    public void waitForLoad(){
        wait.until(ExpectedConditions.visibilityOf(createButton));
    }

    public void goToPage(){
        goToPageAndWait("https://jira.codecool.codecanvas.hu/secure/Dashboard.jspa", homeTitle);
    }

    public void logout(){
        profileMenu.click();
        wait.until(ExpectedConditions.visibilityOf(logoutButton));
        logoutButton.click();
    }
}
