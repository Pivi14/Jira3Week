package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProfilPage extends WebPage{
    @FindBy(id="up-d-username")
    WebElement userName;

    public ProfilPage(WebDriver driver){
        super(driver);
    }

    public void goProfilPage(){
        driver.get(System.getenv("BASE_URL") + "/secure/ViewProfile.jspa");
        wait.until(ExpectedConditions.visibilityOf(userName));
    }

    public String getUserName(){
        return userName.getText();
    }
}
