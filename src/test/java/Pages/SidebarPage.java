package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SideBar extends BaseTest {

    public SideBar(){
        PageFactory.initElements(driver, this);
    }

    @FindBy(id="inventory_sidebar_link")
    public WebElement allItemsButton;

    @FindBy (id="about_sidebar_link")
    public WebElement aboutButton;

    @FindBy (id="logout_sidebar_link")
    public WebElement logoutButton;

    @FindBy (id="reset_sidebar_link")
    public WebElement resetAppStateButton;

    @FindBy (id="react-burger-cross-btn")
    public WebElement sideBarCrossButton;

    //-----------------------------------------------------

    public void clickOnLogoutButton(){
        logoutButton.click();
    }
}
