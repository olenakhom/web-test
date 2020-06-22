package ui.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class ProductDetailsPage extends BasePage {

    private By submitButton = By.name("Submit");
    private By proceedToCheckoutButton = By.xpath("//*[@id='layer_cart']//a[@class and @title='Proceed to checkout']");
    private By iframe = By.className("fancybox-iframe");

    @Step("Product Details Page : Switch to popup")
    public void switchToPopup() {
        waitForVisibility(iframe);
        waitForAvailableAndSwitchToFrame(iframe);
    }

    @Step("Product Details Page : Click on submit button")
    public void clickSubmitButton() {
        waitForClickableAndClick(submitButton);
    }

    @Step("Product Details Page : Click proceed to checkout button")
    public OrderPage clickProceedToCheckoutButton() {
     waitForClickableAndClick(proceedToCheckoutButton);
     return new OrderPage();
    }

}
