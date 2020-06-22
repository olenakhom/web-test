package ui.pages;

import static org.assertj.core.api.Assertions.*;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ui.model.AccountPersonalInfo;

public class AccountPage extends BasePage {

    private By womenLink = By.linkText("Women");

    @Step("Account Page : Click Women link")
    public CategoryPage clickWomenLink() {
        getWait().until(ExpectedConditions.elementToBeClickable(womenLink)).click();
        return new CategoryPage();
    }


    @Step("Account Page : Verify page")
    public void verify(AccountPersonalInfo personalInfo) {
        WebElement heading =
            getWait().until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1")));
        assertThat(heading.getText()).as("Header Title")
            .isEqualTo("MY ACCOUNT");
        assertThat(getDriver().findElement(By.className("account")).getText()).as("Account title contains correct first and last names")
            .isEqualTo(personalInfo.getFirstName() + " " + personalInfo.getLastName());

        assertThat(getDriver().findElement(By.className("info-account")).getText()).as("Account info")
            .contains("Welcome to your account.");
        assertThat(getDriver().findElement(By.className("logout")).isDisplayed()).as("Logout button should be displayed")
            .isTrue();

        assertThat(getDriver().getCurrentUrl()).as("Current Url")
            .contains("controller=my-account");
    }

}
