package Hooks;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import setup.AppiumDriverManager;
import java.io.IOException;

public class Hooks {
    @Before
    public void setUp() throws IOException {
        AppiumDriverManager.getDriver();
    }

    @After
    public void tearDown() {
        AppiumDriverManager.quitDriver();
    }
}


