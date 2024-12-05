package lk.ijse.crop_monitor.repository;

import lk.ijse.crop_monitor.entity.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, String> {
    @Query
    Equipment getEquipmentByEquipmentId(String equipmentId);
}
