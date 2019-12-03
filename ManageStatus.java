package com.okta.springbootvue.entity;

import lombok.*;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;

import com.okta.springbootvue.entity.Room;;
import com.okta.springbootvue.entity.Status;;
import com.okta.springbootvue.entity.Employee;;


@Data
@Entity
@NoArgsConstructor
@Table(name="ManageStatus")
public class ManageStatus {
    @Id
    @SequenceGenerator(name="ManageStatus_seq",sequenceName="ManageStatus_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="ManageStatus_seq")
    @Column(name = "ManageStatus_id", unique = true, nullable = true)
    private @NonNull Long ManageStatus_id;


    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Book.class)
    @JoinColumn(name = "Status_id", insertable = true)
    private Status status;


    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Typeranking.class)
    @JoinColumn(name = "Room_id", insertable = true)
    private Room room;

     @ManyToOne(fetch = FetchType.EAGER, targetEntity = Employee.class)
    @JoinColumn(name = "EMPLOYEE_ID", insertable = true)
    private Employee createdBy;

     @Column(name="RENT_DATE")
    private @NonNull Date rentDate;

}
