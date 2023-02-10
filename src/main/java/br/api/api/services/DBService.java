package br.api.api.services;

import br.api.api.domain.Employee;
import br.api.api.domain.User;
import br.api.api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.api.api.repositories.EmployeeRepository;

import java.util.List;

@Service
public class DBService {

    @Autowired
    private EmployeeRepository repository;

    @Autowired
    private UserRepository userRepository;

    public void instanceDB() {
        Employee e1 = new Employee(null, "Luiz Henrique", "luiz@gmail.com", "195.929.490-34", "12345678", "Developer");
        User u1 = new User(null, "Maria Eduarda", "maria_duda@gmail.com", "854.415.940-04", "12345678");

        repository.saveAll(List.of(e1));
        userRepository.saveAll(List.of(u1));
    }
}
