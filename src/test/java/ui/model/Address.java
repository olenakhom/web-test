package ui.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Address {

    private String address1;
    private String address2;
    private String city;
    private String state;
    private String zipOrPostalCode;
    private String country;
    private String addressAlias;

}
