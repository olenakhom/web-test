package api.tests;

import api.src.junit.CustomApiTestContext;
import api.src.model.Employee;
import api.src.model.EmployeeResponse;
import api.src.steps.EmployeeStep;
import static common.model.TestTag.REGRESSION;
import common.utils.JsonUtil;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith({CustomApiTestContext.class})
final class CreateEmployeeTest {

    @Test
    @Tags({@Tag(REGRESSION), @Tag("employee")})
    void createEmployeeHasValidResponseTest() {
        Employee expectedEmployee = JsonUtil.readFromEmployeeJson("createemployee.json");

        EmployeeStep step = new EmployeeStep();
        EmployeeResponse actualResponse = step.createEmployee(expectedEmployee);

        step.verifyStatus(actualResponse.getStatus());
        step.verifyEmployeeData(actualResponse.getData(), expectedEmployee);
    }

}
