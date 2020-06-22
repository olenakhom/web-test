package ui.webdriver;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import java.util.List;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import static ui.webdriver.BrowserWaits.waitForElementsPresent;
import static utils.AttachmentReportUtils.attachText;

public final class BrowserInteraction {

    private static final Logger LOGGER = LogManager.getLogger(BrowserInteraction.class);

    private BrowserInteraction() {
    }

    private static final long TIME_OUT_SAFARI_MILLIS = 7000;

    private static WebDriver getDriver() {
        return WebDriverManager.getWebDriver();
    }

    public static ElementsCollection getWebElements(final By by) {
        ElementsCollection elements = $$(by);
        LOGGER.info("getWebElements: get selenide web elements {}. Number of elements is {}", by, elements.size());
        return elements;
    }

    @Step("Does element exist?")
    public static boolean exists(final By by) {
        ElementsCollection elements = $$(by);
        boolean exists = !elements.isEmpty();
        LOGGER.info("exists: does element {} exist? {}", by, exists);
        attachText(String.valueOf(exists), "Exists?");
        return exists;
    }

    @Step("Is element visible?")
    public static boolean isVisible(final By by) {
        boolean isVisible = $(by).isDisplayed();
        LOGGER.info("isVisible: is element {} visible? {}", by, isVisible);
        attachText(String.valueOf(isVisible), "Is Visible?");
        return isVisible;
    }

    @Step("Click on element with JS if exists")
    public static void clickIfExistWithJS(final By by) {
        if (!exists(by)) {
            LOGGER.info("clickWithJSIfExists: element {} doesn't exist", by);
            return;
        }
        clickWithJS(by);
        LOGGER.info("clickWithJSIfExists: element {} was clicked", by);
    }

    @Step("Hover element if exists")
    public static void hoverElementIfExists(final By by) {
        if (exists(by)) {
            LOGGER.error("User Account Menu Is Not Available!");
            return;
        }

        final Actions actions = new Actions(getDriver());
        actions.moveToElement($(by));
    }

    public static SelenideElement getElement(final By by) {
        return $(by);
    }

    public static void switchToFrame(String nameOrId) {
        getDriver().switchTo().frame(nameOrId);
    }

    public static void switchToParentFrame() {
        getDriver().switchTo().parentFrame();
    }

    @Step("Click on element")
    public static void click(WebElement element) {
        element.click();
        LOGGER.info("click: element {} was clicked", element);
    }

    @Step("Click on element")
    public static void click(By by) {
        $(by).click();
        LOGGER.info("click: element {} was clicked", by);
    }

    @Step("Click on element if exists")
    public static void clickIfExist(By by) {
        if (!exists(by)) {
            LOGGER.info("clickIfExist: element {} doesn't exist", by);
            return;
        }
        $(by).click();
        LOGGER.info("clickIfExist: element {} was clicked", by);
    }

    @Step("Click on element with JS")
    public static void clickWithJS(final WebElement element) {
        getJavascriptExecutor().executeScript("arguments[0].click();", element);
        LOGGER.info("clickWithJS: element {} was clicked with JS", element);
    }

    @Step("Click on element with JS")
    public static void clickWithJS(final By by) {
        getJavascriptExecutor().executeScript("arguments[0].click();", getDriver().findElement(by));
        LOGGER.info("clickWithJS: element {} was clicked with JS", by);
    }

    @Step("Fill value '{value}' if element is visible")
    public static void fillIfVisible(final By by, final String value) {
        if (!exists(by) || !isVisible(by)) {
            LOGGER.info("fillIfVisible: element {} doesn't exist or isn't visible", by);
            return;
        }
        if (WebDriverManager.isSafariBrowser()) {
            fill(by, value);
            return;
        }
        clearValueByKeyBoardAction(by);
        clickWithJS(by);
        fill(by, value);
    }

    @Step("Fill value '{value}' with JS if element is visible")
    public static void fillIfVisibleWithJs(final By by, final String value) {
        if (!exists(by) || !isVisible(by)) {
            LOGGER.info("fillIfVisibleWithJs: element {} doesn't exist or isn't visible", by);
            return;
        }
        clearValueByKeyBoardAction(by);
        clickWithJS(by);
        fillWithJs(getElement(by), value);
    }

    @Step("Fill value '{value}'")
    public static SelenideElement fill(final By by, final String value) {
        SelenideElement element = $(by).setValue(value);
        LOGGER.info("fill: element {} was filled with text \"{}\"", by, value);
        return element;
    }

    @Step("Fill value '{value}' for elements")
    public static void fill(final List<SelenideElement> elements, final String value) {
        for (SelenideElement element : elements) {
            element.setValue(value);
        }
        LOGGER.info("fill: elements {} were filled with text \"{}\"", elements, value);
    }

    @Step("Fill value '{value}' with JS")
    public static void fillWithJs(WebElement element, String value) {
        String javascript = "arguments[0].value = arguments[1];arguments[0].dispatchEvent(new Event('input'));";
        getJavascriptExecutor().executeScript(javascript, element, value);
        LOGGER.info("fillWithJs: element {} was filled with text \"{}\"", element, value);
    }

    @Step("Fill value '{value}' with JS")
    public static void fillWithJs(By by, String value) {
        String javascript = "arguments[0].value = arguments[1];arguments[0].dispatchEvent(new Event('input'));";
        getJavascriptExecutor().executeScript(javascript, getDriver().findElement(by), value);
        LOGGER.info("fillWithJs: element {} was filled with text \"{}\"", by, value);
    }

    @Step("Clear value from textfield")
    public static void clearValueByKeyBoardAction(final By by) {
        $(by).clear();
        $(by).sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
    }

    @Step("Get text from element")
    public static String getText(final By by) {
        String text = $(by).getText();
        LOGGER.info("getText: text {} was retrieved from element {}", text, by);
        return text;
    }

    public static String getValue(final By by) {
        String value = $(by).getValue();
        LOGGER.info("getValue: value {} was retrieved from element {}", value, by);
        return value;
    }

    public static void selectByIndex(final By by, final int index) {
        if (!isVisible(by)) {
            LOGGER.info("selectByIndex: element {} isn't visible", by);

            // TODO: move optional check to page objects
            return;
        }
        $(by).selectOption(index);
        LOGGER.info("selectByIndex: element {} was selected by index {}", by, index);
    }

    @Step("Select by text {text}")
    public static void selectByTextIfVisible(final By by, final String text) {
        if (!exists(by) || !isVisible(by)) {
            LOGGER.info("selectByText: element {} isn't visible", by);
            return;
        }
        if (WebDriverManager.isSafariBrowser()) {
            getJavascriptExecutor().executeScript("var select = arguments[0]; for(var i = 0; i < select.options.length; i++)"
                + "{ if(select.options[i].text == arguments[1]){ select.options[i].selected = true; } }", $(by), text);
            return;
        }
        $(by).selectOptionContainingText(text);
        LOGGER.info("selectByText: element {} was selected by text \"{}\"", by, text);
    }

    @Step("Select by value {value}")
    public static void selectByValue(final By by, String value) {
        if (!isVisible(by)) {
            LOGGER.info("selectByValue: element {} isn't visible", by);

            // TODO: move optional check to page objects
            return;
        }
        if (WebDriverManager.isSafariBrowser()) {
            getJavascriptExecutor().executeScript("var select = arguments[0]; for(var i = 0; i < select.options.length; i++)"
                + "{ if(select.options[i].value == arguments[1]){ select.options[i].selected = true; } }", $(by), value);
            return;
        }
        $(by).selectOptionByValue(value);
        LOGGER.info("selectByValue: element {} was selected by value {}", by, value);
    }

    @Step("Click submit button")
    public static void clickSubmitButton(By by) {
        $(by).submit();
        LOGGER.info("clickSubmitButton: element {} was clicked", by);
    }

    public static void setAttribute(By by, String attribute, String value) {
        if (!exists(by)) {
            LOGGER.info("setAttribute: element {} doesn't exist", by);

            // TODO: move optional check to page objects
            return;
        }
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].setAttribute('" + attribute + "', '" + value + "')",
            $(by));
        LOGGER.info("setAttribute: value {} was set to element {} with attribute {}", value, by, attribute);
    }

    public String getAttribute(By by, String attribute) {
        LOGGER.info("getAttribute: get value from element {} with attribute {}", by, attribute);
        return $(by).getAttribute(attribute);
    }

    public static void scrollPageBottom() {
        JavascriptExecutor jse = (JavascriptExecutor) getDriver();
        jse.executeScript("window.scrollTo(0, document.body.scrollHeight);");
        LOGGER.info("scrollPageBottom: page was scrolled down to bottom");
    }

    public static void scrollToElement(By by) {
        $(by).scrollTo();
        LOGGER.info("scrollToElement: page was scrolled to element {}", by);
    }

    @Step("Switch to the newest tab")
    public static void switchToNewestTab() {
        Set<String> tabHandles = getDriver().getWindowHandles();
        LOGGER.info("Number of available tabs = {}", tabHandles.size());

        if (WebDriverManager.isSafariBrowser() && tabHandles.size() > 1) {
            BrowserWaits.waitExactly(TIME_OUT_SAFARI_MILLIS);
            String currentTab = getDriver().getWindowHandle();
            tabHandles.remove(currentTab);
        }
        // the actual type isLinkedHashSet
        String newestTabHandle = null;

        for (String tabHandle : tabHandles) {
            newestTabHandle = tabHandle;
        }

        if (newestTabHandle == null) {
            LOGGER.info("switchToNewestTab: browser window was null");
        }

        getDriver().switchTo().window(newestTabHandle);
        LOGGER.info("switchToNewestTab: browser switched to window: {}", getDriver().getTitle());
    }

    @Step("Switch to the first window")
    public static void switchToFirstWindow() {
        Selenide.switchTo().window(0);
    }

    @Step("Switch to the window with name/title/handle {nameOrTitleOrHandle}")
    public static void switchToWindow(String nameOrTitleOrHandle) {
        if (WebDriverManager.isSafariBrowser()) {
            BrowserWaits.waitExactly(TIME_OUT_SAFARI_MILLIS);
        }
        Selenide.switchTo().window(nameOrTitleOrHandle);
    }

    public static List<String> getOptionTextsWithoutDefault(By parent) {
        ElementsCollection elements = getElement(parent).$$(By.tagName("option"));
        List<String> options = elements.texts();
        options.remove(0);
        return options;
    }

    public static String getCurrentUrl() {
        return getDriver().getCurrentUrl();
    }

    public static void addCookie(final Cookie cookie) {
        getDriver().manage().addCookie(cookie);
    }

    public static Cookie getCookieByName(String cookieName) {
        return getDriver().manage().getCookieNamed(cookieName);
    }

    public static JavascriptExecutor getJavascriptExecutor() {
        return (JavascriptExecutor) getDriver();
    }

    @Step("Refresh page")
    public static void refreshPage() {
        getDriver().navigate().refresh();
    }

    @Step("Remove cookie by name {cookieName}")
    public static void removeCookieByName(final String cookieName) {
        getDriver().manage().deleteCookieNamed(cookieName);
    }

    @Step("Is alert window shown")
    public static boolean isAlertWindowShown() {
        try {
            getDriver().switchTo().alert();
        } catch (NoAlertPresentException e) {
            attachText(String.valueOf(false), "Is alert window shown?");
            return false;
        }
        getDriver().switchTo().defaultContent();
        attachText(String.valueOf(true), "Is alert window shown?");
        return true;
    }

    @Step("Skip it if the element is not shown with timeout: '{timeOutInSeconds} sec.'")
    public static void skipIfElementNotVisibleWithTimeout(By element, int timeOutInSeconds) {
        try {
            waitForElementsPresent(element, timeOutInSeconds);
        } catch (TimeoutException ex) {
            LOGGER.info("skipIfElementNotVisibleWithTimeout: element {} doesn't exist", element);
            return;
        }

        if (!isVisible(element)) {
            LOGGER.info("skipIfElementNotVisibleWithTimeout: element {} isn't visible", element);
            return;
        }
    }

    @Step("Get number of opened browser windows")
    public static int getNumberOfOpenedWindows() {
        return getDriver().getWindowHandles().size();
    }

    @Step("Hide element")
    public static void hideElement(By by) {
        boolean exists = exists(by);
        LOGGER.info("hideElement: does element {} exist? {}", by, exists);

        attachText(String.valueOf(exists), "Exists?");

        LOGGER.info("hideElement: {}", by);
        if (exists) {
            setAttribute(by, "style", "visibility: none");
        }
    }

}
