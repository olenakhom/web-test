package api.src.steps;

import api.src.model.DeleteEmployeeResponse;
import static org.assertj.core.api.Assertions.assertThat;

import api.src.model.Employee;
import api.src.model.EmployeeResponse;
import api.src.model.EmployeesResponse;
import api.src.restclient.EmployeeRestClient;
import io.qameta.allure.Step;

import java.util.List;

public class EmployeeStep {

    private EmployeeRestClient employeeRestClient = new EmployeeRestClient();

    @Step("Get employee By ID {id}")
    public EmployeeResponse getEmployee(Long id) {
        return employeeRestClient.getEmployee(id);
    }

    @Step("Get employees")
    public EmployeesResponse getEmployees() {
        return employeeRestClient.getEmployees();
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

    @Step("Delete employee by ID {}")
    public DeleteEmployeeResponse deleteEmployee(Long id) {
        return employeeRestClient.deleteEmployee(id);
    }

    @Step("Delete existing employee")
    public DeleteEmployeeResponse deleteExistingEmployee() {
        Long id = getEmployees().getData().stream().findAny().get().getId();
        return employeeRestClient.deleteEmployee(id);
    }

    @Step("Update employee {employee} by Id {id}")
    public EmployeeResponse updateEmployee(Employee employee, Long id) {
        return employeeRestClient.updateEmployee(employee, id);
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
        verifyEmployeeId(actualData.getId(), expectedData.getId());
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

    @Step("Verify delete employee response")
    public void verifyDeleteSuccessMessage(String message) {
        assertThat(message).as("Message")
            .isEqualTo("successfully! deleted Records");
    }

    private void verifyEmployeeId(Long actualId, Long expectedId) {
        if (expectedId != null) {
            assertThat(actualId).as("Id")
                .isEqualTo(expectedId);
            return;
        }
        assertThat(actualId).as("Id shouldn't be null")
            .isNotNull();
    }

}
