package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SidebarPage extends BaseTest {

    public SidebarPage(){
        PageFactory.initElements(driver, this);
    }

    @FindBy(id="inventory_sidebar_link")
    public WebElement allItemsButton;

    @FindBy (id="about_sidebar_link")
    public WebElement aboutButton;

    @FindBy (id="logout_sidebar_link")
    public WebElement logoutButton;

    @FindBy (id="reset_sidebar_link")
    public WebElement resetAppStateButton;

    @FindBy (id="react-burger-cross-btn")
    public WebElement crossButton;

    //-----------------------------------------------------

    public void clickOnLogoutButton(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(logoutButton));
        logoutButton.click();
    }

    public void clickOnAboutButton(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(aboutButton));
        aboutButton.click();
    }

    public void clickOnallItemsButton(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(allItemsButton));
        allItemsButton.click();
    }

    public void clickOnCrossButton(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(crossButton));
        crossButton.click();
    }

    public void clickOnResetAppStateButton(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(resetAppStateButton));
        resetAppStateButton.click();
    }

    public boolean isSidebarClosed(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.invisibilityOf(crossButton));
        return !crossButton.isDisplayed();
    }
}
