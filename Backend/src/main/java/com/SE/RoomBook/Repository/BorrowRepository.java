package com.SE.RoomBook.Repository;

import java.util.Collection;

import com.SE.RoomBook.Entity.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.repository.query.Param;

@RepositoryRestResource
public
interface BorrowRepository extends JpaRepository<Borrow, Long> {
    Borrow findById(long id);
    
}