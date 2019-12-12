package com.SE.RoomBook.Entity;

import lombok.*;

import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import javax.persistence.Column;
import javax.persistence.Entity;
//import javax.persistence.FetchType;

@Data
@Entity
@NoArgsConstructor
@Table(name = "PurposeRoom")
public class PurposeRoom {

    @Id
    @SequenceGenerator(name = "PurposeRoom_seq", sequenceName = "PurposeRoom_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PurposeRoom_seq")
    @Column(name = "PurposeRoom_id", unique = true, nullable = true)
    private @NonNull Long PurposeRoom_id;

    @Column(name = "Name", unique = false, nullable = true)
    private @NonNull String name;

}
