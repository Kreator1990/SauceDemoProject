package Tests;

import Base.BaseTest;
import Pages.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AddingItemsToCartTest extends BaseTest {

    int singleItemIndex = 1;

    @BeforeMethod
    public void pageSetup() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.navigate().to("https://www.saucedemo.com/");

        homePage = new HomePage();
        inventoryPage = new InventoryPage();
        cartPage = new CartPage();
        sidebarPage = new SidebarPage();
        productDetailPage = new ProductDetailPage();

        loginAsStandardUser();
    }

    @Test (priority = 10)
    public void addSingleItemToCartTest(){
        String addedItemName = inventoryPage.getSingleItemName(singleItemIndex);
        inventoryPage.clickOnAddToCartButton(singleItemIndex);
        Assert.assertEquals(Integer.parseInt(inventoryPage.shoppingCartIcon.getText()), 1);
        inventoryPage.clickOnShoppingCartIcon();
        Assert.assertEquals(cartPage.getCartItemsNames().get(0), addedItemName);
    }

    @Test (priority = 20)
    public void addingAllItemsToCartTest(){
        inventoryPage.clickOnAllAddToCartButtons();
        Assert.assertEquals(Integer.parseInt(inventoryPage.shoppingCartIcon.getText()), inventoryPage.addToCartButton.size());
        inventoryPage.clickOnShoppingCartIcon();
        Assert.assertEquals(inventoryPage.getItemNames(), cartPage.getCartItemsNames());
    }

    @Test (priority = 30)
    public void continueShoppingFromCartTest(){
        inventoryPage.clickOnAddToCartButton(singleItemIndex);
        inventoryPage.clickOnShoppingCartIcon();
        cartPage.clickOnContinueShoppingButton();
        Assert.assertTrue(inventoryPage.isOnInventoryPageURL());
        Assert.assertEquals(Integer.parseInt(inventoryPage.shoppingCartIcon.getText()), 1);
    }

    @Test(priority = 33)
    public void addingItemToCartOnProductDetailPage(){
        inventoryPage.clickOnInventoryItemName(singleItemIndex);
        Assert.assertTrue(driver.getCurrentUrl().contains("https://www.saucedemo.com/inventory-item.html"));
        productDetailPage.clickOnAddToCartButton();
        Assert.assertEquals(Integer.parseInt(productDetailPage.shoppingCartIcon.getText()), 1);
    }

    @Test(priority = 35)
    public void backToProductsRedirectsToInventoryPage(){
        inventoryPage.clickOnInventoryItemName(singleItemIndex);
        productDetailPage.clickOnBackToProductsButton();
        Assert.assertTrue(inventoryPage.isOnInventoryPageURL());
    }

    @Test(priority = 40)
    public void itemRemainsInCartAfterLogoutTest() {
        inventoryPage.clickOnAddToCartButton(singleItemIndex);
        inventoryPage.clickOnBurgerMenuButton();
        sidebarPage.clickOnLogoutButton();
        loginAsStandardUser();
        Assert.assertEquals(Integer.parseInt(inventoryPage.shoppingCartIcon.getText()), 1);
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }

}
