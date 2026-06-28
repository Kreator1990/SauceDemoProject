package Tests;

import Base.BaseTest;
import Pages.HomePage;
import Pages.InventoryPage;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    String validUsername;
    String validPassword;
    String lockedUserUsername;

    @BeforeMethod
    public void pageSetup(){
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.navigate().to("https://www.saucedemo.com/");

        validUsername = excelReader.getStringData("Sheet1", 1, 0);
        validPassword = excelReader.getStringData("Sheet1",1,1);
        lockedUserUsername = excelReader.getStringData("Sheet1",1,4);

        homePage = new HomePage();
        inventoryPage = new InventoryPage();
    }

    @Test (priority = 10)
    public void loginWithValidCredentials(){
        homePage.inputUsername(validUsername);
        homePage.inputPassword(validPassword);
        homePage.clickOnLoginButton();
        Assert.assertTrue((inventoryPage.isOnInventoryPageURL()));
        Assert.assertTrue(inventoryPage.checkIfInventoryIsVisible());
    }

    @Test (priority = 13)
    public void loginWithValidUsernameAndEmptyPassword(){
        homePage.inputUsername(validUsername);
        homePage.clickOnLoginButton();
        Assert.assertTrue(homePage.errorMessage.getText().contains("Password is required"));
    }

    @Test (priority = 16)
    public void loginWithEmptyUsernameAndValidPassword(){
        homePage.inputPassword(validPassword);
        homePage.clickOnLoginButton();
        Assert.assertTrue(homePage.errorMessage.getText().contains("Username is required"));
    }

    @Test (priority = 20)
    public void loginWithValidUsernameAndInvalidPassword(){
        for(int i = 1; i<= excelReader.getLastRow("Sheet1");i++){
            String invalidPassword = excelReader.getStringData("Sheet1", i, 3);
            homePage.inputUsername(validUsername);
            homePage.inputPassword(invalidPassword);
            homePage.clickOnLoginButton();
            Assert.assertTrue(homePage.errorMessage.getText().contains("Username and password do not match"));
        }
    }

    @Test (priority = 30)
    public void loginWithInvalidUsernameAndValidPassword(){
        for(int i = 1; i<= excelReader.getLastRow("Sheet1");i++){
            String invalidUsername = excelReader.getStringData("Sheet1", i, 2);
            homePage.inputUsername(invalidUsername);
            homePage.inputPassword(validPassword);
            homePage.clickOnLoginButton();
            Assert.assertTrue(homePage.errorMessage.getText().contains("Username and password do not match"));
        }
    }

    @Test (priority = 40)
    public void loginWithLockedUserCredentials(){
        homePage.inputUsername(lockedUserUsername);
        homePage.inputPassword(validPassword);
        homePage.clickOnLoginButton();
        Assert.assertTrue(homePage.errorMessage.getText().contains("this user has been locked out"));
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }

}
