package Tests;

import Base.BaseTest;
import Pages.HomePage;
import Pages.InventoryPage;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class SociaLMediaLinksTests extends BaseTest {

    public WebDriverWait wait;

    @BeforeMethod
    public void pageSetup() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.navigate().to("https://www.saucedemo.com/");
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        homePage = new HomePage();
        inventoryPage = new InventoryPage();

        loginAsStandardUser();
        }

    @Test(priority = 10)
    public void xLinkWorks(){
        inventoryPage.clickOnXLinkButton();
        switchToNewTab();
        wait.until(ExpectedConditions.urlToBe("https://x.com/saucelabs"));
    }

    @Test(priority = 20)
    public void facebookLinkWorks(){
        inventoryPage.clickOnFacebookLinkButton();
        switchToNewTab();
        wait.until(ExpectedConditions.urlToBe("https://www.facebook.com/saucelabs"));
    }

    @Test(priority = 30)
    public void linkedinLinkWorks(){
        inventoryPage.clickOnLinkedinLinkButton();
        switchToNewTab();
        wait.until(ExpectedConditions.urlToBe("https://www.linkedin.com/company/sauce-labs/"));
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}
