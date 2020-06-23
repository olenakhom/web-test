package api.tests;

import api.junit.CustomApiTestContext;
import api.model.Employee;
import api.model.EmployeeResponse;
import api.restclient.EmployeeRestClient;
import static common.model.TestTag.REGRESSION;
import common.utils.JsonUtil;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith({CustomApiTestContext.class})
final class GetEmployeeTest {

    private EmployeeRestClient employeeRestClient = new EmployeeRestClient();

    @Test
    @Tags({@Tag(REGRESSION), @Tag("employee")})
    void getEmployeeHasValidResponseTest() {
        Employee expectedEmployee = JsonUtil.readFromEmployeeJson("createemployee.json");
        EmployeeResponse createResponse = employeeRestClient.createEmployee(expectedEmployee);
        Long id = createResponse.getData().getId();

        EmployeeResponse actualResponse = employeeRestClient.getEmployee(id);

        assertThat(actualResponse.getStatus()).as("Status")
            .isEqualTo("success");
        assertThat(actualResponse.getData()).as("Employee shouldn't be null")
            .isNotNull();
        Employee actualEmployee = actualResponse.getData();
        assertThat(actualEmployee.getId()).as("Id")
            .isEqualTo(id);
        assertThat(actualEmployee.getName()).as("Name")
            .isEqualTo(expectedEmployee.getName());
        assertThat(actualEmployee.getAge()).as("Age")
            .isEqualTo(expectedEmployee.getAge());
        assertThat(actualEmployee.getSalary()).as("Salary")
            .isEqualTo(expectedEmployee.getSalary());
    }

}
