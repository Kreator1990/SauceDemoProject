package Tests;

import Base.BaseTest;
import Pages.HomePage;
import Pages.InventoryPage;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SortItemsTest extends BaseTest {

    @BeforeMethod
    public void pageSetup() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.navigate().to("https://www.saucedemo.com/");

        homePage = new HomePage();
        inventoryPage = new InventoryPage();

        loginAsStandardUser();
    }

    @Test (priority = 10)
    public void sortItemsFromZtoA(){
        inventoryPage.sortBy("Name (Z to A)");
        Assert.assertEquals(inventoryPage.getItemNames(), inventoryPage.getSortedItemNamesDesc());
    }

    @Test(priority = 20)
    public void sortItemsFromAtoZ(){
        inventoryPage.sortBy("Name (Z to A)");
        inventoryPage.sortBy("Name (A to Z)");
        Assert.assertEquals(inventoryPage.getItemNames(), inventoryPage.getSortedItemNamesAsc());
    }

    @Test(priority = 30)
    public void sortItemsByPriceLowToHigh(){
        inventoryPage.sortBy("Price (low to high)");
        Assert.assertEquals(inventoryPage.getItemPrices(), inventoryPage.getPricesSortedLowToHigh());
    }

    @Test(priority = 40)
    public void sortItemsByPriceHighToLow(){
        inventoryPage.sortBy("Price (high to low)");
        Assert.assertEquals(inventoryPage.getItemPrices(), inventoryPage.getPricesSortedHighToLow());
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}
