package lk.ijse.crop_monitor.service;

import lk.ijse.crop_monitor.dto.impl.CropDto;

public interface CropService {
    void saveCrop(CropDto cropDto);
}
