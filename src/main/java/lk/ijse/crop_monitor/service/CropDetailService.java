package lk.ijse.crop_monitor.service;

import lk.ijse.crop_monitor.customObj.CropDetailResponse;
import lk.ijse.crop_monitor.dto.impl.CropDetailsDto;
import lk.ijse.crop_monitor.dto.impl.CropDto;

import java.util.List;

public interface CropDetailService {
    void saveCropDetails(CropDetailsDto cropDetailsDTO);

    void updateCropDetails(CropDetailsDto cropDetailsDTO , String logCode);

    CropDetailResponse findCropDetailsByLogCode(String logCode);

    void deleteCropDetailsByLogCode(String logCode);

    List<CropDetailsDto> getAllCropDetails();
}
