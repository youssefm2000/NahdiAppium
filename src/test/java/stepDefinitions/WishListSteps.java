package stepDefinitions;

import io.appium.java_client.AppiumBy;
import io.cucumber.java.en.*;
import io.qameta.allure.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import setup.AppiumDriverManager;

import java.time.Duration;

public class WishListSteps extends AppiumDriverManager {

    private WebDriverWait getWait() {
        return new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    private WebElement waitForElementVisible(By locator) {
        return getWait().until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * Step to add a product to the wishlist from the home page.
     */
    @Epic("Wishlist")
    @Feature("Add to Wishlist")
    @Story("Add product from Home Page")
    @Severity(SeverityLevel.NORMAL)
    @Description("This step adds a product (Panadol) to the wishlist from the home screen.")
    @When("the user adds a product to the wish list from the home page")
    public void addProductToWishlistFromHomePage() {
        driver.findElement(By.xpath("//*[@content-desc='Panadol']")).click();
    }

    /**
     * Step to open the wishlist page.
     */
    @Epic("Wishlist")
    @Feature("View Wishlist")
    @Story("Open Wishlist Page")
    @Severity(SeverityLevel.MINOR)
    @Description("This step opens the wishlist to view saved items.")
    @When("the user opens the wish list")
    public void openWishlist() {
        driver.findElement(By.xpath("//*[@content-desc='wish list']")).click();
    }

    /**
     * Step to verify that the product appears in the wishlist.
     */
    @Epic("Wishlist")
    @Feature("Verify Wishlist")
    @Story("Verify Product in Wishlist")
    @Severity(SeverityLevel.CRITICAL)
    @Description("This step verifies that the added product appears in the wishlist.")
    @Then("the product should be displayed in the wish list")
    public void verifyProductInWishlist() {
        WebElement elementname = waitForElementVisible(AppiumBy.accessibilityId("Panadol"));
        Assert.assertEquals(elementname.getText(), "Panadol");
    }

}
