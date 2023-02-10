package br.api.api.services;

import br.api.api.domain.User;
import br.api.api.domain.dtos.UserDTO;
import br.api.api.repositories.UserRepository;
import br.api.api.services.exception.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
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
        when(repository.findById(anyInt())).thenReturn(optionalUser);

        User response = service.findById(ID);

        assertNotNull(response);
        assertEquals(User.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(EMAIL, response.getEmail());
    }
    @Test
    void whenFindByIdThenReturnAndObjectNotFoundException() {
        when(repository.findById(anyInt())).thenThrow(new ObjectNotFoundException("Object not found: " + ID));

        try {
            service.findById(ID);
        } catch (Exception ex) {
            assertEquals(ObjectNotFoundException.class, ex.getClass());
            assertEquals("Object not found: " + ID, ex.getMessage());
        }
    }

    @Test
    void findAllUser() {
    }

    @Test
    void whenCreateThenReturnSuccess() {
        //Mockar a resposta do repository
        when(repository.save(any())).thenReturn(user);

        User response = service.createUser(userDTO);

        assertNotNull(response);
        assertEquals(User.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(CPF, response.getCpf());
        assertEquals(EMAIL, response.getEmail());
        assertEquals(PASSWORD, response.getPassword());
    }

    private void startUser() {
        user = new User(ID, NAME, EMAIL, CPF, PASSWORD);
        userDTO = new UserDTO(ID, NAME, EMAIL, CPF, PASSWORD);
        optionalUser = Optional.of(new User(ID, NAME, EMAIL, CPF, PASSWORD));
    }
}