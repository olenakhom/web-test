package ui.pages;

import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import ui.model.AccountPersonalInfo;
import ui.model.Address;
import ui.model.Gender;

public class RegistrationPage extends BasePage{

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
        getDriver().findElement(firstNameInput).sendKeys(personalInfo.getFirstName());
        getDriver().findElement(lastNameInput).sendKeys(personalInfo.getLastName());
        getDriver().findElement(passwordInput).sendKeys(personalInfo.getPassword());
        Select select = new Select(getDriver().findElement(dayOfBirthSelect));
        select.selectByValue(String.valueOf(personalInfo.getDateOfBirth().getDay()));
        select = new Select(getDriver().findElement(monthOfBirthSelect));
        select.selectByValue(String.valueOf(personalInfo.getDateOfBirth().getMonth()));
        select = new Select(getDriver().findElement(yearOfBirthSelect));
        select.selectByValue(String.valueOf(personalInfo.getDateOfBirth().getYear()));
        getDriver().findElement(companyInput).sendKeys(personalInfo.getCompany());
        Address address = personalInfo.getAddress();
        getDriver().findElement(address1Input).sendKeys(address.getAddress1());
        getDriver().findElement(address2Input).sendKeys(address.getAddress2());
        getDriver().findElement(cityInput).sendKeys(address.getCity());
        select = new Select(getDriver().findElement(stateSelect));
        select.selectByVisibleText(address.getState());
        getDriver().findElement(postcodeInput).sendKeys(address.getZipOrPostalCode());
        getDriver().findElement(additionalInfoInput).sendKeys(personalInfo.getAdditionalInfo());
        getDriver().findElement(phoneInput).sendKeys(personalInfo.getPhoneNumber());
        getDriver().findElement(mobilePhoneInput).sendKeys(personalInfo.getPhoneNumber());
        getDriver().findElement(addressAliasInput).sendKeys(address.getAddressAlias());

    }

    @Step("Registration Page : Click on submit button")
    public AccountPage clickSubmitButton(){
        getDriver().findElement(submitFormButton).click();
        return new AccountPage();
    }

    @Step("Registration Page : Select gender {gender}")
    public void selectGender(Gender gender) {
        if (gender.equals(Gender.MS)) {
            getWait().until(ExpectedConditions.visibilityOfElementLocated(genderMsRadioButton)).click();
        }
    }

}
