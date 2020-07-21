package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProjectPage extends WebPage{
    @FindBy(xpath = "//h1[contains(., 'Browse projects')]")
    WebElement allProjectTitle;


    public ProjectPage(WebDriver driver) {
        super(driver);
    }

    public void goToPage(){
        goToPageAndWait("https://jira.codecool.codecanvas.hu/secure/BrowseProjects.jspa", allProjectTitle);
    }

    public boolean mainPageAppears(){
        return driver.findElement(By.xpath("//a[@href='/browse/MTP']")) != null;
    }
}
