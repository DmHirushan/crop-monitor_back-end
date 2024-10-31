package lk.ijse.crop_monitor.repository;

import lk.ijse.crop_monitor.entity.Crop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CropRepository extends JpaRepository<Crop, String> {
    @Query
    Crop getCropByCropCode(String cropCode);
}
