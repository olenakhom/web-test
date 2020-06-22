package ui.tests;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Date;

import ui.junit.extensions.custom.CustomTestContext;
import ui.junit.extensions.custom.CustomTestWatcher;
import ui.model.AccountPersonalInfo;
import ui.model.Address;
import ui.model.Gender;
import ui.pages.AccountPage;
import ui.pages.CategoryPage;
import ui.pages.HomePage;
import ui.pages.LoginPage;
import ui.pages.OrderConfirmationPage;
import ui.pages.OrderPage;
import ui.pages.ProductDetailsPage;
import ui.pages.RegistrationPage;

@ExtendWith({CustomTestContext.class, CustomTestWatcher.class})
public class WebTest {

    @Test
    @Tags({@Tag("smoke")})
    public void signInTest() {
        AccountPersonalInfo personalInfo = generatePersonalInfoForNewAccount();

        HomePage homePage = new HomePage();
        homePage.openPage();
        LoginPage loginPage = homePage.clickLoginButton();

        loginPage.fillEmailCreate(generateEmail());
        RegistrationPage registrationPage = loginPage.clickSubmitCreateButton();

        registrationPage.fillForm(personalInfo);
        AccountPage accountPage = registrationPage.clickSubmitButton();

        accountPage.verify(personalInfo);
    }

    @Test
    @Tags({@Tag("smoke")})
    public void logInTest() {
        AccountPersonalInfo personalInfo = generatePersonalInfoForExistingUser();

        HomePage homePage = new HomePage();
        homePage.openPage();
        LoginPage loginPage = homePage.clickLoginButton();

        loginPage.fillFormToLogin(personalInfo.getEmail(), personalInfo.getPassword());
        AccountPage accountPage = loginPage.clickSubmitLoginButton();

        accountPage.verify(personalInfo);
    }

    @Test
    @Tags({@Tag("smoke")})
    public void checkoutTest() {
        AccountPersonalInfo personalInfo = generatePersonalInfoForExistingUser();

        HomePage homePage = new HomePage();
        homePage.openPage();
        LoginPage loginPage = homePage.clickLoginButton();

        loginPage.fillFormToLogin(personalInfo.getEmail(), personalInfo.getPassword());
        AccountPage accountPage = loginPage.clickSubmitLoginButton();

        CategoryPage categoryPage = accountPage.clickWomenLink();
        ProductDetailsPage productPage = categoryPage.clickProductLink();

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

    private AccountPersonalInfo generatePersonalInfoForNewAccount() {
        AccountPersonalInfo personalInfo = new AccountPersonalInfo();
        personalInfo.setGender(Gender.MS);
        personalInfo.setFirstName("Firstname");
        personalInfo.setLastName("Lastname");
        personalInfo.setPassword("Qwerty");
        personalInfo.setDateOfBirth(new Date(2000, 1, 1));
        personalInfo.setCompany("Company");
        personalInfo.setAddress(Address.builder()
            .address1("Qwerty, 123")
            .address2("zxcvb")
            .city("Qwerty")
            .state("Colorado")
            .zipOrPostalCode("12345")
            .addressAlias("gk")
            .build());
        personalInfo.setAdditionalInfo("Qwerty");
        personalInfo.setPhoneNumber("12345123123");
        personalInfo.setMobilePhone("12345123123");
        return personalInfo;
    }

    private String generateEmail() {
        String timestamp = String.valueOf(new Date().getTime());
        String email = "gk_" + timestamp + "@gk" + timestamp.substring(7) + ".com";
        return email;
    }

    private AccountPersonalInfo generatePersonalInfoForExistingUser() {
        AccountPersonalInfo personalInfo = new AccountPersonalInfo();
        personalInfo.setEmail("gk123@gk.com");
        personalInfo.setPassword("123456");
        personalInfo.setFirstName("Ankit");
        personalInfo.setLastName("Nigam");
        return personalInfo;
    }

}
