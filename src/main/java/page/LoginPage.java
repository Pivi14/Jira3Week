package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends WebPage{
    @FindBy(id="login-form-username")
    WebElement userNameField;
    @FindBy(id="login-form-password")
    WebElement passwordField;
    @FindBy(id="login-form-submit")
    WebElement loginButton;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void goToPage(){
        driver.get("https://jira.codecool.codecanvas.hu/login.jsp");
    }

    public void login(String userName, String password){
        wait.until(ExpectedConditions.visibilityOf(loginButton));
        userNameField.sendKeys(userName);
        passwordField.sendKeys(password);
        loginButton.submit();
    }
}
