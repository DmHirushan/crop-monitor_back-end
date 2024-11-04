package lk.ijse.crop_monitor.service;

import lk.ijse.crop_monitor.customObj.CropResponse;
import lk.ijse.crop_monitor.dto.impl.CropDto;

import java.util.List;

public interface CropService {
    void saveCrop(CropDto cropDTO, String fieldCode);

    CropResponse getCrop(String id);

    void updateCrop(CropDto cropDTO, String fieldCode, String id);

    void deleteCrop(String id);

    List getAllCrops();
}
