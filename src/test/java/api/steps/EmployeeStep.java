package api.steps;

import api.model.Employee;
import api.model.EmployeeResponse;
import api.model.EmployeesResponse;
import api.restclient.EmployeeRestClient;
import io.qameta.allure.Step;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

public class EmployeeStep {

    private EmployeeRestClient employeeRestClient = new EmployeeRestClient();

    @Step("Get employee By ID {id}")
    public EmployeeResponse getEmployee(Long id) {
        return employeeRestClient.getEmployee(id);
    }

    @Step("Create employee {employee}")
    public EmployeeResponse createEmployee(Employee employee) {
        return employeeRestClient.createEmployee(employee);
    }

    @Step("Create employee {employee} and get Id")
    public Long createEmployeeAndGetId(Employee employee) {
        EmployeeResponse employeeResponse = employeeRestClient.createEmployee(employee);
        return employeeResponse.getData().getId();
    }

    @Step("Verify status {status} is success")
    public void verifyStatus(String status) {
        assertThat(status).as("Status")
            .isEqualTo("success");
    }

    @Step("Verify employee data")
    public void verifyEmployeeData(Employee actualData, Employee expectedData) {
        if (expectedData != null) {
            assertThat(actualData).as("Employee shouldn't be null")
                .isNotNull();
        }
        assertThat(actualData.getId()).as("Id")
            .isEqualTo(expectedData.getId());
        assertThat(actualData.getName()).as("Name")
            .isEqualTo(expectedData.getName());
        assertThat(actualData.getAge()).as("Age")
            .isEqualTo(expectedData.getAge());
        assertThat(actualData.getSalary()).as("Salary")
            .isEqualTo(expectedData.getSalary());
    }

    @Step("Verify employees data")
    public void verifyEmployeesData(List<Employee> employees) {
        assertThat(employees).as("List of employees is not null")
            .isNotNull();
        assertThat(employees.size()).as("List of employees is not empty")
            .isGreaterThan(0);
    }

}
