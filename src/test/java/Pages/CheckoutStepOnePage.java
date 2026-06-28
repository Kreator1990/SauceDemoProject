package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutStepOnePage extends BaseTest {

    public CheckoutStepOnePage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "first-name")
    public WebElement firstNameField;

    @FindBy(id = "last-name")
    public WebElement lastNameField;

    @FindBy(id = "postal-code")
    public WebElement postalCodeField;

    @FindBy(id = "cancel")
    public WebElement cancelButton;

    @FindBy(id = "continue")
    public WebElement continueButton;

    @FindBy(className = "error-message-container")
    public WebElement errorMessage;

    //----------------------------------------------------------------

    public void inputFirstName(String firstname){
        firstNameField.clear();
        firstNameField.sendKeys(firstname);
    }

    public void inputLastName(String lastname){
        lastNameField.clear();
        lastNameField.sendKeys(lastname);
    }

    public void inputPostalCode(String postalCode){
        postalCodeField.clear();
        postalCodeField.sendKeys(postalCode);
    }

    public void clickOnContinueButton(){
        continueButton.click();
    }

    public void clickOnCancelButton(){
        cancelButton.click();
    }

    public boolean isOnCheckoutStepOnePageURL(){
        return driver.getCurrentUrl().equals(
                "https://www.saucedemo.com/checkout-step-one.html"
        );
    }

}

