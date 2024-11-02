package lk.ijse.crop_monitor.repository;

import lk.ijse.crop_monitor.entity.Field;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FieldRepository extends JpaRepository<Field, String> {
    Field getFieldByFieldCode(String fieldCode);
}
