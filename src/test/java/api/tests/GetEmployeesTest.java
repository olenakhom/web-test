package api.tests;

import static common.model.TestTag.REGRESSION;

import api.junit.CustomApiTestContext;
import api.model.EmployeesResponse;
import api.restclient.EmployeeRestClient;
import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith({CustomApiTestContext.class})
final class GetEmployeesTest {

    private EmployeeRestClient employeeRestClient = new EmployeeRestClient();

    @Test
    @Tags({@Tag(REGRESSION), @Tag("employee")})
    void getEmployeesHasValidSchemaAndStatusTest() {
        EmployeesResponse employeesResponse = employeeRestClient.getEmployees();

        assertThat(employeesResponse.getStatus()).as("Status")
            .isEqualTo("success");
        assertThat(employeesResponse.getData()).as("List of employees is not null")
            .isNotNull();
        assertThat(employeesResponse.getData().size()).as("List of employees is not empty")
            .isGreaterThan(0);
    }

}
