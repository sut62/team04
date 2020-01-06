package com.SE.RoomBook.Repository;

import com.SE.RoomBook.Entity.ManageStatus;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import java.util.Collection;
import javax.persistence.*;

@RepositoryRestResource
public
interface ManageStatusRepository extends JpaRepository<ManageStatus, Long> {
    

    ManageStatus findByRoom(String Room_name);
    Collection<ManageStatus> findByRoomGreaterThan(String Room_name);
    Long Count(); // ตัวนี้จะมองที่ BookRanking ใน Entity  



    //ManageStatus findById(long id);
}
