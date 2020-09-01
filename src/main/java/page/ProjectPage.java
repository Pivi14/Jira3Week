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
        driver.get(System.getenv("BASE_URL") + "/secure/BrowseProjects.jspa");
        wait.until(ExpectedConditions.visibilityOf(allProjectTitle));
    }

    public boolean mainPageAppears(){
        return driver.findElement(By.xpath("//a[@href='/browse/MTP']")) != null;
    }

    public boolean checkAvailableProject(String url, String title){
        driver.get(url);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//dd[@class='project-meta-value'][contains(., " + title + ")]")));
        return driver.findElement(By.xpath("//dd[@class='project-meta-value'][contains(., " + title + ")]")) != null;
    }
}
