package com.SE.RoomBook.Repository;
import com.SE.RoomBook.Entity.ManageEquipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import java.util.Collection;
@RepositoryRestResource
public interface ManageEquipmentRepository extends JpaRepository<ManageEquipment, Long> {
	ManageEquipment findById(long manageEquipment_id);

	@Query(value = "SELECT * FROM MANAGE_EQUIPMENT WHERE 	MANAGE_EQUIPMENT_ID = :Id" ,nativeQuery = true)
	ManageEquipment findByAmount(@Param("Id") long id);


}
