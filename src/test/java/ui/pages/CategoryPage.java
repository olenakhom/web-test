package ui.pages;

import io.qameta.allure.Step;
import static org.assertj.core.api.Assertions.assertThat;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CategoryPage extends BasePage {

    private By productLink = By.xpath("//a[@title='Faded Short Sleeve T-shirts']");

    @Step("Category Page : Click product link")
    public ProductDetailsPage clickProductLink() {
        getWait().until(ExpectedConditions.elementToBeClickable(productLink)).click();
        return new ProductDetailsPage();
    }

}
