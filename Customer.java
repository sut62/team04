package com.SE.RoomBook.Entity;

import lombok.*;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import com.SE.RoomBook.Entity.TitleName;
import com.SE.RoomBook.Entity.Gender;
import com.SE.RoomBook.Entity.Status;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;

@Data
@Entity
@NoArgsConstructor
@Table(name="CUSTOMER")
public class Customer {
	@Id
	@SequenceGenerator(name="customer_seq",sequenceName="customer_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="customer_seq")
	@Column(name="Customer_id",unique = true, nullable = true)
    private @NonNull Long id;
    @Column(name="Customer_name")
    private @NonNull String Name;
    private @NonNull String Password;
    @Column(name="Customer_Email")
    private @NonNull String Email;
    @Column(name="Customer_Phone")
    private @NonNull String Phone;

	@ManyToOne(fetch = FetchType.EAGER ,targetEntity = Gender.class)
	@JoinColumn(name="Gender",insertable = true)
    private Gender Genders;

    @ManyToOne(fetch = FetchType.EAGER ,targetEntity = Status.class)
	@JoinColumn(name="Status",insertable = true)
    private Status Statuss;

    @ManyToOne(fetch = FetchType.EAGER ,targetEntity = TitleName.class)
	@JoinColumn(name="Title",insertable = true)
    private TitleName TitleNames;


    

	}
