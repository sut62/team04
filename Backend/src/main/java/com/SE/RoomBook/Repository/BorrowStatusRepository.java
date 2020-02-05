package com.SE.RoomBook.Repository;

import com.SE.RoomBook.Entity.BorrowStatus;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface BorrowStatusRepository extends JpaRepository<BorrowStatus, Long> {

    BorrowStatus findById(long BorrowStatus_id);

    @Query(value = "SELECT * FROM BORROW_STATUS WHERE BORROW_STATUS_ID = 1", nativeQuery = true)
    BorrowStatus findBorrowById();

    @Query(value = "SELECT * FROM BORROW_STATUS WHERE BORROW_STATUS_ID = 2", nativeQuery = true)
    BorrowStatus findNotBorrowById();

}
