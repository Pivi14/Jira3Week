package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class WebPage {
    WebDriver driver;
    WebDriverWait wait;

    public WebPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Integer.parseInt(System.getenv("TIMEOUT")));
    }

    public void acceptAlertBox(){
        try{
            driver.switchTo().alert().accept();
        } catch (Exception ignore){}
    }

    public void goToPageAndWait(String url, WebElement webElement){
        driver.get(url);
        acceptAlertBox();
        wait.until(ExpectedConditions.visibilityOf(webElement));
    }

    public void waitForElement(WebElement element){
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public String getTextOfElement(WebElement element){
        return element.getText();
    }
}
