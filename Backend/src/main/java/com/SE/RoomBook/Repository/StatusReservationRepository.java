package com.SE.RoomBook.Repository;

import com.SE.RoomBook.Entity.StatusReservation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface StatusReservationRepository extends JpaRepository<StatusReservation, Long> {

    StatusReservation findById(long StatusReservation_id);

    @Query(value = "SELECT * FROM STATUS_RESERVATION  WHERE STATUS_RESERVATION_NAME = 'confirm'", nativeQuery = true)
    StatusReservation findConfirmByName();

    @Query(value = "SELECT * FROM STATUS_RESERVATION  WHERE STATUS_RESERVATION_NAME = 'cancel'", nativeQuery = true)
    StatusReservation findCancelByName();

}
