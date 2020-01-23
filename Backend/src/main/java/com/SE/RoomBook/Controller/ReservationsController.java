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
import java.util.stream.Collectors;
import java.net.URLDecoder;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class ReservationsController { // Tue Aug 31 10:20:56 ICT 1982

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

    ReservationsController(final ReservationsRepository reservationsRepository,
            final CustomerRepository customerRepository, final DetailPurposeRepository detailPurposeRepository,
            final PurposeRoomRepository purposeRoomRepository, final ManageStatusRepository manageStatusRepository) {
        this.detailPurposeRepository = detailPurposeRepository;
        this.reservationsRepository = reservationsRepository;
        this.customerRepository = customerRepository;
        this.purposeRoomRepository = purposeRoomRepository;
        this.manageStatusRepository = manageStatusRepository;
    }

    @GetMapping("/reservationses") // เลือกดูการจองทั้งหมดที่มี bookconfrim = true
    public Collection<Reservations> reservationss() {
        return reservationsRepository.findByReservationAll().stream().collect(Collectors.toList());
    }

    @GetMapping("/reservationCheckTime/{startTime}/{endTime}/{idroom}")
    public Reservations CheckTime(@PathVariable("startTime") final String startTime,
            @PathVariable("endTime") final String endTime, @PathVariable("idroom") final Long idroom) {
        LocalDate startDate = LocalDate.now();
        String start = startDate.toString() + " " + startTime;
        String end = startDate.toString() + " " + endTime;
        // Reservations checktime = reservationsRepository.findTime(start, end);

        return reservationsRepository.findTime(start, end, idroom);
    }

    @PostMapping("/Reservation") // บันทึกการจองห้องค้นคว้าออนไลน์
    public Reservations index(@RequestBody final BodyBook bodyBook) throws ParseException {
        final Reservations r = new Reservations();

        final Customer c = customerRepository.findById(bodyBook.getCustomerid()).get();
        final ManageStatus m = manageStatusRepository.findById(bodyBook.getRoomid()).get();
        r.setManageStatus(m);
        r.setCustomer(c);

        final LocalDateTime d = LocalDateTime.parse(LocalDate.now() + "T" + bodyBook.getStart());
        r.setStartTime(d);
        final LocalDateTime e = LocalDateTime.parse(LocalDate.now() + "T" + bodyBook.getEnd());
        r.setEndTime(e);
        r.setBookdate(new Date());
        r.setConfrimBook(true);
        for (final Long purpose : bodyBook.getPurosebook()) {
            final DetailPurpose dp = new DetailPurpose();
            final PurposeRoom p = purposeRoomRepository.findById(purpose).get();
            dp.setPurposeRoom(p);
            dp.setReservations(r);
            detailPurposeRepository.save(dp);
        }

        return reservationsRepository.save(r);
    }

}
