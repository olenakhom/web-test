package ui.pages;

import io.qameta.allure.Step;
import static org.assertj.core.api.Assertions.assertThat;
import org.openqa.selenium.By;

public class CategoryPage extends BasePage {

    private By productLink = By.xpath("//a[@title='Faded Short Sleeve T-shirts']");

    @Step("Category Page : Click product link")
    public ProductDetailsPage clickProductLink(String title) {
        waitForClickableAndClick(getProductLink(title));
        return new ProductDetailsPage();
    }

    private By getProductLink(String title) {
        return By.xpath("//a[@title='" + title + "']");
    }

}
