package ui.src.pages;

import static org.assertj.core.api.Assertions.*;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import ui.src.model.AccountPersonalInfo;

public class AccountPage extends BasePage {

    private By womenLink = By.linkText("Women");
    private By header = By.cssSelector("h1");
    private By accountTitle = By.className("account");
    private By accountInfoTitle = By.className("info-account");
    private By logoutButton = By.className("logout");

    @Step("Account Page : Click women link")
    public CategoryPage clickWomenLink() {
        waitForClickableAndClick(womenLink);
        return new CategoryPage();
    }


    @Step("Account Page : Verify page")
    public void verify(AccountPersonalInfo personalInfo) {
        assertThat(waitForVisibility(header).getText()).as("Header Title")
            .isEqualTo("MY ACCOUNT");
        assertThat(getTextFrom(accountTitle)).as("Account title contains correct first and last names")
            .isEqualTo(personalInfo.getFirstName() + " " + personalInfo.getLastName());
        assertThat(getTextFrom(accountInfoTitle)).as("Account info")
            .contains("Welcome to your account.");
        assertThat(isDisplayed(logoutButton)).as("Logout button should be displayed")
            .isTrue();
        assertThat(getPageUrl()).as("Current Url")
            .contains("controller=my-account");
    }

}
