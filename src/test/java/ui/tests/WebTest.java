package ui.tests;

import java.util.List;
import ui.model.ProductDetails;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import ui.junit.extensions.custom.CustomTestContext;
import ui.junit.extensions.custom.CustomTestWatcher;
import ui.model.AccountPersonalInfo;
import ui.pages.AccountPage;
import ui.pages.CategoryPage;
import ui.pages.HomePage;
import ui.pages.LoginPage;
import ui.pages.OrderConfirmationPage;
import ui.pages.OrderPage;
import ui.pages.ProductDetailsPage;
import ui.pages.RegistrationPage;
import ui.testdata.AccountInfo;
import common.utils.JsonUtil;

@ExtendWith({CustomTestContext.class, CustomTestWatcher.class})
public class WebTest {

    @Test
    @Tags({@Tag("regression")})
    public void signInTest() {
        AccountPersonalInfo personalInfo = AccountInfo.generateForNewAccount();

        HomePage homePage = new HomePage();
        homePage.openPage();
        LoginPage loginPage = homePage.clickLoginButton();

        loginPage.fillEmailCreate(AccountInfo.generateNewEmail());
        RegistrationPage registrationPage = loginPage.clickSubmitCreateButton();

        registrationPage.fillForm(personalInfo);
        AccountPage accountPage = registrationPage.clickSubmitButton();

        accountPage.verify(personalInfo);
    }

    @Test
    @Tags({@Tag("regression")})
    public void logInTest() {
        AccountPersonalInfo personalInfo = AccountInfo.generateForExistingUser();

        HomePage homePage = new HomePage();
        homePage.openPage();
        LoginPage loginPage = homePage.clickLoginButton();

        loginPage.fillFormToLogin(personalInfo.getEmail(), personalInfo.getPassword());
        AccountPage accountPage = loginPage.clickSubmitLoginButton();

        accountPage.verify(personalInfo);
    }

    @Test
    @Tags({@Tag("regression")})
    public void checkoutTest() {
        AccountPersonalInfo personalInfo = AccountInfo.generateForExistingUser();
        List<ProductDetails> productDetails = JsonUtil.readFromProductDetailsJson();

        HomePage homePage = new HomePage();
        homePage.openPage();
        LoginPage loginPage = homePage.clickLoginButton();

        loginPage.fillFormToLogin(personalInfo.getEmail(), personalInfo.getPassword());
        AccountPage accountPage = loginPage.clickSubmitLoginButton();

        CategoryPage categoryPage = accountPage.clickWomenLink();
        ProductDetailsPage productPage = categoryPage.clickProductLink(productDetails.get(0).getTitle());

        productPage.switchToPopup();
        productPage.clickSubmitButton();
        OrderPage orderPage = productPage.clickProceedToCheckoutButton();

        orderPage.clickProceedToCheckoutButton();
        orderPage.clickProcessAddressButton();
        orderPage.clickUniformButton();
        orderPage.clickProcessCarrierButton();
        orderPage.clickBankWireButton();
        OrderConfirmationPage orderConfirmationPage = orderPage.clickSubmitOrderButton();

        orderConfirmationPage.verify();
    }

}
