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

public class PlaceOrderSteps extends AppiumDriverManager {

    private WebDriverWait getWait() {
        return new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    private WebElement waitForElementPresent(By locator) {
        return getWait().until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    private WebElement waitForElementVisible(By locator) {
        return getWait().until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    private WebElement waitForElementClickable(By locator) {
        return getWait().until(ExpectedConditions.elementToBeClickable(locator));
    }

    private void scrollToElementByDescription(String desc) {
        By locator = AppiumBy.accessibilityId(desc);
        driver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true))" +
                        ".scrollIntoView(new UiSelector().description(\"" + desc + "\"))"));

        waitForElementVisible(locator);
    }

    private void clickElement(By locator) {
        try {
            waitForElementPresent(locator);
            waitForElementVisible(locator);
            waitForElementClickable(locator);
            driver.findElement(locator).click();
        } catch (Exception e) {
            throw new RuntimeException("Element not clickable or not found: " + locator, e);
        }
    }

    @When("i click on cart icon")
    public void clickOnCartIcon() {
        clickElement(By.xpath("//*[contains(@content-desc, 'Cart')]"));
    }

    @When("I select a product from cart page")
    public void clickOnItem() {
        clickElement(By.xpath("(//android.widget.ImageView)[1]"));
    }

    @When("I click on Checkout Button")
    public void clickOnCheckoutButton() {
        clickElement(By.xpath("//*[@content-desc='Checkout']"));
    }

    @When("I select a delivery slot")
    public void selectDeliverySlot() {
        clickElement(By.xpath("//android.view.View[@content-desc='Day']"));
    }

    @When("I select the available time")
    public void selectTime() {
        clickElement(AppiumBy.accessibilityId("16:00-18:00"));
    }

    @When("I click on Confirm button")
    public void clickOnConfirmButton() {
        clickElement(By.xpath("//android.view.View[@content-desc='Confirm']"));
    }

    @When("scroll down to payement method")
    public void scrollDownToPaymentMethod() {
        scrollToElementByDescription("Cash on delivery");
    }

    @When("I select the payement method i need it")
    public void selectPaymentMethod() {
        clickElement(AppiumBy.accessibilityId("Cash on delivery"));
    }

    @When("I click on place order button")
    public void clickOnPlaceOrderButton() {
        clickElement(AppiumBy.accessibilityId("Place Order"));
    }

    @Then("I should see the confirmation message")
    public void i_should_see_the_confirmation_message() {
        WebElement confirmation = waitForElementVisible(AppiumBy.accessibilityId("Order confirmed"));
        Assert.assertEquals(confirmation.getText(), "Order confirmed");
    }
}
