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

@CrossOrigin(origins = "http://localhost:8081")
@RestController
public class BorrowController {
    @Autowired
    private final BorrowRepository BorrowRepository;
    @Autowired
    private ManageEquipmentRepository ManageEquipmentRepository;
    @Autowired
    private CustomerRepository CustomerRepository;
    @Autowired
    private EmployeeRepository EmployeeRepository;
    

    BorrowController(final BorrowRepository BorrowRepository) {
        this.BorrowRepository = BorrowRepository;
    }

	@GetMapping("/Borrow")
    public Collection<Borrow> Borrows() {
        return BorrowRepository.findAll().stream().collect(Collectors.toList());
    }
    
    @PostMapping("/Borrow/{cus_id}/{em_id}/{manageEquipment_id}")
    public Borrow newBorrow (final Borrow newBorrow,
    @PathVariable final long cus_id,//customer
    @PathVariable final long em_id,//employee
    @PathVariable final long manageEquipment_id)//equipment
    {
    
    final Customer Customer = CustomerRepository.findById(cus_id);
    final Employee Employee = EmployeeRepository.findById(em_id);
    final ManageEquipment ManageEquipment = ManageEquipmentRepository.findById(manageEquipment_id);

    newBorrow.setCustomer(Customer);
    newBorrow.setEmployee(Employee);
    newBorrow.setBordate(new Date());
    newBorrow.setManageequipment(ManageEquipment);
    
    
    return BorrowRepository.save(newBorrow); //บันทึก Objcet ชื่อ Borrow
    
    }
}