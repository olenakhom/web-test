package ui.pages;

import io.qameta.allure.Step;
import static org.assertj.core.api.Assertions.assertThat;
import org.openqa.selenium.By;

public class OrderConfirmationPage extends BasePage {

    private By header = By.cssSelector("h1");
    private By orderTitle = By.xpath("//*[@class='cheque-indent']/strong");
    private By stepsTab = By.xpath("//li[@class='step_done step_done_last four']");
    private By currentStep = By.xpath("//li[@id='step_end' and @class='step_current last']");

    @Step("Order Confirmation Page : Verify page")
    public void verify() {
        assertThat(waitForVisibility(header).getText()).as("Header Title")
            .isEqualTo("ORDER CONFIRMATION");
        assertThat(getTextFrom(orderTitle)).as("Order title")
            .contains("Your order on My Store is complete.");
        assertThat(isDisplayed(stepsTab)).as("Steps list should be displayed")
            .isTrue();
        assertThat(isDisplayed(currentStep)).as("Current step should be displayed")
            .isTrue();
        assertThat(getPageUrl()).as("Current Url")
            .contains("controller=order-confirmation");
    }

}
