package ui.src.model;

import java.util.Date;
import lombok.Data;

@Data
public class AccountPersonalInfo {

    private Gender gender;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Date dateOfBirth;
    private String company;
    private Address address;
    private String phoneNumber;
    private String mobilePhone;
    private String additionalInfo;

}
