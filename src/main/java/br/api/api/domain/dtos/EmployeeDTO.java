package br.api.api.domain.dtos;

import br.api.api.domain.Employee;
import br.api.api.domain.User;

public class EmployeeDTO extends User {
    private String jobPosition;

    public EmployeeDTO(String jobPosition) {
        this.jobPosition = jobPosition;
    }

    public EmployeeDTO(Integer id, String name, String email, String cpf, String password, String jobPosition) {
        super(id, name, email, cpf, password);
        this.jobPosition = jobPosition;
    }

    public EmployeeDTO(Employee obj) {
        super();
        this.jobPosition = obj.getJobPosition();
    }

    public String getJobPosition() {
        return jobPosition;
    }

    public void setJobPosition(String jobPosition) {
        this.jobPosition = jobPosition;
    }
}
