package com.SE.RoomBook.Entity;

import lombok.*;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.SE.RoomBook.Entity.*;

@Data
@Entity
@NoArgsConstructor
@Table(name="BORROW")
public class Borrow {
    @Id
    @SequenceGenerator(name="borrow_seq",sequenceName="borrow_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="borrow_seq")
    @Column(name = "borrow__id", unique = true, nullable = true)
    private @NonNull Long bid;
    @Column(name="borrow_date",unique = false, nullable = true)
    private @NonNull Date bordate;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Employee.class)
    @JoinColumn(name = "EMPLOYEE_ID", insertable = true)
    private Employee employee;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Customer.class)
    @JoinColumn(name = "CUSTOMER_ID", insertable = true)
    private Customer customer;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = ManageEquipment.class)
    @JoinColumn(name = "ManageEquipment_ID", insertable = true)
    private ManageEquipment manageequipment;
}
