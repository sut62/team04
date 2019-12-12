package com.okta.springbootvue.entity;

import lombok.*;

import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Data
@Entity
@Getter @Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name="RETURNS")
public class Returns {
    @Id    
    @SequenceGenerator(name="returns_SEQ",sequenceName="returns_SEQ")               
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="returns_SEQ")
    @Column(name="RETURNS_Id",unique = true, nullable = true)
    private @NonNull Long ReturnsId;
    private @NonNull Date date;
    
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Employee.class)
    @JoinColumn(name = "EMPLOYEE_ID", insertable = true)
    private Employee employee;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Customer.class)
    @JoinColumn(name = "CUSTOMER_ID", insertable = true)
    private Customer customer;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Equipment.class)
    @JoinColumn(name = "Equipment_ID", insertable = true)
    private Equipment equipment;

}
