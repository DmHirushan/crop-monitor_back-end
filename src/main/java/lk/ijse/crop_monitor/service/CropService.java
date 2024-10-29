package lk.ijse.crop_monitor.service;

import lk.ijse.crop_monitor.customObj.CropResponse;
import lk.ijse.crop_monitor.dto.impl.CropDto;

import java.util.List;

public interface CropService {
    void saveCrop(CropDto cropDto);
    CropResponse getCrop(String cropCode);
    void deleteCrop(String cropCode);
    void updateCrop(CropDto cropDto);
    List<CropDto> getAllCrops();
}
