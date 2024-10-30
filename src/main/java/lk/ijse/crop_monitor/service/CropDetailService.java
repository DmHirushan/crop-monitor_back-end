package lk.ijse.crop_monitor.service;

import lk.ijse.crop_monitor.customObj.CropDetailResponse;
import lk.ijse.crop_monitor.dto.impl.CropDetailsDto;
import lk.ijse.crop_monitor.dto.impl.CropDto;

import java.util.List;

public interface CropDetailService {
    void saveCropDetail(CropDetailsDto cropDetailsDto);
    CropDetailResponse getCropDetail(String logCode);
    void deleteCropDetail(String logCode);
    void updateCropDetail(CropDetailsDto cropDetailsDto);
    List<CropDetailsDto> getAllCropDetails();
}
