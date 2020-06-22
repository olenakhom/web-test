package ui.pages;

import static com.codeborne.selenide.Selenide.open;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

public class HomePage extends BasePage {

    private static final Logger LOGGER = LogManager.getLogger(HomePage.class);

    private By loginButton = By.className("login");

    @Step("Home Page : Open page")
    public void openPage() {
        open(System.getProperty("site.base.url"));
    }

    @Step("Home Page : Click on login button")
    public LoginPage clickLoginButton() {
        waitForClickableAndClick(loginButton);
        return new LoginPage();
    }

}
