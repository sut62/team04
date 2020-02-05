package com.SE.RoomBook;

import com.SE.RoomBook.Entity.*;
import com.SE.RoomBook.Repository.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;


@DataJpaTest
public class BorrowTest{

    private Validator Validator;

    @Autowired
    private BorrowRepository BorrowRepository;

    @BeforeEach
    public void setup(){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator = factory.getValidator();
    }
    //test1
    @Test
    void B6007317_testAllOK() throws ParseException {
        
        Borrow borrow = new Borrow();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
        String dateInString = "31-08-1982 10:20:56";
        Date date = sdf.parse(dateInString);

        borrow.setBordate(date);
        borrow.setBornote("test");
        //borrow.setBorstatus(true);

        borrow = BorrowRepository.saveAndFlush(borrow);

        Optional<Borrow> found = BorrowRepository.findById(borrow.getBid());

        assertEquals(date, found.get().getBordate());
        assertEquals(sdf.parse("31-08-1982 10:20:56"), found.get().getBordate());
        assertEquals("test", found.get().getBornote());
        //assertEquals(true, found.get().getBorstatus());
    }
    
    //test2 NotNull
    @Test
    void B6007317_testDateMustBeNotNull() throws ParseException {

       
        Borrow borrow = new Borrow();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
        String dateInString = "31-08-1982 10:20:56";
        Date date = sdf.parse(dateInString);
        
        
        borrow.setBordate(null);
        borrow.setBornote("test");
        //borrow.setBorstatus(true);

        // ตรวจสอบ error และเก็บค่า error ในรูปแบบ set
        Set<ConstraintViolation<Borrow>> result = Validator.validate(borrow);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Borrow> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("bordate", v.getPropertyPath().toString());
    }
    
    //test3 @Size
    @Test
    void B6007317_testNoteMax50Digits() throws ParseException {

        //เตรียมเวลา ทั้ง Date
        Borrow borrow = new Borrow();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
        String dateInString = "31-08-1982 10:20:56";
        Date date = sdf.parse(dateInString);
        
        
        borrow.setBordate(date);
        borrow.setBornote("teststeststeststeststeststeststeststeststeststeststests");
        //borrow.setBorstatus(true);

        // ตรวจสอบ error และเก็บค่า error ในรูปแบบ set
        Set<ConstraintViolation<Borrow>> result = Validator.validate(borrow);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Borrow> v = result.iterator().next();
        assertEquals("size must be between 0 and 50", v.getMessage());
        assertEquals("bornote", v.getPropertyPath().toString());

    }
    //test4 @Pattern
    @Test
    void B6007317_testNotePattern() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
        String dateInString = "31-08-1982 10:20:56";
        Date date = sdf.parse(dateInString);

        Borrow borrow = new Borrow();
        borrow.setBordate(date);
        borrow.setBornote("วุ่นวายยย++");
        //borrow.setBorstatus(true);

        Set<ConstraintViolation<Borrow>> result = Validator.validate(borrow);
        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Borrow> v = result.iterator().next();
        assertEquals("must match \"[a-zA-Z0-9ก-๙-]*\"", v.getMessage());
        assertEquals("bornote", v.getPropertyPath().toString());
    }

    @Test
    void B6007317_testBorrow_noteMustBeNotNull() throws ParseException {
        // //เตรียมเวลา ทั้ง Date และ LocalDate
        SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
        String dateInString = "31-08-1982 10:20:56";
        Date date = sdf.parse(dateInString);
        // // สร้าง ManageStatus และทำการใส่ เวลา (Date and LocalDate)
        Borrow borrow = new Borrow();
        borrow.setBordate(date);
        borrow.setBornote(null);
        //borrow.setBorstatus(true);


        // ตรวจสอบ error และเก็บค่า error ในรูปแบบ set
        Set<ConstraintViolation<Borrow>> result = Validator.validate(borrow);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Borrow> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("bornote", v.getPropertyPath().toString());

    }
    /*@Test
    void B6007317_testBorrow_statusMustBeNotNull() throws ParseException {
        // //เตรียมเวลา ทั้ง Date และ LocalDate
        SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
        String dateInString = "31-08-1982 10:20:56";
        Date date = sdf.parse(dateInString);
        // // สร้าง ManageStatus และทำการใส่ เวลา (Date and LocalDate)
        Borrow borrow = new Borrow();
        borrow.setBordate(date);
        borrow.setBornote("test");
        borrow.setBorstatus(null);


        // ตรวจสอบ error และเก็บค่า error ในรูปแบบ set
        Set<ConstraintViolation<Borrow>> result = Validator.validate(borrow);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Borrow> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("borstatus", v.getPropertyPath().toString());

    }*/

}
