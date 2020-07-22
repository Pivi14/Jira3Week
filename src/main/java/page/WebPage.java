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
        wait = new WebDriverWait(driver, 10);
    }

    public void goToPageAndWait(String url, WebElement webElement){
        driver.get(url);
        wait.until(ExpectedConditions.visibilityOf(webElement));
    }

    public void waitForElement(WebElement element){
        ;
    }
}
