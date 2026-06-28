package Tests;

import Base.BaseTest;
import Pages.CartPage;
import Pages.HomePage;
import Pages.InventoryPage;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RemoveItemFromCartTest extends BaseTest {

    int singleItemIndex = 1;

    @BeforeMethod
    public void pageSetup() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.navigate().to("https://www.saucedemo.com/");

        homePage = new HomePage();
        inventoryPage = new InventoryPage();
        cartPage = new CartPage();

        loginAsStandardUser();
    }

    @Test(priority = 10)
    public void removeItemFromCartOnInventoryPage(){
        inventoryPage.clickOnAddToCartButton(singleItemIndex);
        Assert.assertEquals(inventoryPage.addToCartButton.get(singleItemIndex).getText(), "Remove");
        Assert.assertEquals(Integer.parseInt(inventoryPage.shoppingCartIcon.getText()), 1);
        inventoryPage.clickOnAddToCartButton(singleItemIndex);
        Assert.assertEquals(inventoryPage.addToCartButton.get(singleItemIndex).getText(), "Add to cart");
        Assert.assertTrue(inventoryPage.shoppingCartIcon.getText().isEmpty());
        inventoryPage.clickOnShoppingCartIcon();
        Assert.assertTrue(cartPage.shoppingCartIcon.getText().isEmpty());
    }

    @Test(priority = 20)
    public void removeItemFromCartOnCartPage(){
        inventoryPage.clickOnAddToCartButton(singleItemIndex);
        inventoryPage.clickOnShoppingCartIcon();
        cartPage.clickOnRemoveButton(0);
        Assert.assertTrue(cartPage.shoppingCartIcon.getText().isEmpty());
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
