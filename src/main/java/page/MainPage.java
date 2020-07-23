package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.concurrent.TimeUnit;

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
    @FindBy(id = "login-container")
    WebElement loginContainer;

    public MainPage(WebDriver driver){
        super(driver);
    }

    public void login(String userName, String password){
        if (driver.findElements(By.id("login-container")).size() > 0){
            wait.until(ExpectedConditions.visibilityOf(loginContainer));
            userNameField.sendKeys(userName);
            passwordField.sendKeys(password);
            loginButton.submit();
        }
    }

    public void goToMainPage(){
        if (driver.findElements(By.id("login-container")).size() > 0){
            driver.get(System.getenv("MAIN_PAGE"));
            wait.until(ExpectedConditions.visibilityOf(loginContainer));
        }
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
