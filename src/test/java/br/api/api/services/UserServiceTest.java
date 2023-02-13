package br.api.api.services;

import br.api.api.domain.User;
import br.api.api.domain.dtos.UserDTO;
import br.api.api.repositories.UserRepository;
import br.api.api.services.exception.DataIntegratyViolationException;
import br.api.api.services.exception.ObjectNotFoundException;
import org.jboss.jandex.Index;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import java.util.List;
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
    public static final String MessageObjectNotFoundExceptionWithID = "Object not found: " + ID;
    public static final String EMAIL_ALREADY_REGISTERED = "Email already registered";
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
        when(repository.findById(anyInt())).thenThrow(new ObjectNotFoundException(MessageObjectNotFoundExceptionWithID));

        try {
            service.findById(ID);
        } catch (Exception ex) {
            assertEquals(ObjectNotFoundException.class, ex.getClass());
            assertEquals(MessageObjectNotFoundExceptionWithID, ex.getMessage());
        }
    }

    @Test
    void whenFindAllThenReturnAListOfUser() {
        when(repository.findAll()).thenReturn(List.of(user));

        List<User> response = service.findAllUser();

        assertNotNull(response);
        assertEquals(User.class, response.get(0).getClass());
    }

    @Test
    void whenCreateThenReturnSuccess() {
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

    @Test
    void whenCreateThenReturnAnDataIntegrationViolationException() {
        when(repository.findByEmail(anyString())).thenReturn(optionalUser);

        try {
            optionalUser.get().setId(2);
            service.createUser(userDTO);
        } catch (Exception ex) {
            assertEquals(DataIntegratyViolationException.class, ex.getClass());
            assertEquals(EMAIL_ALREADY_REGISTERED, ex.getMessage());
        }
    }

    @Test
    void whenUpdateThenReturnSuccess() {
        when(repository.save(any())).thenReturn(user);

        User response = service.updateUser(ID, userDTO);

        assertNotNull(response);
        assertEquals(User.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(CPF, response.getCpf());
        assertEquals(EMAIL, response.getEmail());
        assertEquals(PASSWORD, response.getPassword());
    }

    @Test
    void whenUpdateThenReturnAnDataIntegrationViolationException() {
        when(repository.findByEmail(anyString())).thenReturn(optionalUser);

        try {
            optionalUser.get().setId(2);
            service.updateUser(ID, userDTO);
        } catch (Exception ex) {
            assertEquals(DataIntegratyViolationException.class, ex.getClass());
            assertEquals(EMAIL_ALREADY_REGISTERED, ex.getMessage());
        }
    }

    @Test
    void whenDeleteThenReturnSuccess() {
        when(repository.findById(anyInt())).thenReturn(optionalUser);
        doNothing().when(repository).deleteById(anyInt());
        service.deleteUser(ID);
        verify(repository, times(1)).deleteById(ID);
    }

    @Test
    void deleteWithObjectNotFoundException() {
        when(repository.findById(anyInt()))
                .thenThrow(new ObjectNotFoundException(MessageObjectNotFoundExceptionWithID));

        try {
            service.deleteUser(ID);
        }  catch (Exception ex) {
            assertEquals(ObjectNotFoundException.class, ex.getClass());
            assertEquals(MessageObjectNotFoundExceptionWithID, ex.getMessage());
        }
    }

    private void startUser() {
        user = new User(ID, NAME, EMAIL, CPF, PASSWORD);
        userDTO = new UserDTO(ID, NAME, EMAIL, CPF, PASSWORD);
        optionalUser = Optional.of(new User(ID, NAME, EMAIL, CPF, PASSWORD));
    }
}