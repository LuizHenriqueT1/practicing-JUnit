package br.api.api.services;

import br.api.api.domain.User;
import br.api.api.domain.dtos.UserDTO;
import br.api.api.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class UserServiceTest {

    public static final Integer ID = 1;
    public static final String NAME = "Maria Eduarda";
    public static final String EMAIL = "maria_duda@gmail.com";
    public static final String CPF = "854.415.940-04";
    public static final String PASSWORD = "12345678";
    @InjectMocks
    private UserService service;
    @Mock
    private UserRepository repository;

    private User user;
    private UserDTO userDTO;
    private Optional<User> optionalUser;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startUser();
    }

    @Test
    void whenFindByIdThenReturnUserInstance() {
        when(repository.findById(Mockito.anyInt())).thenReturn(optionalUser);

        User response = service.findById(ID);

        assertNotNull(response);
        assertEquals(User.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(EMAIL, response.getEmail());
    }

    @Test
    void findAllUser() {
    }

    @Test
    void createUser() {
    }

    private void startUser() {
        user = new User(ID, NAME, EMAIL, CPF, PASSWORD);
        userDTO = new UserDTO(ID, NAME, EMAIL, CPF, PASSWORD);
        optionalUser = Optional.of(new User(ID, NAME, EMAIL, CPF, PASSWORD));
    }
}