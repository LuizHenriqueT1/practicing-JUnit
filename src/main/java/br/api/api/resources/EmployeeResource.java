package br.api.api.resources;


import br.api.api.domain.Employee;
import br.api.api.domain.dtos.EmployeeDTO;
import br.api.api.services.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping(value = "/user/employee")
public class EmployeeResource {

    private EmployeeService service;
    private EmployeeDTO objDto;

    @GetMapping(value = "/{id}")
    public ResponseEntity<EmployeeDTO> findByNameEmployee(@PathVariable Integer id) {
        Employee obj = service.findById(id);
        return ResponseEntity.ok().body(new EmployeeDTO(obj));
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> findAllEmployee() {
        List<Employee> list = service.findAllEmployee();
        List<EmployeeDTO> listDto = list.stream()
                .map(epy -> new EmployeeDTO(epy)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }

    @PostMapping
    public ResponseEntity<EmployeeDTO> createEmployee(@Valid @RequestBody EmployeeDTO objDto) {
        Employee newObj = service.createEmployee(objDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable Integer id, @RequestBody EmployeeDTO objDto) {
        Employee obj = service.updateEmployee(id, objDto);
        return ResponseEntity.ok().body(new EmployeeDTO(obj));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<EmployeeDTO> deleteEmployee(@PathVariable Integer id) {
        service.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }
}
