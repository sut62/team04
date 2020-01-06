package com.SE.RoomBook.Repository;

import com.SE.RoomBook.Entity.Reservations;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface ReservationsRepository extends JpaRepository<Reservations, Long> {
	Reservations findById(long id);
}