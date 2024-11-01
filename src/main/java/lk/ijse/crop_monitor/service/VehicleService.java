package lk.ijse.crop_monitor.service;

import lk.ijse.crop_monitor.customObj.CropResponse;
import lk.ijse.crop_monitor.customObj.VehicleResponse;
import lk.ijse.crop_monitor.dto.impl.CropDto;
import lk.ijse.crop_monitor.dto.impl.VehicleDto;

import java.util.List;

public interface VehicleService {
    void saveVehicle(VehicleDto vehicleDto);
    VehicleResponse getVehicle(String vehicleCode);
    void deleteVehicle(String vehicleCode);
    void updateVehicle(VehicleDto vehicleDto);
    List<VehicleDto> getAllVehicle();
}
