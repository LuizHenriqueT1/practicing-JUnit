package br.api.api.resources;

import br.api.api.domain.User;
import br.api.api.domain.dtos.UserDTO;
import br.api.api.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserResourceTest {

    @InjectMocks
    private UserResource resource;

    @Mock
    private UserService service;

    public static final Integer ID = 1;
    public static final String NAME = "Maria Eduarda";
    public static final String EMAIL = "maria_duda@gmail.com";
    public static final String CPF = "854.415.940-04";
    public static final String PASSWORD = "12345678";

    private User  user;
    private UserDTO userDTO;

    @BeforeEach
    void setUp() {
        //Iniciar os mock dessa classe
        MockitoAnnotations.openMocks(this);
        startUser();
    }

    @Test
    void findById() {
    }

    @Test
    void findAllUser() {
    }

    @Test
    void createUser() {
    }

    @Test
    void updateUser() {
    }

    @Test
    void deleteUser() {
    }

    private void startUser() {
        user = new User(ID, NAME, EMAIL, CPF, PASSWORD);
        userDTO = new UserDTO(ID, NAME, EMAIL, CPF, PASSWORD);
    }
}