package com.SE.RoomBook;


import com.SE.RoomBook.Entity.ManageEquipment;
import com.SE.RoomBook.Repository.ManageEquipmentRepository;
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
public class ManageEquipmentTest{
    private Validator validator;

    @Autowired
    private ManageEquipmentRepository ManageEquipmentRepository;

    @BeforeEach
    public void setup(){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testAmountOK(){
        ManageEquipment ManageEquipment = new ManageEquipment();
        ManageEquipment.setManageEquipment_amount("20");

        ManageEquipment = ManageEquipmentRepository.saveAndFlush(ManageEquipment);
        Optional<ManageEquipment> found = ManageEquipmentRepository.findById(ManageEquipment.getManageEquipment_id());
        assertEquals("20", found.get().getManageEquipment_amount());
    }

   @Test
    void testAmountMustNotBeNull() {
        ManageEquipment ManageEquipment = new ManageEquipment();
        ManageEquipment.setManageEquipment_amount(null);
        Set<ConstraintViolation<ManageEquipment>> result = validator.validate(ManageEquipment);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<ManageEquipment> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("manageEquipment_amount", v.getPropertyPath().toString());
    }

    @Test
     void testAmountMustHaveNumber() {
         ManageEquipment ManageEquipment = new ManageEquipment();
         ManageEquipment.setManageEquipment_amount("A1");

         Set<ConstraintViolation<ManageEquipment>> result = validator.validate(ManageEquipment);

         // result ต้องมี error 1 ค่าเท่านั้น
         assertEquals(1, result.size());

         // error message ตรงชนิด และถูก field
         ConstraintViolation<ManageEquipment> v = result.iterator().next();
         assertEquals("must match \"\\d{1,2}\"", v.getMessage());
         assertEquals("manageEquipment_amount", v.getPropertyPath().toString());
     }
}
