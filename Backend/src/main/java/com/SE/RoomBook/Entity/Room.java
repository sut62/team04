package com.SE.RoomBook.Entity;

import lombok.*;

import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import java.util.Collection;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;

@Data
@Entity
@NoArgsConstructor
@Table(name="ROOMS")
public class Room {
    @Id
    @SequenceGenerator(name="room_seq",sequenceName="room_seq")               
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="room_seq")  
    @Column(name = "room_id", unique = true, nullable = true)
    private @NonNull Long id;
    @Column(name = "room_name")
    private @NonNull String name;


    @OneToMany(fetch = FetchType.EAGER)
    // mappedBy  = "Room"
    private Collection<ManageStatus> ManageStatus;
}
