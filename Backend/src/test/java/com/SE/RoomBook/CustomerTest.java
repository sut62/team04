package com.SE.RoomBook;


import com.SE.RoomBook.Entity.Customer;
import com.SE.RoomBook.Repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@DataJpaTest
public class CustomerTest{
    private Validator validator;
        
    @Autowired
    private CustomerRepository customerRepository;
    
    @BeforeEach
    public void setup(){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testCustomerOKFullData(){
        Customer customer = new Customer();
        customer.setName("Test");
        customer.setPassword("Test");
        customer.setEmail("Test@ex.com");
        customer.setPhone("0930949139");
        

        customer = customerRepository.saveAndFlush(customer);
        Optional<Customer> found = customerRepository.findById(customer.getId());
        assertEquals("Test", found.get().getName());
        assertEquals("Test", found.get().getPassword());
        assertEquals("Test@ex.com", found.get().getEmail());
        assertEquals("0930949139", found.get().getPhone());
        
    }
    @Test
    void testCustomerNameMustNotBeNull() {
        Customer customer = new Customer();
        customer.setName(null);
        customer.setPassword("Test");
        customer.setEmail("Test@ex.com");
        customer.setPhone("0930949139");
        

        Set<ConstraintViolation<Customer>> result = validator.validate(customer);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Customer> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("Name", v.getPropertyPath().toString());
    }

    @Test
    void testCustomerEmailMustHaveAddress() {
        Customer customer = new Customer();
        customer.setName("Test");
        customer.setPassword("Test");
        customer.setEmail("Testex.com");
        customer.setPhone("0930949139");
        

        Set<ConstraintViolation<Customer>> result = validator.validate(customer);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Customer> v = result.iterator().next();
        assertEquals("must be a well-formed email address", v.getMessage());
        assertEquals("Email", v.getPropertyPath().toString());
    }

    @Test
    void testCustomePhoneMustHaveNumber() {
        Customer customer = new Customer();
        customer.setName("Test");
        customer.setPassword("Test");
        customer.setEmail("Testex@ex.com");
        customer.setPhone("A123456789");
        

        Set<ConstraintViolation<Customer>> result = validator.validate(customer);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Customer> v = result.iterator().next();
        assertEquals("must match \"\\d{10}\"", v.getMessage());
        assertEquals("Phone", v.getPropertyPath().toString());
    }
    @Test
    void testCustomerEmailMustBeUnique() {
        Customer customer = new Customer();
        customer.setName("Test");
        customer.setPassword("Test");
        customer.setEmail("Test@ex.com");
        customer.setPhone("0930949139");
        customerRepository.saveAndFlush(customer);
        
        
        
        assertThrows(DataIntegrityViolationException.class, () -> {
            Customer customer2 = new Customer();
            customer2.setName("Test2");
            customer2.setPassword("Test2");
            customer2.setEmail("Test@ex.com");
            customer2.setPhone("0849835818");
            customerRepository.saveAndFlush(customer2);
        });
    }
}