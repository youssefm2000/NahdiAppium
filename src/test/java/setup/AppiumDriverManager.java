package setup;
import io.appium.java_client.android.AndroidDriver;
import java.net.URL;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.qameta.allure.Step;
import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class AppiumDriverManager {

    public static AndroidDriver driver;
    public static AppiumDriverLocalService service;

    // method to start emulator
    @Step("Test Setup - Open the appium server and emulator")
    public static void getDriver() throws IOException {

        //code to start appium server automatic without manual command
        service = new AppiumServiceBuilder().withAppiumJS(new File("C://Users//yelshemy//AppData//Roaming//npm//node_modules//appium//build//lib//main.js"))
                .withIPAddress("127.0.0.1")
                .usingPort(4723).build();
        service.start();

        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("Youssef");
        options.setApp(System.getProperty("user.dir") + "//src//test//resources//TestDataFiles//NahdiApp.apk");
        options.setCapability("autoGrantPermissions", true);
        options.setCapability("uiautomator2ServerInstallTimeout", 120000);  // 60s
        options.setCapability("uiautomator2ServerLaunchTimeout", 120000);
        options.setCapability("ignoreHiddenApiPolicyError", true);
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    // method to close emulator and the appium server
    @Step("Test Teardown - close the appium server and emulator")
    public static void quitDriver() {
        driver.quit();
    }
}