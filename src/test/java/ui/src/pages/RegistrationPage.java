package ui.src.pages;

import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import ui.src.model.AccountPersonalInfo;
import ui.src.model.Address;
import ui.src.model.Gender;

public class RegistrationPage extends BasePage {

    private static final Logger LOGGER = LogManager.getLogger(RegistrationPage.class);

    private By genderMsRadioButton = By.id("id_gender2");
    private By firstNameInput = By.id("customer_firstname");
    private By lastNameInput = By.id("customer_lastname");
    private By passwordInput = By.id("passwd");
    private By dayOfBirthSelect = By.id("days");
    private By monthOfBirthSelect = By.id("months");
    private By yearOfBirthSelect = By.id("years");
    private By companyInput = By.id("company");
    private By address1Input = By.id("address1");
    private By address2Input = By.id("address2");
    private By cityInput = By.id("city");
    private By stateSelect = By.id("id_state");
    private By postcodeInput = By.id("postcode");
    private By additionalInfoInput = By.id("other");
    private By phoneInput = By.id("phone");
    private By mobilePhoneInput = By.id("phone_mobile");
    private By addressAliasInput = By.id("alias");
    private By submitFormButton = By.id("submitAccount");

    @Step("Registration Page : Fill form")
    public void fillForm(AccountPersonalInfo personalInfo) {
        selectGender(personalInfo.getGender());
        waitForClickableAndFill(firstNameInput, personalInfo.getFirstName());
        waitForClickableAndFill(lastNameInput, personalInfo.getLastName());
        waitForClickableAndFill(passwordInput, personalInfo.getPassword());
        selectByValue(dayOfBirthSelect, String.valueOf(personalInfo.getDateOfBirth().getDay()));
        selectByValue(monthOfBirthSelect, String.valueOf(personalInfo.getDateOfBirth().getMonth()));
        selectByValue(yearOfBirthSelect, String.valueOf(personalInfo.getDateOfBirth().getYear()));
        waitForClickableAndFill(companyInput, personalInfo.getCompany());
        Address address = personalInfo.getAddress();
        waitForClickableAndFill(address1Input, address.getAddress1());
        waitForClickableAndFill(address2Input, address.getAddress2());
        waitForClickableAndFill(cityInput, address.getCity());
        selectByText(stateSelect, address.getState());
        waitForClickableAndFill(postcodeInput, address.getZipOrPostalCode());
        waitForClickableAndFill(additionalInfoInput, personalInfo.getAdditionalInfo());
        waitForClickableAndFill(phoneInput, personalInfo.getPhoneNumber());
        waitForClickableAndFill(mobilePhoneInput, personalInfo.getPhoneNumber());
        waitForClickableAndFill(addressAliasInput, address.getAddressAlias());
    }

    @Step("Registration Page : Click on submit button")
    public AccountPage clickSubmitButton(){
        waitForClickableAndClick(submitFormButton);
        return new AccountPage();
    }

    @Step("Registration Page : Select gender {gender}")
    public void selectGender(Gender gender) {
        if (gender.equals(Gender.MS)) {
            waitForClickableAndClick(genderMsRadioButton);
        }
    }

}
