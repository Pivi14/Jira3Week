package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProfilPage {
    WebDriver driver;
    WebDriverWait wait;
    @FindBy(id="up-d-username")
    WebElement userName;

    public ProfilPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, 10);
    }

    public void goProfilPage(){
        driver.get("https://jira.codecool.codecanvas.hu/secure/ViewProfile.jspa");
    }

    public String getUserName(){
        return userName.getText();
    }
}
