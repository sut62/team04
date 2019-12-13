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

//import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;

//import com.fasterxml.jackson.annotation.JsonManagedReference;

@Data
@Entity
@NoArgsConstructor
@Table(name="ManageEquipment")
public class ManageEquipment {
    @Id
    @SequenceGenerator(name="ManageEquipment_seq",sequenceName="ManageEquipment_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="ManageEquipment_seq")
    @Column(name="ManageEquipment_ID",unique = true, nullable = true)
    private @NonNull Long mnageEquipment_id;

    private @NonNull Date manageEquipment_date;

    private @NonNull Integer manageEquipment_amount;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = EquipmentType.class)
    @JoinColumn(name = "EquipmentType_ID", insertable = true)
    private EquipmentType EquipmentType;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = EquipmentName.class)
    @JoinColumn(name = "EquipmentName_ID", insertable = true)
    private EquipmentName EquipmentName;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Employee.class)
    @JoinColumn(name = "EMPLOYEE_ID", insertable = true)
    private Employee Employee;
}