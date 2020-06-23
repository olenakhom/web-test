package ui.src.pages;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.AccessLevel;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ui.src.webdriver.WebDriverManager;

public abstract class BasePage {

    @Getter(AccessLevel.PROTECTED)
    private WebDriver driver;
    @Getter(AccessLevel.PROTECTED)
    private WebDriverWait wait;

    BasePage() {
        this.driver = WebDriverManager.getWebDriver();
        this.wait = new WebDriverWait(driver, 15);
    }

    @Step("Wait until clickable and click on element {by}")
    protected void waitForClickableAndClick(By by) {
        getWait().until(ExpectedConditions.elementToBeClickable(by)).click();
    }

    @Step("Wait until clickable and fill input by element {by}")
    protected void waitForClickableAndFill(By by, String text) {
        getWait().until(ExpectedConditions.elementToBeClickable(by))
            .sendKeys(text);
    }

    @Step("Wait until frame available and switch to frame {by}")
    protected void waitForAvailableAndSwitchToFrame(By frame) {
        getWait().until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frame));;
    }

    @Step("Wait until visible element {by}")
    protected WebElement waitForVisibility(By by) {
        return getWait().until(ExpectedConditions.visibilityOfElementLocated(by));
    }
    @Step("Find element {by}")
    protected SelenideElement findElement(By by) {
        return $(by);
    }

    @Step("Get text from element {by}")
    protected String getTextFrom(By by) {
        return findElement(by).getText();
    }

    @Step("Is displayed element {by}")
    protected boolean isDisplayed(By by) {
        return findElement(by).isDisplayed();
    }

    @Step("Get current page URL")
    protected String getPageUrl() {
        return getDriver().getCurrentUrl();
    }

    @Step("Select element {by} by value {value}")
    protected void selectByValue(By by, String value) {
        findElement(by).selectOptionByValue(value);
    }

    @Step("Select element {by} by text {text}")
    protected void selectByText(By by, String text) {
        findElement(by).selectOptionContainingText(text);
    }

}
