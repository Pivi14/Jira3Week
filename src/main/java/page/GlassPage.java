package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GlassPage extends WebPage{
    @FindBy(id = "glass-general-panel")
    WebElement glassPage;


    public GlassPage(WebDriver driver) {
        super(driver);
    }

    public void goToPage(){
        goToPageAndWait("https://jira.codecool.codecanvas.hu/projects/PP1", glassPage);
    }

    public String getComponentTitle(String componentId){
        return driver.findElement(By.xpath("//tr[@data-component-id='" + componentId + "']/td[@class='components-table__name']/div/a")).getText();
    }
}
