package com.SE.RoomBook.Repository;

import com.SE.RoomBook.Entity.Borrow;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface BorrowRepository extends JpaRepository<Borrow, Long> {
}