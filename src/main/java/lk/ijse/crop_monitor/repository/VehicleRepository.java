package lk.ijse.crop_monitor.repository;

import lk.ijse.crop_monitor.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, String> {
    Vehicle getVehicleByVehicleCode(String vehicleCode);
}
