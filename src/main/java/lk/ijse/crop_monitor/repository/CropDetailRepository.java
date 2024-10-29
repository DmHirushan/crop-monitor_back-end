package lk.ijse.crop_monitor.repository;

import lk.ijse.crop_monitor.entity.CropDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CropDetailRepository extends JpaRepository<CropDetails, String> {
}
