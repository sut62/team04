package com.SE.RoomBook.Controller;

import com.SE.RoomBook.Entity.*;
import com.SE.RoomBook.Repository.*;
import com.SE.RoomBook.model.*;

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

import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
public class ReservationsController {

    @Autowired
    private final PurposeRoomRepository purposeRoomRepository;

    @Autowired
    private final ReservationsRepository reservationsRepository;

    @Autowired
    private final CustomerRepository customerRepository;

    @Autowired
    private final DetailPurposeRepository detailPurposeRepository;

    @Autowired
    private final ManageStatusRepository manageStatusRepository;

    ReservationsController(ReservationsRepository reservationsRepository, CustomerRepository customerRepository,
            DetailPurposeRepository detailPurposeRepository, PurposeRoomRepository purposeRoomRepository,
            ManageStatusRepository manageStatusRepository) {
        this.detailPurposeRepository = detailPurposeRepository;
        this.reservationsRepository = reservationsRepository;
        this.customerRepository = customerRepository;
        this.purposeRoomRepository = purposeRoomRepository;
        this.manageStatusRepository = manageStatusRepository;
    }

    @GetMapping("/re") // เลือกดูการจองทั้งหมด
    public Collection<Reservations> reservations() {
        return reservationsRepository.findAll().stream().collect(Collectors.toList());
    }

    @PostMapping("/Reservation") // บันทึกการจองห้องค้นคว้าออนไลน์
    public Reservations index(@RequestBody final BodyBook bodyBook) {
        final Reservations r = new Reservations();
        Customer c = customerRepository.findById(bodyBook.getCustomerid()).get();
        ManageStatus m = manageStatusRepository.findById(bodyBook.getRoomid()).get();
        r.setManageStatus(m);
        r.setCustomer(c);
        for (Long purpose : bodyBook.getPurosebook()) {
            DetailPurpose dp = new DetailPurpose();
            PurposeRoom p = purposeRoomRepository.findById(purpose).get();
            dp.setPurposeRoom(p);
            dp.setReservations(r);
            detailPurposeRepository.save(dp);
        }
       
        r.setBookdate(new Date());
        return reservationsRepository.save(r);
    }

}
