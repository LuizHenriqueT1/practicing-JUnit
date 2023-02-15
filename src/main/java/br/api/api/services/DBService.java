package br.api.api.services;

import br.api.api.domain.User;
import br.api.api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DBService {

    @Autowired
    private UserRepository userRepository;

    public void instanceDB() {
        User u1 = new User(null, "Maria Eduarda", "maria_duda@gmail.com", "854.415.940-04", "12345678");

        userRepository.saveAll(List.of(u1));
    }
}
