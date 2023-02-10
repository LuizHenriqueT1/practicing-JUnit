package br.api.api.domain;

import br.api.api.domain.dtos.EmployeeDTO;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;

@Entity
public class Employee extends User {

    private static final long serialVersionUID = 1L;
    @NotNull(message = "Object cannot be null")
    private String jobPosition;

    public Employee() {
    }

    public Employee(String jobPosition) {
        this.jobPosition = jobPosition;
    }

    public Employee(Integer id, String name, String email, String cpf, String password, String jobPosition) {
        super(id, name, email, cpf, password);
        this.jobPosition = jobPosition;
    }

    public Employee(EmployeeDTO objDto) {
        super();
        this.jobPosition = objDto.getJobPosition();
    }

    public String getJobPosition() {
        return jobPosition;
    }

    public void setJobPosition(String jobPosition) {
        this.jobPosition = jobPosition;
    }
}
