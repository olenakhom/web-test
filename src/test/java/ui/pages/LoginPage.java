package ui.pages;

import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage{

    private static final Logger LOGGER = LogManager.getLogger(LoginPage.class);

    private By emailCreateInput = By.id("email_create");
    private By submitCreateButton = By.id("SubmitCreate");
    private By submitLoginButton = By.id("SubmitLogin");

    private By emailInput = By.id("email");
    private By passwordInput = By.id("passwd");

    @Step("Home Page : Fill email {email} to create account")
    public void fillEmailCreate(String email) {
        getWait().until(ExpectedConditions.elementToBeClickable(emailCreateInput))
            .sendKeys(email);
    }

    @Step("Home Page : Click on submit create button")
    public RegistrationPage clickSubmitCreateButton() {
        getWait().until(ExpectedConditions.elementToBeClickable(submitCreateButton))
            .click();
        return new RegistrationPage();
    }

    @Step("Home Page : Click on submit login button")
    public AccountPage clickSubmitLoginButton() {
        getWait().until(ExpectedConditions.elementToBeClickable(submitLoginButton))
            .click();
        return new AccountPage();
    }

    @Step("Home Page : Fill in login form")
    public void fillFormToLogin(String email, String password){
        getWait().until(ExpectedConditions.elementToBeClickable(emailInput))
            .sendKeys(email);
        getDriver().findElement(passwordInput).sendKeys(password);
    }

}
