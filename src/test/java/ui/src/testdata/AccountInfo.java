package ui.src.testdata;

import java.util.Date;
import ui.src.model.AccountPersonalInfo;
import ui.src.model.Address;
import ui.src.model.Gender;
import common.utils.RandomUtil;

public class AccountInfo {

    private AccountInfo() {}

    public static AccountPersonalInfo generateForNewAccount() {
        AccountPersonalInfo personalInfo = new AccountPersonalInfo();
        personalInfo.setGender(Gender.MS);
        personalInfo.setFirstName(RandomUtil.generateString());
        personalInfo.setLastName(RandomUtil.generateString());
        personalInfo.setPassword(RandomUtil.generateString());
        personalInfo.setDateOfBirth(new Date(2000, 1, 1));
        personalInfo.setCompany(RandomUtil.generateString());
        personalInfo.setAddress(Address.builder()
            .address1(RandomUtil.generateString())
            .address2(RandomUtil.generateString())
            .city(RandomUtil.generateString())
            .state("Colorado")
            .zipOrPostalCode(String.valueOf(RandomUtil.generateNumberInRange(1, 99999)))
            .addressAlias(RandomUtil.generateString(5))
            .build());
        personalInfo.setAdditionalInfo(RandomUtil.generateString());
        personalInfo.setPhoneNumber(String.valueOf(RandomUtil.generateNumberInRange(1, 999999)));
        personalInfo.setMobilePhone(String.valueOf(RandomUtil.generateNumberInRange(1, 999999)));
        return personalInfo;
    }

    public static String generateNewEmail() {
        return RandomUtil.generateEmail();
    }

    public static AccountPersonalInfo generateForExistingUser() {
        AccountPersonalInfo personalInfo = new AccountPersonalInfo();
        personalInfo.setEmail("gk123@gk.com");
        personalInfo.setPassword("123456");
        personalInfo.setFirstName("Ankit");
        personalInfo.setLastName("Nigam");
        return personalInfo;
    }

}
