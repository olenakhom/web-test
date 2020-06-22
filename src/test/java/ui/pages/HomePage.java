package ui.pages;

import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends BasePage{

    private static final Logger LOGGER = LogManager.getLogger(HomePage.class);

    private By loginButton = By.className("login");

    @Step("Home Page : Open page")
    public void openPage() {
        getDriver().get(System.getProperty("site.base.url"));
    }

    @Step("Home Page : Click on login button")
    public LoginPage clickLoginButton() {
        getWait().until(ExpectedConditions.elementToBeClickable(loginButton)).click();
        return new LoginPage();
    }

}
