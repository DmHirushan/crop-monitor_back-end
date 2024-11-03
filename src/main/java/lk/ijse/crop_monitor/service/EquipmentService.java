package lk.ijse.crop_monitor.service;

import lk.ijse.crop_monitor.customObj.CropResponse;
import lk.ijse.crop_monitor.customObj.EquipmentResponse;
import lk.ijse.crop_monitor.dto.impl.CropDto;
import lk.ijse.crop_monitor.dto.impl.EquipmentDto;

import java.util.List;

public interface EquipmentService {
    void saveEquipment(EquipmentDto equipmentDto);
    EquipmentResponse getEquipment(String equipmentId);
    void deleteEquipment(String equipmentId);
    void updateEquipment(EquipmentDto equipmentDto);
    List<EquipmentDto> getAllEquipments();
}
