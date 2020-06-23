package ui.tests;

import static common.model.TestTag.REGRESSION;

import ui.src.model.ProductDetails;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import ui.src.junit.extensions.custom.CustomTestContext;
import ui.src.junit.extensions.custom.CustomTestWatcher;
import ui.src.model.AccountPersonalInfo;
import ui.src.pages.AccountPage;
import ui.src.pages.CategoryPage;
import ui.src.pages.HomePage;
import ui.src.pages.LoginPage;
import ui.src.pages.OrderConfirmationPage;
import ui.src.pages.OrderPage;
import ui.src.pages.ProductDetailsPage;
import ui.src.pages.RegistrationPage;
import ui.src.testdata.AccountInfo;
import common.utils.JsonUtil;

import java.util.List;

@ExtendWith({CustomTestContext.class, CustomTestWatcher.class})
public class WebTest {

    @Test
    @Tags({@Tag(REGRESSION)})
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
    @Tags({@Tag(REGRESSION)})
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
    @Tags({@Tag(REGRESSION)})
    public void checkoutTest() {
        AccountPersonalInfo personalInfo = AccountInfo.generateForExistingUser();
        List<ProductDetails> productDetails = JsonUtil.readFromProductDetailsJson("productdetails.json");

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
