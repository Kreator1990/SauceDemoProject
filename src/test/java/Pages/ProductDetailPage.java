package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductDetailPage extends BaseTest {

    public ProductDetailPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "add-to-cart")
    public WebElement addToCartButton;

    @FindBy(id = "back-to-products")
    public WebElement backToProductsButton;

    @FindBy(id = "shopping_cart_container")
    public WebElement shoppingCartIcon;

//---------------------------------------------------------

    public void clickOnAddToCartButton(){
        addToCartButton.click();
    }

    public void clickOnBackToProductsButton(){
        backToProductsButton.click();
    }
}