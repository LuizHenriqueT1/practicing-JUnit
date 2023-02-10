package br.api.api.services;

import br.api.api.domain.Employee;
import br.api.api.domain.dtos.EmployeeDTO;
import br.api.api.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.api.api.repositories.EmployeeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository repository;

    public Employee findById(Integer id) {
        Optional<Employee> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Object not found: " + id));
    }

    public List<Employee> findAllEmployee() {
        return repository.findAll();
    }

    public Employee createEmployee(EmployeeDTO objDto) {
        objDto.setId(null);
        Employee newObj = new Employee(objDto);
        return repository.save(newObj);
    }

    public Employee updateEmployee(Integer id, EmployeeDTO objDto) {
        objDto.setId(id);
        Employee oldObj = findById(id);
        oldObj = new Employee(objDto);
        return repository.save(oldObj);
    }

    public void deleteEmployee(Integer id) {
        repository.deleteById(id);
    }
}
