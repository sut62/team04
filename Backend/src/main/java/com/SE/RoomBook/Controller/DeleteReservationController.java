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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TimeZone;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.net.URLDecoder;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;

@CrossOrigin(origins = "http://localhost:8080")
@RestController

public class DeleteReservationController {

    @Autowired
    private final ReservationsRepository reservationsRepository;

    public DeleteReservationController(ReservationsRepository reservationsRepository) {
        this.reservationsRepository = reservationsRepository;
    }

    // เรียก list Reservation ทั้งหมด ที่มี confrim = true
    @GetMapping("/reservationCustomerId/{customerId}")
    public Collection<Reservations> getResvationAlllistCustomer(@PathVariable("customerId") Long id) {

        return reservationsRepository.findCustomerofReservationByIdAlllist(id).stream().collect(Collectors.toList());
    }

    // แก้ไข Reservation ที่ต้องการเปลี่ยน ให้เป็นยกเลิกการจอง
    @PutMapping("/reservationCustomer/{reservationid}")
    public Reservations updateReservation(@PathVariable("reservationid") Long id) {

        Reservations rchange = reservationsRepository.findReservationById(id);
        // rchange.setManageStatus(rchange.getManageStatus());
        // rchange.setCustomer(rchange.getCustomer());
        // rchange.setStartTime(rchange.getStartTime());
        // rchange.setEndTime(rchange.getEndTime());
        // rchange.setBookdate(rchange.getBookdate());
        rchange.setConfrimBook(false);

        return reservationsRepository.save(rchange);
    }
}