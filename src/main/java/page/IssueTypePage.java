package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class IssueTypePage extends WebPage{
    ArrayList<String> exceptIssueTypes = new ArrayList<>();
    @FindBy(id = "project-config-panel-issuetypes")
    WebElement issueTypePanel;

    public IssueTypePage(WebDriver driver) {
        super(driver);
    }

    public ArrayList<String> getExceptIssueTypes() {
        return exceptIssueTypes;
    }

    public void goToPage(){
//        goToPageAndWait("https://jira.codecool.codecanvas.hu/plugins/servlet/project-config/PP1/issuetypes", issueTypePanel);
        driver.get("https://jira.codecool.codecanvas.hu/plugins/servlet/project-config/PP1/issuetypes");
        wait.until(ExpectedConditions.visibilityOf(issueTypePanel));
    }

    public void saveExceptIssueTypes(){
        List<WebElement> allIssueTypeRow = driver.findElements(By.xpath("//li[@id='project-issuetypes-container']/ul/li"));
        for (WebElement issueType: allIssueTypeRow){
            exceptIssueTypes.add(issueType.findElement(By.tagName("a")).getAttribute("title"));
        }
        Collections.sort(exceptIssueTypes);
    }
}
