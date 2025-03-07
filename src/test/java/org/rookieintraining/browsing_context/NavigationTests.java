package org.rookieintraining.browsing_context;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.bidi.browsingcontext.BrowsingContext;
import org.openqa.selenium.bidi.browsingcontext.NavigationResult;
import org.openqa.selenium.bidi.browsingcontext.ReadinessState;
import org.rookieintraining.BaseTest;

import java.util.Objects;

public class NavigationTests extends BaseTest {

    @Test
    public void navigate_to_page_same_window() throws InterruptedException {
        String currentWindow = driver.getWindowHandle();
        BrowsingContext context = new BrowsingContext(driver, currentWindow);
        context.navigate("https://www.amazon.com/");

        Thread.sleep(5000);
    }

    @Test
    public void navigate_to_page_new_tab() throws InterruptedException {
        BrowsingContext context = new BrowsingContext(driver, WindowType.TAB);
        context.navigate("https://www.amazon.com/");

        Thread.sleep(5000);
    }

    @Test
    public void navigate_to_page_new_window() throws InterruptedException {
        BrowsingContext context = new BrowsingContext(driver, WindowType.WINDOW);
        context.navigate("https://www.amazon.com/");

        Thread.sleep(5000);
    }

    @Test
    public void navigate_to_page_with_readiness_state() {
        BrowsingContext context = new BrowsingContext(driver, WindowType.WINDOW);
        context.navigate("https://www.amazon.com/", ReadinessState.COMPLETE);
    }

    @Test
    public void close_a_window() {
        BrowsingContext context = new BrowsingContext(driver, WindowType.WINDOW);
        context.navigate("https://www.amazon.com/", ReadinessState.COMPLETE);

        context.close();
    }

    @Test
    public void close_a_tab() {
        BrowsingContext context = new BrowsingContext(driver, WindowType.TAB);
        context.navigate("https://www.amazon.com/", ReadinessState.COMPLETE);

        context.close();
    }

    @Test
    public void activate_a_given_tab() {
        BrowsingContext amazon = new BrowsingContext(driver, WindowType.WINDOW);
        BrowsingContext flipkart = new BrowsingContext(driver, WindowType.TAB);

        NavigationResult navigationResult = flipkart.navigate("https://www.flipkart.com", ReadinessState.COMPLETE);
        System.out.println(navigationResult.getNavigationId());

        amazon.activate();
        amazon.navigate("https://www.amazon.com", ReadinessState.COMPLETE);

        flipkart.activate();
    }

    @Test
    public void navigate_and_go_forward_and_backward() {
        BrowsingContext browser = new BrowsingContext(driver, driver.getWindowHandle());
        browser.navigate("https://www.amazon.com", ReadinessState.COMPLETE);
        browser.navigate("https://www.flipkart.com", ReadinessState.COMPLETE);

        browser.back();
        Assertions.assertTrue(Objects.requireNonNull(driver.getCurrentUrl()).contains("amazon"),
                "Unable to traverse back");

        browser.forward();
        Assertions.assertTrue(driver.getCurrentUrl().contains("flipkart"),
                "Unable to traverse forward");

    }

}
