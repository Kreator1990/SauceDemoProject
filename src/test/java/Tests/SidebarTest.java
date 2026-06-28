package Tests;

import Base.BaseTest;
import Pages.CartPage;
import Pages.HomePage;
import Pages.InventoryPage;
import Pages.SidebarPage;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SidebarTest extends BaseTest {

    @BeforeMethod
    public void pageSetup() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.navigate().to("https://www.saucedemo.com/");

        homePage = new HomePage();
        inventoryPage = new InventoryPage();
        cartPage = new CartPage();
        sidebarPage = new SidebarPage();

        loginAsStandardUser();
    }

    @Test(priority = 10)
    public void aboutRedirectsToSauceLabs(){
        inventoryPage.clickOnBurgerMenuButton();
        sidebarPage.clickOnAboutButton();
        Assert.assertEquals(driver.getCurrentUrl(),"https://saucelabs.com/");
    }

    @Test(priority = 20)
    public void allItemsRedirectsToInventoryPage(){
        inventoryPage.clickOnShoppingCartIcon();
        cartPage.clickOnBurgerMenuButton();
        sidebarPage.clickOnallItemsButton();
        Assert.assertTrue((inventoryPage.isOnInventoryPageURL()));
    }

    @Test(priority = 30)
    public void resetAppStateClearsCart(){
        inventoryPage.clickOnAllAddToCartButtons();
        inventoryPage.clickOnBurgerMenuButton();
        sidebarPage.clickOnResetAppStateButton();
        Assert.assertTrue(inventoryPage.shoppingCartIcon.getText().isEmpty());
    }

    @Test(priority = 40)
    public void crossButtonClosesSidebar(){
        inventoryPage.clickOnBurgerMenuButton();
        sidebarPage.clickOnCrossButton();
        Assert.assertTrue(sidebarPage.isSidebarClosed());
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}
