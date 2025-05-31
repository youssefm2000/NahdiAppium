package stepDefinitions;

import io.appium.java_client.AppiumBy;
import io.cucumber.java.en.*;
import io.qameta.allure.*; // Required for Allure annotations
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

    /**
     * Utility method to wait for an element to be clickable.
     *
     * @param locator The locator of the element to wait for.
     * @return WebElement once it is clickable.
     */
    private WebElement waitForElementToBeClickable(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    /**
     * Step to launch the Nahdi app and navigate past splash screen.
     */
    @Epic("Login Flow")
    @Feature("Launch App")
    @Story("Launch the Nahdi app")
    @Severity(SeverityLevel.CRITICAL)
    @Description("This step launches the Nahdi app and skips splash screen.")
    @Given("the Nahdi app is launched")
    public void applanuched() throws IOException {
        AppiumDriverManager.getDriver();
        driver.findElement(AppiumBy.androidUIAutomator(
                "new UiSelector().className(\"android.widget.ImageView\").instance(1)"
        )).click();
        driver.findElement(AppiumBy.androidUIAutomator(
                "new UiSelector().className(\"android.widget.ImageView\").instance(1)"
        )).click();
    }

    /**
     * Step to select the preferred language on the app.
     */
    @Epic("Login Flow")
    @Feature("Language Selection")
    @Story("Select Language")
    @Severity(SeverityLevel.NORMAL)
    @Description("This step selects the English language on the app.")
    @When("I select the language")
    public void selectLanguage() {
        waitForElementToBeClickable(By.xpath("//*[@content-desc='English']")).click();
    }

    /**
     * Step to click on the continue button after language selection.
     */
    @Epic("Login Flow")
    @Feature("Language Selection")
    @Story("Confirm Language")
    @Severity(SeverityLevel.NORMAL)
    @Description("This step clicks the Continue button after selecting a language.")
    @When("I click on continue button")
    public void clickOnContinueButton() {
        waitForElementToBeClickable(By.xpath("//*[@content-desc='Continue']")).click();
    }

    /**
     * Step to select the user's country from the list.
     */
    @Epic("Login Flow")
    @Feature("Country Selection")
    @Story("Select Country")
    @Severity(SeverityLevel.NORMAL)
    @Description("This step selects Saudi Arabia as the country.")
    @When("I select the country")
    public void SelectCountry() {
        waitForElementToBeClickable(By.xpath("//*[@content-desc='Saudi Arabia']")).click();
    }

    /**
     * Step to click continue after selecting the country.
     */
    @Epic("Login Flow")
    @Feature("Country Selection")
    @Story("Confirm Country")
    @Severity(SeverityLevel.NORMAL)
    @Description("This step clicks the Continue button after selecting the country.")
    @When("clicking on continue button")
    public void clickingOnContinueButton() {
        waitForElementToBeClickable(By.xpath("//*[@content-desc='Continue']")).click();
    }

    /**
     * Step to skip any optional onboarding screens.
     */
    @Epic("Login Flow")
    @Feature("Onboarding")
    @Story("Skip Onboarding")
    @Severity(SeverityLevel.MINOR)
    @Description("This step clicks the Skip button to skip onboarding.")
    @When("I click on Skip button")
    public void clickOnSkipButton() {
        waitForElementToBeClickable(By.xpath("//*[@content-desc='Skip']")).click();
    }

    /**
     * Step to enter the username into the login form.
     */
    @Epic("Login Flow")
    @Feature("Credentials Entry")
    @Story("Enter Username")
    @Severity(SeverityLevel.CRITICAL)
    @Description("This step enters the username into the login field.")
    @When("I enter username")
    public void usernameField() {
        driver.findElement(By.xpath("//android.widget.EditText")).click();
        driver.findElement(By.xpath("//android.widget.EditText"))
                .sendKeys(testData.getTestData("username"));
    }

    /**
     * Step to proceed to the next login screen.
     */
    @Epic("Login Flow")
    @Feature("Navigation")
    @Story("Continue After Username")
    @Severity(SeverityLevel.NORMAL)
    @Description("This step clicks Continue after entering the username.")
    @When("I click on continue")
    public void clickOnContinue() {
        waitForElementToBeClickable(By.xpath("//*[@content-desc='Continue']")).click();
    }

    /**
     * Step to switch login method to password-based authentication.
     */
    @Epic("Login Flow")
    @Feature("Login Method")
    @Story("Switch to Password Login")
    @Severity(SeverityLevel.NORMAL)
    @Description("This step switches login method to use password.")
    @When("I click on login with password instead button")
    public void clickOnLoginWithPasswordButton() {
        waitForElementToBeClickable(By.xpath("//*[@content-desc='Login with password instead']")).click();
    }

    /**
     * Step to enter the password into the login form.
     */
    @Epic("Login Flow")
    @Feature("Credentials Entry")
    @Story("Enter Password")
    @Severity(SeverityLevel.CRITICAL)
    @Description("This step enters the password into the password field.")
    @When("I enter password")
    public void passwordField() {
        waitForElementToBeClickable(By.xpath("//android.widget.EditText"))
                .sendKeys(testData.getTestData("password"));
    }

    /**
     * Step to continue to the home screen after entering credentials.
     */
    @Epic("Login Flow")
    @Feature("Navigation")
    @Story("Login Submission")
    @Severity(SeverityLevel.CRITICAL)
    @Description("This step clicks Continue after entering the password to reach the home page.")
    @When("I click on Continue to see home page")
    public void clickingOnContinue() {
        waitForElementToBeClickable(By.xpath("//*[@content-desc='Continue']")).click();
    }

    /**
     * Step to verify that the homepage is displayed after successful login.
     */
    @Epic("Login Flow")
    @Feature("Verification")
    @Story("Verify Home Page")
    @Severity(SeverityLevel.BLOCKER)
    @Description("This step verifies that the home page is displayed after login.")
    @Then("I should see the homepage")
    public void checkTitleOfHomePage() {
        WebElement homeTitle = waitForElementToBeClickable(By.xpath("//*[contains(@content-desc, 'Home')]"));
        Assert.assertTrue(homeTitle.isDisplayed(), "Home page is not displayed.");
    }
}
