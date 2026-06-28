package Tests;

import Base.BaseTest;
import Pages.HomePage;
import Pages.InventoryPage;
import Pages.SidebarPage;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LogoutTest extends BaseTest {

    @BeforeMethod
    public void pageSetup() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.navigate().to("https://www.saucedemo.com/");

        homePage = new HomePage();
        inventoryPage = new InventoryPage();
        sidebarPage = new SidebarPage();

        loginAsStandardUser();
    }

    @Test (priority = 10)
    public void userCanLogout() {
        inventoryPage.clickOnBurgerMenuButton();
        sidebarPage.clickOnLogoutButton();
        Assert.assertTrue(homePage.isOnHomePageURL());
        Assert.assertTrue(homePage.loginButton.isDisplayed());
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
