package api.src.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Employee {

    private Long id;

    @JsonAlias("employee_name")
    @JsonProperty("name")
    private String name;

    @JsonAlias("employee_salary")
    @JsonProperty("salary")
    private Long salary;

    @JsonAlias("employee_age")
    @JsonProperty("age")
    private int age;

    @JsonProperty("profile_image")
    private String profileImage;

}
