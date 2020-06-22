package ui.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class OrderPage extends BasePage {

    private By submitOrderButton = By.xpath("//*[@id='cart_navigation']/button");
    private By bankWireButton = By.className("bankwire");
    private By processCarrierButton = By.name("processCarrier");
    private By processAddressButton = By.name("processAddress");
    private By proceedToCheckoutButton = By.xpath("//*[contains(@class,'cart_navigation')]/a[@title='Proceed to checkout']");
    private By uniformButton = By.id("uniform-cgv");

    @Step("Order Page : Click on submit order button")
    public OrderConfirmationPage clickSubmitOrderButton() {
        waitForClickableAndClick(submitOrderButton);
        return new OrderConfirmationPage();
    }

    @Step("Order Page : Click on bank wire button")
    public void clickBankWireButton() {
        waitForClickableAndClick(bankWireButton);
    }

    @Step("Order Page : Click on process carrier button")
    public void clickProcessCarrierButton() {
        waitForClickableAndClick(processCarrierButton);
    }

    @Step("Order Page : Click on uniform button")
    public void clickUniformButton() {
        waitForClickableAndClick(uniformButton);
    }

    @Step("Order Page : Click on process address button")
    public void clickProcessAddressButton() {
        waitForClickableAndClick(processAddressButton);
    }

    @Step("Order Page : Click proceed to checkout button")
    public void clickProceedToCheckoutButton() {
        waitForClickableAndClick(proceedToCheckoutButton);
    }

}
