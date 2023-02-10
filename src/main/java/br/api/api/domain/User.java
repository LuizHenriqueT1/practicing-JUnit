package br.api.api.domain;

import br.api.api.domain.dtos.UserDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
@Entity
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull(message = "Object cannot be null")
    private String name;
    @Column(unique = true)
    @NotNull(message = "Object cannot be null")
    private String email;
    @Column(unique = true)
    @NotNull(message = "Object cannot be null")
    private String cpf;
    @NotNull(message = "Object cannot be null")
    private String password;

    public User() {
    }

    public User(Integer id, String name, String email, String cpf, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.cpf = cpf;
        this.password = password;
    }

    public User(UserDTO objDto) {
        super();
        this.id = objDto.getId();
        this.name = objDto.getName();
        this.email = objDto.getEmail();;
        this.cpf = objDto.getCpf();
        this.password = objDto.getPassword();
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
