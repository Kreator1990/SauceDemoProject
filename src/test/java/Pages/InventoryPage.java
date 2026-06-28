package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InventoryPage extends BaseTest{

    public InventoryPage(){
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "inventory_container")
    public WebElement inventoryContainerField;

    @FindBy(id = "react-burger-menu-btn")
    public WebElement burgerMenu;

    @FindBy(css = ".btn_inventory")
    public List<WebElement> addToCartButton;

    @FindBy(id = "shopping_cart_container")
    public WebElement shoppingCartIcon;

    @FindBy(className = "inventory_item_name")
    public List<WebElement> inventoryItemName;

    @FindBy(className = "social_twitter")
    public WebElement xLinkButton;

    @FindBy(className = "social_facebook")
    public WebElement facebookLinkButton;

    @FindBy(className = "social_linkedin")
    public WebElement linkedinLinkButton;

    @FindBy(className = "product_sort_container")
    public WebElement sortDropdownMenu;

    @FindBy(className = "inventory_item_price")
    public List<WebElement> itemPrice;

    //-------------------------------------------------------

    public boolean isOnInventoryPageURL(){
        return driver.getCurrentUrl().equals("https://www.saucedemo.com/inventory.html");
    }

    public boolean checkIfInventoryIsVisible(){
        return inventoryContainerField.isDisplayed();
    }

    public void clickOnBurgerMenuButton(){
        burgerMenu.click();
    }

    //-----------------------Add to Cart---------------------

    public void clickOnAddToCartButton(int index) {
        addToCartButton.get(index).click();
    }

    public void clickOnAllAddToCartButtons(){
        for(int i = 0; i < addToCartButton.size(); i++){
            addToCartButton.get(i).click();
        }
    }

    public void clickOnShoppingCartIcon(){
        shoppingCartIcon.click();
    }

    public String getSingleItemName(int index){
        return inventoryItemName.get(index).getText();
    }

    public List<String> getItemNames(){
        List<String> names = new ArrayList<>();
        for(WebElement item: inventoryItemName){
            names.add(item.getText());
        }
        return names;
    }

    public List<Double> getItemPrices(){
        List<Double> prices = new ArrayList<>();
        for(WebElement item: itemPrice){
            String text = item.getText();
            text = text.replace("$", "");
            prices.add(Double.parseDouble(text));
        }
        return prices;
    }

    public void clickOnInventoryItemName(int index){
        inventoryItemName.get(index).click();
    }

    //----------------------Social Media Links--------------

    public void clickOnXLinkButton(){
        xLinkButton.click();
    }

    public void clickOnFacebookLinkButton(){
        facebookLinkButton.click();
    }

    public void clickOnLinkedinLinkButton(){
        linkedinLinkButton.click();
    }

    //------------------Sorting----------------------

    public void sortBy(String visibleText) {
        Select select = new Select(sortDropdownMenu);
        select.selectByVisibleText(visibleText);
    }

    public List<String> getSortedItemNamesDesc() {
        List<String> names = new ArrayList<>(getItemNames());
        Collections.sort(names, Collections.reverseOrder());
        return names;
    }

    public List<String> getSortedItemNamesAsc() {
        List<String> names = new ArrayList<>(getItemNames());
        Collections.sort(names);
        return names;
    }

    public List<Double> getPricesSortedLowToHigh() {
        List<Double> prices = new ArrayList<>(getItemPrices());
        Collections.sort(prices);
        return prices;
    }

    public List<Double> getPricesSortedHighToLow() {
        List<Double> prices = new ArrayList<>(getItemPrices());
        Collections.sort(prices, Collections.reverseOrder());
        return prices;
    }

}
