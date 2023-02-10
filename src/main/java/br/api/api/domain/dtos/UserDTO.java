package br.api.api.domain.dtos;

import br.api.api.domain.User;

public class UserDTO {
    private Integer id;
    private String name;
    private String email;
    private String cpf;
    private String password;
    public UserDTO() {
    }
    public UserDTO(Integer id, String name, String email, String cpf, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.cpf = cpf;
        this.password = password;
    }

    public UserDTO(User obj) {
        super();
        this.id = obj.getId();
        this.name = obj.getName();
        this.email = obj.getEmail();;
        this.cpf = obj.getCpf();
        this.password = obj.getPassword();
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
