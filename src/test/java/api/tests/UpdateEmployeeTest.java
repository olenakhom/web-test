package api.tests;

import static common.model.TestTag.REGRESSION;

import api.src.junit.CustomApiTestContext;
import api.src.model.Employee;
import api.src.model.EmployeeResponse;
import api.src.steps.EmployeeStep;
import common.utils.JsonUtil;
import io.qameta.allure.Issue;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith({CustomApiTestContext.class})
final class UpdateEmployeeTest {

    @Test
    @Issue("TFG-3")
    @Tags({@Tag(REGRESSION), @Tag("employee")})
    void updateEmployeeHasValidResponseTest() {
        Employee employeeToCreate = JsonUtil.readFromEmployeeJson("createemployee.json");
        Employee expectedEmployee = JsonUtil.readFromEmployeeJson("updateemployee.json");
        EmployeeStep step = new EmployeeStep();

        Long id = step.createEmployeeAndGetId(employeeToCreate);
        expectedEmployee.setId(id);
        EmployeeResponse actualResponse = step.updateEmployee(expectedEmployee, id);

        step.verifyStatus(actualResponse.getStatus());
        step.verifyEmployeeData(actualResponse.getData(), expectedEmployee);
    }

}
