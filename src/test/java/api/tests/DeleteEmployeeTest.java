package api.tests;

import api.src.junit.CustomApiTestContext;
import api.src.model.DeleteEmployeeResponse;
import api.src.model.EmployeesResponse;
import api.src.steps.EmployeeStep;
import static common.model.TestTag.REGRESSION;
import io.qameta.allure.Issue;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith({CustomApiTestContext.class})
final class DeleteEmployeeTest {

    @Test
    @Issue("TFG-1")
    @Tags({@Tag(REGRESSION), @Tag("employee")})
    void deleteEmployeeHasValidResponseTest() {
        EmployeeStep step = new EmployeeStep();

        DeleteEmployeeResponse response = step.deleteExistingEmployee();

        step.verifyStatus(response.getStatus());
        step.verifyDeleteSuccessMessage(response.getMessage());
    }

}
