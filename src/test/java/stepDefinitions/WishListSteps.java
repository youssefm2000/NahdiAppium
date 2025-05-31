package stepDefinitions;
import io.appium.java_client.AppiumBy;
import io.cucumber.java.en.*;
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

    @When("the user adds a product to the wish list from the home page")
    public void addProductToWishlistFromHomePage() {
        driver.findElement(By.xpath("//*[@content-desc='Panadol']")).click();
    }

    @When("the user opens the wish list")
    public void openWishlist() {
        driver.findElement(By.xpath("//*[@content-desc='wish list']")).click();

    }

    @Then("the product should be displayed in the wish list")
    public void verifyProductInWishlist() {
        WebElement elementname = waitForElementVisible(AppiumBy.accessibilityId("Panadol"));
        Assert.assertEquals(elementname.getText(), "Panadol");
    }

}
