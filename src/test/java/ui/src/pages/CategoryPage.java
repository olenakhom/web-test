package ui.src.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class CategoryPage extends BasePage {

    @Step("Category Page : Click product link")
    public ProductDetailsPage clickProductLink(String title) {
        waitForVisibilityAndClickWithJs(getProductLink(title));
        return new ProductDetailsPage();
    }

    private By getProductLink(String title) {
        return By.xpath("//a[@title='" + title + "']");
    }

}
