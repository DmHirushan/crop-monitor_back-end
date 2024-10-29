package lk.ijse.crop_monitor.service;

import lk.ijse.crop_monitor.dto.impl.CropDetailsDto;

public interface CropDetailService {
    void saveCropDetail(CropDetailsDto cropDetailsDto);
}
