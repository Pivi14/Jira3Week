package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage {
    WebDriver driver;
    @FindBy(id="login-form-username")
    WebElement userNameField;
    @FindBy(id="login-form-password")
    WebElement passwordField;
    @FindBy(id="login")
    WebElement loginButton;

    public MainPage(WebDriver driver){
        this.driver = driver;
    }

    public void login(String userName, String password){
        userNameField.sendKeys(userName);
        passwordField.sendKeys(password);
        loginButton.submit();
    }
}
