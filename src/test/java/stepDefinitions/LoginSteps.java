package stepDefinitions;
import io.appium.java_client.AppiumBy;
import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import setup.AppiumDriverManager;
import utils.JsonFileManager;
import java.io.IOException;
import java.time.Duration;

public class LoginSteps extends AppiumDriverManager {

    JsonFileManager testData = new JsonFileManager("src/test/resources/TestDataFiles/LoginData.json");

    private WebElement waitForElementToBeClickable(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    @Given("the Nahdi app is launched")
    public void the_nahdi_app_is_launched() throws IOException {
        AppiumDriverManager.getDriver();
        driver.findElement(AppiumBy.androidUIAutomator(
                "new UiSelector().className(\"android.widget.ImageView\").instance(1)"
        )).click();
        driver.findElement(AppiumBy.androidUIAutomator(
                "new UiSelector().className(\"android.widget.ImageView\").instance(1)"
        )).click();

    }

    @When("I select the language")
    public void i_select_the_language() {
        waitForElementToBeClickable(By.xpath("//*[@content-desc='English']")).click();
    }

    @When("I click on continue button")
    public void i_click_on_continue_button() {
        waitForElementToBeClickable(By.xpath("//*[@content-desc='Continue']")).click();
    }

    @When("I select the country")
    public void i_select_the_country() {
        waitForElementToBeClickable(By.xpath("//*[@content-desc='Saudi Arabia']")).click();
    }

    @When("clicking on continue button")
    public void clicking_on_continue_button() {
        waitForElementToBeClickable(By.xpath("//*[@content-desc='Continue']")).click();
    }

    @When("I click on Skip button")
    public void i_click_on_skip_button() {
        waitForElementToBeClickable(By.xpath("//*[@content-desc='Skip']")).click();
    }

    @When("I enter username")
    public void i_enter_username() {
        driver.findElement(By.xpath("//android.widget.EditText")).click();
        driver.findElement(By.xpath("//android.widget.EditText"))
                .sendKeys(testData.getTestData("username"));
    }

    @When("I click on continue")
    public void i_click_on_continue() {
        waitForElementToBeClickable(By.xpath("//*[@content-desc='Continue']")).click();
    }

    @When("I click on login with password instead button")
    public void i_click_on_login_with_password_instead_button() {
        waitForElementToBeClickable(By.xpath("//*[@content-desc='Login with password instead']")).click();
    }

    @When("I enter password")
    public void i_enter_password() {
        waitForElementToBeClickable(By.xpath("//android.widget.EditText"))
                .sendKeys(testData.getTestData("password"));
    }

    @When("I click on Continue to see home page")
    public void i_click_on_continue_to_see_home_page() {
        waitForElementToBeClickable(By.xpath("//*[@content-desc='Continue']")).click();
    }

    @Then("I should see the homepage")
    public void i_should_see_the_homepage() {
        WebElement homeTitle = waitForElementToBeClickable(By.xpath("//*[contains(@content-desc, 'Home')]"));
        Assert.assertTrue(homeTitle.isDisplayed(), "Home page is not displayed.");
    }
}
