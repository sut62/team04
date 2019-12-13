package com.SE.RoomBook.Entity;

import lombok.*;

import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


import javax.persistence.Column;
import javax.persistence.Entity;

@Data
@Entity
@NoArgsConstructor
@Table(name="STATUS")
public class StatusCustomer {
	@Id 
	@SequenceGenerator(name="Status_seq",sequenceName="Status_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="Status_seq")
	@Column(name="Status_ID",unique = true, nullable = true)
	private @NonNull Long id;
	private @NonNull String Status;
	
}