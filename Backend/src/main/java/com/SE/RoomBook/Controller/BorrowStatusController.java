package com.SE.RoomBook.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.stream.Collectors;

import com.SE.RoomBook.Entity.BorrowStatus;
import com.SE.RoomBook.Repository.BorrowStatusRepository;

import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class BorrowStatusController {

    @Autowired
    private final BorrowStatusRepository BorrowStatusRepository;

    public BorrowStatusController(BorrowStatusRepository BorrowStatusRepository) {
        this.BorrowStatusRepository = BorrowStatusRepository;
    }

    @GetMapping("/borrowStatus")
    public Collection<BorrowStatus> BorrowStatuss() {
        return BorrowStatusRepository.findAll().stream().collect(Collectors.toList());
    }
}
