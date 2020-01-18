package com.SE.RoomBook.Repository;

import com.SE.RoomBook.Entity.Reservations;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ReservationsRepository extends JpaRepository<Reservations, Long> {
	Reservations findById(long id);

	@Query(value = "SELECT * FROM RESERVATIONS WHERE MANAGE_STATUS_ID = :idroom AND (( START_TIME = :start  OR  END_TIME =  :end )  OR (START_TIME > :start  AND  START_TIME <  :end)  OR  (START_TIME > :start  AND  END_TIME <  :end)  OR (START_TIME < :start  AND  END_TIME >  :end) OR  (END_TIME > :start  AND  END_TIME <  :end))", nativeQuery = true)
	Reservations findTime(@Param("start") String start, @Param("end") String end, @Param("idroom") Long idroom);
}