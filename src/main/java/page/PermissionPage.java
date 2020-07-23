package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.HashMap;

public class PermissionPage extends WebPage{
    HashMap<String, Boolean> permissionFromProjectPage = new HashMap<>();
    @FindBy(id = "project-config-panel-permissions")
    WebElement permissionPanel;

    public PermissionPage(WebDriver driver) {
        super(driver);
    }

    public void goToPage(){
        driver.get("https://jira.codecool.codecanvas.hu/plugins/servlet/project-config/PP1/permissions");
        wait.until(ExpectedConditions.visibilityOf(permissionPanel));
    }

    public void savePermissionData(){
        permissionFromProjectPage.put("Browse Projects", driver.findElement(By.xpath("//tr[@data-permission-key='BROWSE_PROJECTS']/td[@class]/dl/dd")).getText().equals("Any logged in user"));
        permissionFromProjectPage.put("Create Issues", driver.findElement(By.xpath("//tr[@data-permission-key='CREATE_ISSUES']/td[@class]/dl/dd")).getText().equals("Any logged in user"));
        permissionFromProjectPage.put("Edit Issues", driver.findElement(By.xpath("//tr[@data-permission-key='EDIT_ISSUES']/td[@class]/dl/dd")).getText().equals("Any logged in user"));
    }

    public HashMap<String, Boolean> getPermissionFromProjectPage() {
        return permissionFromProjectPage;
    }

}
