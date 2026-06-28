package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutCompletePage extends BaseTest {

    public CheckoutCompletePage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "back-to-products")
    public WebElement backToHomeButton;

    @FindBy(className = "complete-header")
    public WebElement successMessage;

    //----------------------------------------------------

    public void clickOnBackToHomeButton(){
        backToHomeButton.click();
    }
}
