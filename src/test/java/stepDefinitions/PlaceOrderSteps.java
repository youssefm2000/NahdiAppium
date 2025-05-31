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

    /**
     * Step to click the cart icon on the home screen.
     */
    @Epic("Order Flow")
    @Feature("Cart Management")
    @Story("Open Cart")
    @Severity(SeverityLevel.NORMAL)
    @Description("This step clicks the cart icon to view cart items.")
    @When("i click on cart icon")
    public void clickOnCartIcon() {
        clickElement(By.xpath("//*[contains(@content-desc, 'Cart')]"));
    }

    /**
     * Step to select a product from the cart page.
     */
    @Epic("Order Flow")
    @Feature("Cart Management")
    @Story("Select Product")
    @Severity(SeverityLevel.NORMAL)
    @Description("This step selects a product from the cart.")
    @When("I select a product from cart page")
    public void clickOnItem() {
        clickElement(By.xpath("(//android.widget.ImageView)[1]"));
    }

    /**
     * Step to proceed to the checkout screen.
     */
    @Epic("Order Flow")
    @Feature("Checkout")
    @Story("Proceed to Checkout")
    @Severity(SeverityLevel.CRITICAL)
    @Description("This step clicks the checkout button.")
    @When("I click on Checkout Button")
    public void clickOnCheckoutButton() {
        clickElement(By.xpath("//*[@content-desc='Checkout']"));
    }

    /**
     * Step to select a delivery slot for the order.
     */
    @Epic("Order Flow")
    @Feature("Delivery Scheduling")
    @Story("Select Delivery Slot")
    @Severity(SeverityLevel.NORMAL)
    @Description("This step selects a delivery day slot.")
    @When("I select a delivery slot")
    public void selectDeliverySlot() {
        clickElement(By.xpath("//android.view.View[@content-desc='Day']"));
    }

    /**
     * Step to choose a delivery time.
     */
    @Epic("Order Flow")
    @Feature("Delivery Scheduling")
    @Story("Select Delivery Time")
    @Severity(SeverityLevel.NORMAL)
    @Description("This step selects an available delivery time.")
    @When("I select the available time")
    public void selectTime() {
        clickElement(AppiumBy.accessibilityId("16:00-18:00"));
    }

    /**
     * Step to confirm delivery slot and time.
     */
    @Epic("Order Flow")
    @Feature("Delivery Scheduling")
    @Story("Confirm Delivery Time")
    @Severity(SeverityLevel.NORMAL)
    @Description("This step confirms the selected delivery slot and time.")
    @When("I click on Confirm button")
    public void clickOnConfirmButton() {
        clickElement(By.xpath("//android.view.View[@content-desc='Confirm']"));
    }

    /**
     * Step to scroll down to the payment method section.
     */
    @Epic("Order Flow")
    @Feature("Payment")
    @Story("Access Payment Methods")
    @Severity(SeverityLevel.MINOR)
    @Description("This step scrolls down to the payment method section.")
    @When("scroll down to payement method")
    public void scrollDownToPaymentMethod() {
        scrollToElementByDescription("Cash on delivery");
    }

    /**
     * Step to select the preferred payment method.
     */
    @Epic("Order Flow")
    @Feature("Payment")
    @Story("Select Payment Method")
    @Severity(SeverityLevel.CRITICAL)
    @Description("This step selects the Cash on Delivery payment method.")
    @When("I select the payement method i need it")
    public void selectPaymentMethod() {
        clickElement(AppiumBy.accessibilityId("Cash on delivery"));
    }

    /**
     * Step to place the order after selecting payment method.
     */
    @Epic("Order Flow")
    @Feature("Place Order")
    @Story("Submit Order")
    @Severity(SeverityLevel.BLOCKER)
    @Description("This step places the order by clicking the Place Order button.")
    @When("I click on place order button")
    public void clickOnPlaceOrderButton() {
        clickElement(AppiumBy.accessibilityId("Place Order"));
    }

    /**
     * Step to verify that the order confirmation message is displayed.
     */
    @Epic("Order Flow")
    @Feature("Order Confirmation")
    @Story("Verify Order Success")
    @Severity(SeverityLevel.BLOCKER)
    @Description("This step asserts that the order confirmation message is displayed.")
    @Then("I should see the confirmation message")
    public void i_should_see_the_confirmation_message() {
        WebElement confirmation = waitForElementVisible(AppiumBy.accessibilityId("Order confirmed"));
        Assert.assertEquals(confirmation.getText(), "Order confirmed");
    }
}
