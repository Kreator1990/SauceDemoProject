package Tests;

import Base.BaseTest;
import Pages.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CheckoutTest extends BaseTest {

    String validFirstName = "Slobodan";
    String validLastName = "Petrovic";
    String validPostalCode = "11073";

    @BeforeMethod
    public void pageSetup() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.navigate().to("https://www.saucedemo.com/");

        homePage = new HomePage();
        inventoryPage = new InventoryPage();
        cartPage = new CartPage();
        checkoutStepOnePage = new CheckoutStepOnePage();
        checkoutStepTwoPage = new CheckoutStepTwoPage();
        checkoutCompletePage = new CheckoutCompletePage();

        loginAsStandardUser();
    }

    @Test(priority = 10)
    public void successfulCheckout(){
        inventoryPage.clickOnAllAddToCartButtons();
        inventoryPage.clickOnShoppingCartIcon();
        cartPage.clickOnCheckoutButton();
        checkoutStepOnePage.inputFirstName(validFirstName);
        checkoutStepOnePage.inputLastName(validLastName);
        checkoutStepOnePage.inputPostalCode(validPostalCode);
        checkoutStepOnePage.clickOnContinueButton();
        checkoutStepTwoPage.clickOnFinnishButton();
        Assert.assertEquals(checkoutCompletePage.successMessage.getText(), "Thank you for your order!");
        checkoutCompletePage.clickOnBackToHomeButton();
        Assert.assertTrue(inventoryPage.isOnInventoryPageURL());
        Assert.assertTrue(inventoryPage.shoppingCartIcon.getText().isEmpty());
    }

    @Test(priority = 20)
    public void checkoutWithEmptyFirstNameField(){
        inventoryPage.clickOnAllAddToCartButtons();
        inventoryPage.clickOnShoppingCartIcon();
        cartPage.clickOnCheckoutButton();
        checkoutStepOnePage.inputLastName(validLastName);
        checkoutStepOnePage.inputPostalCode(validPostalCode);
        checkoutStepOnePage.clickOnContinueButton();
        Assert.assertEquals(checkoutStepOnePage.errorMessage.getText(), "Error: First Name is required");
        Assert.assertTrue(checkoutStepOnePage.isOnCheckoutStepOnePageURL());
    }

    @Test(priority = 30)
    public void checkoutWithEmptyLastNameField(){
        inventoryPage.clickOnAllAddToCartButtons();
        inventoryPage.clickOnShoppingCartIcon();
        cartPage.clickOnCheckoutButton();
        checkoutStepOnePage.inputFirstName(validFirstName);
        checkoutStepOnePage.inputPostalCode(validPostalCode);
        checkoutStepOnePage.clickOnContinueButton();
        Assert.assertEquals(checkoutStepOnePage.errorMessage.getText(), "Error: Last Name is required");
        Assert.assertTrue(checkoutStepOnePage.isOnCheckoutStepOnePageURL());
    }

    @Test(priority = 40)
    public void checkoutWithEmptyPostalCodeField(){
        inventoryPage.clickOnAllAddToCartButtons();
        inventoryPage.clickOnShoppingCartIcon();
        cartPage.clickOnCheckoutButton();
        checkoutStepOnePage.inputFirstName(validFirstName);
        checkoutStepOnePage.inputLastName(validLastName);
        checkoutStepOnePage.clickOnContinueButton();
        Assert.assertEquals(checkoutStepOnePage.errorMessage.getText(), "Error: Postal Code is required");
        Assert.assertTrue(checkoutStepOnePage.isOnCheckoutStepOnePageURL());
    }

    @Test(priority = 50)
    public void cancelOnStepOne(){
        inventoryPage.clickOnAllAddToCartButtons();
        inventoryPage.clickOnShoppingCartIcon();
        cartPage.clickOnCheckoutButton();
        checkoutStepOnePage.clickOnCancelButton();
        Assert.assertTrue(cartPage.isOnCartPageURL());
    }

    @Test(priority = 61)
    public void cancelOnStepTwo(){
        inventoryPage.clickOnAllAddToCartButtons();
        inventoryPage.clickOnShoppingCartIcon();
        cartPage.clickOnCheckoutButton();
        checkoutStepOnePage.inputFirstName(validFirstName);
        checkoutStepOnePage.inputLastName(validLastName);
        checkoutStepOnePage.inputPostalCode(validPostalCode);
        checkoutStepOnePage.clickOnContinueButton();
        checkoutStepTwoPage.clickOnCancelButton();
        Assert.assertTrue(inventoryPage.isOnInventoryPageURL());
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}