package com.SE.RoomBook.Controller;

import com.SE.RoomBook.Entity.*;
import com.SE.RoomBook.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class EmployeeController {

    @Autowired
    private final EmployeeRepository EmployeeRepository;

    public EmployeeController(EmployeeRepository EmployeeRepository) {
        this.EmployeeRepository = EmployeeRepository;
    }

    @GetMapping("/employees")
    public Collection<Employee> Employee() {
        return EmployeeRepository.findAll().stream().collect(Collectors.toList());
    }

    // @GetMapping("/Employee/{em_id}")
    // public Optional<Employee> EquipmentName(@PathVariable Long em_id) {
    //     Optional<Employee> Employee = EmployeeRepository.findById(em_id);
    //     return Employee;
    // }

}
