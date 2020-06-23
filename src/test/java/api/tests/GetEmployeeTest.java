package api.tests;

import api.src.junit.CustomApiTestContext;
import api.src.model.Employee;
import api.src.model.EmployeeResponse;
import api.src.steps.EmployeeStep;
import static common.model.TestTag.REGRESSION;
import common.utils.JsonUtil;
import io.qameta.allure.Issue;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith({CustomApiTestContext.class})
final class GetEmployeeTest {

    @Test
    @Issue("TFG-2")
    @Tags({@Tag(REGRESSION), @Tag("employee")})
    void getEmployeeHasValidResponseTest() {
        Employee expectedEmployee = JsonUtil.readFromEmployeeJson("createemployee.json");
        EmployeeStep step = new EmployeeStep();

        Long id = step.createEmployeeAndGetId(expectedEmployee);
        expectedEmployee.setId(id);

        EmployeeResponse actualResponse = step.getEmployee(id);

        step.verifyStatus(actualResponse.getStatus());
        step.verifyEmployeeData(actualResponse.getData(), expectedEmployee);
    }

}
