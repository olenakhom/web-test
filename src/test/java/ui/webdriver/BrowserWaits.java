package ui.webdriver;

import static com.codeborne.selenide.Selenide.$;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public final class BrowserWaits {

    private static final Logger LOGGER = LogManager.getLogger(BrowserWaits.class);

    private static final long TIME_OUT_IN_SECONDS_DEFAULT = 40;

    private BrowserWaits() {
    }

    public static void waitForElementPresent(WebElement element) {
        LOGGER.info("waitForElementPresent: wait for visibility of web element: {}", element.toString());
        waitFor(TIME_OUT_IN_SECONDS_DEFAULT).until(ExpectedConditions.visibilityOf(element));
    }

    public static void waitForElementNotPresent(WebElement element) {
        LOGGER.info("waitForElementPresent: wait for visibility of web element: {}", element.toString());
        waitFor(TIME_OUT_IN_SECONDS_DEFAULT).until(ExpectedConditions.invisibilityOf(element));
    }

    @Step("Wait for element be clickable")
    public static void waitForElementClickable(WebElement element) {
        LOGGER.info("waitForElementClickable: wait for click-ability of web element: {}", element.toString());
        waitFor(TIME_OUT_IN_SECONDS_DEFAULT).until(ExpectedConditions.elementToBeClickable(element));
    }

    @Step("Wait for elements are present")
    public static void waitForElementsPresent(By by) {
        LOGGER.info("waitForElementsPresent: wait for all elements {} are present", by);
        waitFor(TIME_OUT_IN_SECONDS_DEFAULT).until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
    }

    @Step("Wait for elements are present")
    public static void waitForElementsPresent(By by, long timeOutInSeconds) {
        LOGGER.info("waitForElementsPresent: wait for all elements {} are present", by);
        waitFor(timeOutInSeconds).until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
    }

    public static void waitForElementNotPresent(By by) {
        LOGGER.info("waitForElementNotPresent: wait for element {} is invisible", by);
        waitFor(TIME_OUT_IN_SECONDS_DEFAULT).until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    public static void waitForNumberOfElementsToBe(By by, int number) {
        LOGGER.info("waitForNumberOfElementsToBe: wait for number of elements {} to be {}", by, number);
        waitFor(TIME_OUT_IN_SECONDS_DEFAULT).until(ExpectedConditions.numberOfElementsToBe(by, number));
    }

    @Step("Wait for element is clickable")
    public static void waitForElementClickable(By by) {
        LOGGER.info("waitForElementClickable: wait for element {} to be clickable", by);
        waitFor(TIME_OUT_IN_SECONDS_DEFAULT).until(ExpectedConditions.elementToBeClickable($(by)));
    }

    public static void waitForElementClickable(By by, long timeOutInSeconds) {
        LOGGER.info("waitForElementClickable: wait for element {} to be clickable", by);
        waitFor(timeOutInSeconds).until(ExpectedConditions.elementToBeClickable($(by)));
    }

    public static void waitForTextPresent(By by, String text) {
        LOGGER.info("waitForTextPresent: wait for text {} to be present in element {}", text, by);
        waitFor(TIME_OUT_IN_SECONDS_DEFAULT).until(ExpectedConditions.textToBePresentInElement($(by), text));
    }

    public static void waitForTextNotPresent(By by, String text) {
        LOGGER.info("waitForTextNotPresent: wait for {} not to be present in the element", text);
        waitFor(TIME_OUT_IN_SECONDS_DEFAULT).until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElement($(by), text)));
    }

    public static void waitForPageNotContainsUrl(String url) {
        LOGGER.info("waitForPageNotContainsUrl: wait for url doesn't contain {}", url);
        waitFor(TIME_OUT_IN_SECONDS_DEFAULT).until(ExpectedConditions.not(ExpectedConditions.urlContains(url)));
    }

    public static void waitForJsReturnsTrue(String javascript, long timeOutInSeconds) {
        LOGGER.info("waitForJsReturnsValue: wait for javascript {} returns True in {} seconds", javascript, timeOutInSeconds);
        waitFor(timeOutInSeconds).until(jsReturnsTrue(javascript));
    }

    public static WebDriverWait explicitlyWaitFor(long timeOutInSeconds) {
        return new WebDriverWait(WebDriverManager.getWebDriver(), timeOutInSeconds);
    }

    public static void waitExactly(long timeOutInMilliseconds) {
        try {
            Thread.sleep(timeOutInMilliseconds);
        } catch (InterruptedException e) {
            LOGGER.info("Exception from Thread sleep method was thrown exception {}", e.getMessage());
        }
    }

    private static ExpectedCondition<Boolean> jsReturnsTrue(String javascript) {
        return driver -> (Boolean) ((JavascriptExecutor) driver).executeScript(javascript);
    }

    public static WebDriverWait waitFor(long timeOutInSeconds) {
        return new WebDriverWait(WebDriverManager.getWebDriver(), timeOutInSeconds);
    }

}
