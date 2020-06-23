package api.tests;

import api.junit.CustomApiTestContext;
import api.model.Employee;
import api.model.EmployeeResponse;
import api.restclient.EmployeeRestClient;
import static common.model.TestTag.REGRESSION;
import common.utils.JsonUtil;
import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith({CustomApiTestContext.class})
final class CreateEmployeeTest {

    private EmployeeRestClient employeeRestClient = new EmployeeRestClient();

    @Test
    @Tags({@Tag(REGRESSION), @Tag("employee")})
    void createEmployeeHasValidResponseTest() {
        Employee expectedEmployee = JsonUtil.readFromEmployeeJson("createemployee.json");
        EmployeeResponse createResponse = employeeRestClient.createEmployee(expectedEmployee);

        assertThat(createResponse.getStatus()).as("Status")
            .isEqualTo("success");
        assertThat(createResponse.getData()).as("Employee is not null")
            .isNotNull();
        Employee actualEmployee = createResponse.getData();
        assertThat(actualEmployee.getId()).as("Id is not null")
            .isNotNull();
        assertThat(actualEmployee.getName()).as("Name")
            .isEqualTo(expectedEmployee.getName());
        assertThat(actualEmployee.getAge()).as("Age")
            .isEqualTo(expectedEmployee.getAge());
        assertThat(actualEmployee.getSalary()).as("Salary")
            .isEqualTo(expectedEmployee.getSalary());
    }

}
