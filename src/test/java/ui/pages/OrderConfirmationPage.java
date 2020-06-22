package ui.pages;

import io.qameta.allure.Step;
import static org.assertj.core.api.Assertions.assertThat;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ui.model.AccountPersonalInfo;

public class OrderConfirmationPage extends BasePage {

    @Step("Account Page : Verify page")
    public void verify() {
        WebElement heading =
            getWait().until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1")));
        assertThat(heading.getText()).as("Header Title")
            .isEqualTo("ORDER CONFIRMATION");
        assertThat(getDriver().findElement(By.xpath("//*[@class='cheque-indent']/strong")).getText()).as("Order title")
            .contains("Your order on My Store is complete.");

        assertThat(getDriver().findElement(By.xpath("//li[@class='step_done step_done_last four']")).isDisplayed()).as("Steps list should be displayed")
            .isTrue();
        assertThat(getDriver().findElement(By.xpath("//li[@id='step_end' and @class='step_current last']")).isDisplayed()).as("Current step should be displayed")
            .isTrue();
        assertThat(getDriver().getCurrentUrl()).as("Current Url")
            .contains("controller=order-confirmation");
    }

}
