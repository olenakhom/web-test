package api.tests;

import static common.model.TestTag.REGRESSION;

import api.src.steps.EmployeeStep;
import api.src.junit.CustomApiTestContext;
import api.src.model.EmployeesResponse;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith({CustomApiTestContext.class})
final class GetEmployeesTest {

    @Test
    @Tags({@Tag(REGRESSION), @Tag("employee")})
    void getEmployeesHasValidSchemaAndStatusTest() {
        EmployeeStep step = new EmployeeStep();

        EmployeesResponse response = step.getEmployees();

        step.verifyStatus(response.getStatus());
        step.verifyEmployeesData(response.getData());
    }

}
