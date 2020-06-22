package ui.junit.extensions;

import java.lang.reflect.Method;
import java.util.Optional;
import lombok.Synchronized;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.WebDriver;
import ui.webdriver.WebDriverManager;
import static common.utils.AttachmentReportUtil.attachFullPageScreenshot;
import static common.utils.AttachmentReportUtil.attachText;

public class WebTestWatcher implements TestWatcher {
    private static final Logger LOGGER = LogManager.getLogger(WebTestWatcher.class);

    @Override
    @Synchronized
    public void testDisabled(ExtensionContext context, Optional<String> reason) {
        LOGGER.info("testDisabled: test skipped \"{}\" from class \"{}\"", context.getDisplayName(),
            context.getTestClass().get().getName());
    }

    @Override
    @Synchronized
    public void testSuccessful(ExtensionContext context) {
        LOGGER.info("testSuccessful: test passed \"{}\" from class \"{}\"", context.getDisplayName(),
            context.getTestClass().get().getName());
    }

    @Override
    @Synchronized
    public void testAborted(ExtensionContext context, Throwable cause) {
        LOGGER.info("testAborted: test aborted \"{}\" from class \"{}\"", context.getDisplayName(),
            context.getTestClass().get().getName());
    }

    @Override
    @Synchronized
    public void testFailed(ExtensionContext context, Throwable cause) {
        final WebDriver driver = WebDriverManager.getWebDriver();
        final String testDisplayName = context.getDisplayName();
        final Class<?> testClass = context.getTestClass().get();
        final Method testMethod = context.getTestMethod().get();

        String testMethodName = testMethod.getName();
        String testClassName = testClass.getName();
        String exception = cause.getMessage();
        LOGGER.info("testFailed: Test failed \"{}\" from class \"{}\" because \"{}\"",
            testDisplayName, testClassName, exception + "Stack trace: " + cause.getStackTrace());

        attachText(driver.getCurrentUrl(), "Page URL");
        attachText(exception, "Exception message");
        attachText(driver.getPageSource(), "Page source");
        attachFullPageScreenshot(driver, testMethodName);
    }

}
