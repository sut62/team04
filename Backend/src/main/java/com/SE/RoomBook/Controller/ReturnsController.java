package com.SE.RoomBook.Controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.net.URLDecoder;
import com.SE.RoomBook.Entity.*;
import com.SE.RoomBook.Repository.*;

import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class ReturnsController {
    @Autowired
    private final ReturnsRepository returnsRepository;
    @Autowired
    private BorrowRepository borrowRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private CustomerRepository customerRepository;

    ReturnsController(ReturnsRepository returnsRepository) {
        this.returnsRepository = returnsRepository;
    }

    @GetMapping("/returns")
    public Collection<Returns> Returns() {
        return returnsRepository.findAll().stream().collect(Collectors.toList());
    }

    @PostMapping("/returns/{C}/{bid}/{E}/{N}")
    public Returns newReturns(Returns newReturns, @PathVariable long C, @PathVariable long bid, @PathVariable long E,@PathVariable String N) {

        Employee employee = employeeRepository.findById(E);
        Borrow borrow = borrowRepository.findById(bid);
        Customer customer = customerRepository.findById(C);

        newReturns.setBorrow(borrow);
        newReturns.setCustomer(customer);
        newReturns.setEmployee(employee);
        newReturns.setReturnsdate(new Date());
        newReturns.setNote(N);

        return returnsRepository.save(newReturns);

    }
}
