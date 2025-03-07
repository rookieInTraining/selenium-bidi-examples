package org.rookieintraining.browsing_context;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.bidi.browsingcontext.BrowsingContext;
import org.openqa.selenium.bidi.browsingcontext.ReadinessState;
import org.rookieintraining.BaseTest;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;

public class ScreenshotTests extends BaseTest {

    @Test
    public void navigate_to_page_same_window_capture_ss() throws InterruptedException, IOException {
        BrowsingContext context = new BrowsingContext(driver, driver.getWindowHandle());
        context.navigate("https://www.amazon.com/", ReadinessState.COMPLETE);

        String ss = context.captureScreenshot();
        byte[] decodedImage = Base64.getDecoder().decode(ss);

        String filePath = System.getProperty("user.dir") + "\\" + Thread.currentThread().getId() + ".png";
        Files.write(Path.of(filePath), decodedImage);
    }

}
