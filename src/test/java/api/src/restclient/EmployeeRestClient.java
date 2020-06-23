package api.src.restclient;

import api.src.model.DeleteEmployeeResponse;
import static io.restassured.RestAssured.given;

import api.src.model.Employee;
import api.src.model.EmployeeResponse;
import api.src.model.EmployeesResponse;
import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class EmployeeRestClient {

    private static final Logger LOGGER = LogManager.getLogger(EmployeeRestClient.class);

    public EmployeeRestClient() {
        LOGGER.info("Configure rest assured...");
        RestAssured.reset();
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.filters(new AllureRestAssured());
        RestAssured.baseURI = System.getProperty("api.base.url");
        RestAssured.basePath = "/api/v1";
    }

    @Step("EmployeeRestClient : Get employees")
    public EmployeesResponse getEmployees() {
        return given()
            .log().all()
            .when().get("/employees")
            .then()
            .log().all()
            .statusCode(HttpStatus.SC_OK)
            .contentType(ContentType.JSON)
            .extract().response().as(EmployeesResponse.class);
    }

    @Step("EmployeeRestClient : Create employee {employee}")
    public EmployeeResponse createEmployee(Employee employee) {
        return given()
            .log().all()
            .contentType(ContentType.JSON)
            .body(employee)
            .when().post("/create")
            .then()
            .log().all()
            .statusCode(HttpStatus.SC_OK)
            .contentType(ContentType.JSON)
            .extract().response().as(EmployeeResponse.class);
    }

    @Step("EmployeeRestClient : Update employee {employee} by id {id}")
    public EmployeeResponse updateEmployee(Employee employee, Long id) {
        return given()
            .log().all()
            .contentType(ContentType.JSON)
            .pathParam("id", id)
            .body(employee)
            .when().put("/update/{id}")
            .then()
            .log().all()
            .statusCode(HttpStatus.SC_OK)
            .contentType(ContentType.JSON)
            .extract().response().as(EmployeeResponse.class);
    }

    @Step("EmployeeRestClient : Get employee by id {id}")
    public EmployeeResponse getEmployee(Long id) {
        return given()
            .log().all()
            .pathParam("id", id)
            .when().get("/employee/{id}")
            .then()
            .log().all()
            .statusCode(HttpStatus.SC_OK)
            .contentType(ContentType.JSON)
            .extract().response().as(EmployeeResponse.class);
    }

    @Step("EmployeeRestClient : Delete employee by id {id}")
    public DeleteEmployeeResponse deleteEmployee(Long id) {
        return given()
            .log().all()
            .pathParam("id", id)
            .when().delete("/delete/{id}")
            .then()
            .log().all()
            .statusCode(HttpStatus.SC_OK)
            .contentType(ContentType.JSON)
            .extract().response().as(DeleteEmployeeResponse.class);
    }

}
