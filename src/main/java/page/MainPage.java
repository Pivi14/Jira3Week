package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MainPage extends WebPage{
    @FindBy(id="login-form-username")
    WebElement userNameField;
    @FindBy(id="login-form-password")
    WebElement passwordField;
    @FindBy(id="login")
    WebElement loginButton;
    @FindBy(id="usernameerror")
    WebElement userNameErrorMessage;
    @FindBy(xpath = "//h1[contains(., 'Logout')]")
    WebElement logoutMessage;

    public MainPage(WebDriver driver){
        super(driver);
    }

    public void login(String userName, String password){
        wait.until(ExpectedConditions.visibilityOf(loginButton));
        userNameField.sendKeys(userName);
        passwordField.sendKeys(password);
        loginButton.submit();
    }

    public void goToMainPage(){
        goToPageAndWait(System.getenv("MAIN_PAGE"), loginButton);
    }

    public boolean errorMessageAppears(){
        wait.until(ExpectedConditions.visibilityOf(userNameErrorMessage));
        return userNameErrorMessage != null;
    }

    public boolean logoutMessageAppears(){
        wait.until(ExpectedConditions.visibilityOf(logoutMessage));
        return logoutMessage != null;
    }
}
