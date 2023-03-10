package br.api.api.services;

import br.api.api.domain.User;
import br.api.api.domain.dtos.UserDTO;
import br.api.api.repositories.UserRepository;
import br.api.api.services.exception.DataIntegratyViolationException;
import br.api.api.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;
    public User findById(Integer id) {
        Optional<User> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Object not found: " + id));
    }

    public List<User> findAllUser() {
        return repository.findAll();
    }


    public User createUser(UserDTO objDto) {
        objDto.setId(null);
        emailIsExisting(objDto);
        User newObj = new User(objDto);
        return repository.save(newObj);
    }

    public User updateUser(Integer id, UserDTO objDto) {
        objDto.setId(id);
        emailIsExisting(objDto);
        User oldObj = new User(objDto);
        return repository.save(oldObj);
    }

    public void deleteUser(Integer id) {
        findById(id);
        repository.deleteById(id);
    }

    private void emailIsExisting(UserDTO objDto) {
        Optional<User> user = repository.findByEmail(objDto.getEmail());
        if (user.isPresent() && !user.get().getId().equals(objDto.getId())) {
            throw new DataIntegratyViolationException("Email already registered");
        }
    }
}
