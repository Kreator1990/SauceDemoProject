package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class CartPage extends BaseTest {

    public CartPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "checkout")
    public WebElement checkoutButton;

    @FindBy(id = "continue-shopping")
    public WebElement continueShoppingButton;

    @FindBy(className = "inventory_item_name")
    public List<WebElement> itemsInCart;

    @FindBy(className = "cart_button")
    public List<WebElement> removeButton;

    @FindBy(id = "shopping_cart_container")
    public WebElement shoppingCartIcon;

    @FindBy(id = "react-burger-menu-btn")
    public WebElement burgerMenu;


//----------------------------------------------------------------

    public boolean isOnCartPageURL(){
        return driver.getCurrentUrl().equals("https://www.saucedemo.com/cart.html");
    }

    public List<String> getCartItemsNames(){
        List<String> names = new ArrayList<>();
        for(WebElement item: itemsInCart){
            names.add(item.getText());
        }
        return names;
    }

    public void clickOnCheckoutButton(){
        checkoutButton.click();
    }

    public void clickOnRemoveButton(int index){
        removeButton.get(index).click();
    }

    public void clickOnContinueShoppingButton(){
        continueShoppingButton.click();
    }

    public void clickOnBurgerMenuButton(){
        burgerMenu.click();
    }

}
