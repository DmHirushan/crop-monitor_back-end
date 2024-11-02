package lk.ijse.crop_monitor.service;

import lk.ijse.crop_monitor.customObj.CropResponse;
import lk.ijse.crop_monitor.dto.impl.CropDto;

import java.util.List;

public interface FieldService {
    void saveField(CropDto cropDto);
    CropResponse getField(String cropCode);
    void deleteField(String cropCode);
    void updateField(CropDto cropDto);
    List<CropDto> getAllFields();
}
