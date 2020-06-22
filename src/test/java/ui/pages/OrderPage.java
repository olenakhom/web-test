package ui.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class OrderPage extends BasePage {

    private By submitOrderButton = By.xpath("//*[@id='cart_navigation']/button");
    private By bankWireButton = By.className("bankwire");
    private By processCarrierButton = By.name("processCarrier");
    private By processAddressButton = By.name("processAddress");
    private By proceedToCheckoutButton = By.xpath("//*[contains(@class,'cart_navigation')]/a[@title='Proceed to checkout']");
    private By uniformButton = By.id("uniform-cgv");

    @Step("Order Page : Click on submit order button")
    public OrderConfirmationPage clickSubmitOrderButton() {
        getWait().until(ExpectedConditions.elementToBeClickable(submitOrderButton)).click();
        return new OrderConfirmationPage();
    }

    @Step("Order Page : Click on bank wire button")
    public void clickBankWireButton() {
        getWait().until(ExpectedConditions.elementToBeClickable(bankWireButton)).click();
    }

    @Step("Order Page : Click on process carrier button")
    public void clickProcessCarrierButton() {
        getWait().until(ExpectedConditions.elementToBeClickable(processCarrierButton)).click();
    }

    @Step("Order Page : Click on uniform button")
    public void clickUniformButton() {
        getWait().until(ExpectedConditions.elementToBeClickable(uniformButton)).click();
    }

    @Step("Order Page : Click on process address button")
    public void clickProcessAddressButton() {
        getWait().until(ExpectedConditions.elementToBeClickable(processAddressButton)).click();
    }

    @Step("Order Page : Click proceed to checkout button")
    public void clickProceedToCheckoutButton() {
     getWait().until(ExpectedConditions.visibilityOfElementLocated(proceedToCheckoutButton)).click();
    }

}
