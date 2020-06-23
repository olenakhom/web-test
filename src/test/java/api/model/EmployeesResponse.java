package api.model;

import java.util.List;
import lombok.Data;

@Data
public class EmployeesResponse {

    private String status;
    private List<Employee> data;

}
