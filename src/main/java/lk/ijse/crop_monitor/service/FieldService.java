package lk.ijse.crop_monitor.service;

import lk.ijse.crop_monitor.customObj.CropResponse;
import lk.ijse.crop_monitor.customObj.FieldResponse;
import lk.ijse.crop_monitor.dto.impl.CropDto;
import lk.ijse.crop_monitor.dto.impl.FieldDto;

import java.util.List;

public interface FieldService {
    void saveField(FieldDto fieldDto);
    FieldResponse getField(String fieldCode);
    void deleteField(String fieldCode);
    void updateField(FieldDto fieldDto, List<String> staffIds);
    List<FieldDto> getAllFields();
}
