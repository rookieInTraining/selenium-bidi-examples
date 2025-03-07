package org.rookieintraining;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.manager.SeleniumManager;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseTest {

    protected static WebDriver driver;
    protected static WebDriverWait wait;

    @BeforeAll
    public static void init() {
        EdgeOptions options = new EdgeOptions();
        options.enableBiDi();
        driver = new EdgeDriver(options);
    }

    @AfterAll
    public static void teardown() {
        driver.quit();
    }

}
