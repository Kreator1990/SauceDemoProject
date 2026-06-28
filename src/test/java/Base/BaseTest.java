package Base;

import Pages.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BaseTest {

    public static WebDriver driver;

    public HomePage homePage;

    public InventoryPage inventoryPage;

    public SidebarPage sidebarPage;

    public CartPage cartPage;

    public CheckoutStepOnePage checkoutStepOnePage;

    public CheckoutStepTwoPage checkoutStepTwoPage;

    public CheckoutCompletePage checkoutCompletePage;

    public ProductDetailPage productDetailPage;

    public ExcelReader excelReader;

    public void loginAsStandardUser() {
        String standardLoginUsername = excelReader.getStringData("Sheet1", 1, 0);
        String standardLoginPassword = excelReader.getStringData("Sheet1", 1, 1);

        homePage.login(standardLoginUsername, standardLoginPassword);
    }

    public void switchToNewTab() {
        List<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
    }

    @BeforeClass
    public void setUp() throws IOException {
        WebDriverManager.firefoxdriver().setup();
        excelReader = new ExcelReader(System.getProperty("user.dir") + "/src/test/resources/users.xlsx");
    }

}
