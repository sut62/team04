package com.SE.RoomBook;

import com.SE.RoomBook.Entity.*;
import com.SE.RoomBook.Repository.*;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@SpringBootApplication
public class RoomBookApplication {

	public static void main(String[] args) {
		SpringApplication.run(RoomBookApplication.class, args);
	}
	@Bean
	ApplicationRunner init(GenderRepository genderRepository, 
	TitleNameRepository titleNameRepository, 
	StatusCustomerRepository statusCustomer )
	{
		return args -> {
		Employee em1 = new Employee();
			em1.setEm_name("Dodo");
			em1.setPassword("1234");
			employeeRepository.save(em1);

			
			Employee em2 = new Employee();
			em2.setEm_name("Mook");
			em2.setPassword("5678");
			employeeRepository.save(em2);

			
			Employee em3 = new Employee();
			em3.setEm_name("Prow");
			em3.setPassword("9876");
			employeeRepository.save(em3);

			Employee em4 = new Employee();
			em4.setEm_name("Dodo");
			em4.setPassword("4444");
			employeeRepository.save(em4);

			Stream.of("Man", "Female").forEach(Gender -> {
                Gender gender = new Gender(); // สร้าง Object Gender
				gender.setGender(Gender); // set ชื่อ (Gender) ให้ Object ชื่อ gender
				genderRepository.save(gender); // บันทึก Objcet ชื่อ Gender
			});

			Stream.of("Mr.", "Miss.", "Professor.", "Dr.").forEach(Title -> {
				TitleName title = new TitleName(); // สร้าง Object Title
				title.setTitle(Title); // set ชื่อ (Title) ให้ Object ชื่อ title
				titleNameRepository.save(title); // บันทึก Objcet ชื่อ Title
			});

			Stream.of("Studens", "Teacher","Normal Person").forEach(Type -> {
				StatusCustomer Status = new StatusCustomer(); // สร้าง Object Type
				statusCustomer.setStatus(Status); // set ชื่อ (Type) ให้ Object ชื่อ type
				statusCustomer.save(statusCustomer); // บันทึก Objcet ชื่อ Type
			});
		};
	}

}
