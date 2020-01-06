package com.SE.RoomBook.Entity;

import lombok.*;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import java.sql.Date;
//import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Data
@Entity
@NoArgsConstructor
@Table(name = "Reservations")
public class Reservations {
    @Id
    @SequenceGenerator(name = "Reservations_SEQ", sequenceName = "Reservations_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Reservations_SEQ")
    @Column(name = "Reservations_id", unique = true, nullable = true)
    private @NonNull Long Reservations_id;

    @Column(name = "StartTime")
    private @NonNull Date StartTime;

    @Column(name = "EndTime")
    private @NonNull Date EndTime;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = ManageStatus.class)
    @JoinColumn(name = "manageStatus_id", insertable = true)
    private ManageStatus manageStatus;

    @OneToMany(fetch = FetchType.EAGER, targetEntity = DetailPurpose.class)
    @JoinColumn(name = "DetailPurpose_id", insertable = true)
    private DetailPurpose detailPurpose;


    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Customer.class)
    @JoinColumn(name = "EMPLOYEE_ID", insertable = true)
    private Customer customer;

}