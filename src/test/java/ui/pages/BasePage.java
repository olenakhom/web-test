package ui.pages;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import ui.webdriver.BrowserWaits;
import ui.webdriver.WebDriverManager;

public abstract class BasePage {

    @Getter
    private WebDriver driver;
    @Getter
    private WebDriverWait wait;

    BasePage() {
        this.driver = WebDriverManager.getWebDriver();
        this.wait = BrowserWaits.waitFor(15);
    }

}
