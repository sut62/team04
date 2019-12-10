package com.SE.RoomBook.Entity;

import lombok.*;

import javax.persistence.Id;

import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Data
@Entity
@NoArgsConstructor
@Table(name="EMPLOYEE")
public class Employee {
    @Id
    @SequenceGenerator(name="EMPLOYEE_SEQ",sequenceName="EMPLOYEE_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="EMPLOYEE_SEQ")
    @Column(name="EMPLOYEE_ID",unique = true, nullable = true)
    private @NonNull Long em_id;
    @Column(name="EMPLOYEE_NAME",unique = false, nullable = true)
    private @NonNull String em_name;

     @OneToMany(fetch = FetchType.EAGER)
    //mappedBy  = "Employee"
    private Collection<BookRanking> BookRanking;
}
