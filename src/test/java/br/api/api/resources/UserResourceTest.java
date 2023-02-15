package br.api.api.resources;

import br.api.api.domain.User;
import br.api.api.domain.dtos.UserDTO;
import br.api.api.services.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.engine.TestExecutionResult;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static javax.security.auth.callback.ConfirmationCallback.OK;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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
        MockitoAnnotations.openMocks(this);
        startUser();
    }

    @Test
    void whenFindByIdThenReturnSuccess() {
        when(service.findById(anyInt())).thenReturn(user);

        ResponseEntity<UserDTO> response = resource.findById(ID);
        UserDTO userDto = response.getBody();

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(UserDTO.class, response.getBody().getClass());

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(userDto.getId()).isEqualTo(ID);
        assertThat(userDto.getName()).isEqualTo(NAME);
        assertThat(userDto.getEmail()).isEqualTo(EMAIL);
        assertThat(userDto.getCpf()).isEqualTo(CPF);
    }

    @Test
    void whenFindAllThenReturnAListOfUserDTO() {
        when(service.findAllUser()).thenReturn(List.of(user));

        ResponseEntity<List<UserDTO>> response = resource.findAllUser();

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(ArrayList.class, response.getBody().getClass());
        assertEquals(UserDTO.class, response.getBody().get(0).getClass());
        assertEquals(HttpStatus.OK, response.getStatusCode());

        assertEquals(ID, response.getBody().get(0).getId());
        assertEquals(NAME, response.getBody().get(0).getName());
        assertEquals(EMAIL, response.getBody().get(0).getEmail());
        assertEquals(CPF, response.getBody().get(0).getCpf());
    }

    @Test
    void whenCreatedThenReturnSuccess() {
        when(service.createUser(any())).thenReturn(user);

        ResponseEntity<UserDTO> response = resource.createUser(userDTO);

        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getHeaders().get("Location"));
    }

    @Test
    void whenUpdateThenReturnSuccess() {
        when(service.updateUser(ID, userDTO)).thenReturn(user);

        ResponseEntity<UserDTO> response = resource.updateUser(ID, userDTO);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(HttpStatus.OK, response.getStatusCode());

        assertEquals(ID, response.getBody().getId());
        assertEquals(NAME, response.getBody().getName());
        assertEquals(EMAIL, response.getBody().getEmail());
        assertEquals(CPF, response.getBody().getCpf());
    }

    @Test
    void whenDeleteThenReturnSuccess() {
        doNothing().when(service.deleteUser(anyInt()));

        ResponseEntity<UserDTO> response = resource.deleteUser(ID);

        assertNotNull(response);
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(service, times(1)).deleteUser(ID);
    }

    private void startUser() {
        user = new User(ID, NAME, EMAIL, CPF, PASSWORD);
        userDTO = new UserDTO(ID, NAME, EMAIL, CPF, PASSWORD);
    }
}