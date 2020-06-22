package ui.pages;

import io.qameta.allure.Step;
import static org.assertj.core.api.Assertions.assertThat;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ui.model.AccountPersonalInfo;

public class ProductDetailsPage extends BasePage {

    private By submitButton = By.name("Submit");
    private By proceedToCheckoutButton = By.xpath("//*[@id='layer_cart']//a[@class and @title='Proceed to checkout']");
    private By iframe = By.className("fancybox-iframe");

    @Step("Product Details Page : Switch to popup")
    public void switchToPopup() {
        getWait().until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(iframe));
    }

    @Step("Product Details Page : Click on submit button")
    public void clickSubmitButton() {
        getWait().until(ExpectedConditions.elementToBeClickable(submitButton)).click();
    }

    @Step("Product Details Page : Click proceed to checkout button")
    public OrderPage clickProceedToCheckoutButton() {
     getWait().until(ExpectedConditions.visibilityOfElementLocated(proceedToCheckoutButton)).click();
     return new OrderPage();
    }

}
