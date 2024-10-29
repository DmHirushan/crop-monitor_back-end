package lk.ijse.crop_monitor.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.crop_monitor.customObj.CropDetailResponse;
import lk.ijse.crop_monitor.customObj.impl.CropDetailErrorResponse;
import lk.ijse.crop_monitor.dto.impl.CropDetailsDto;
import lk.ijse.crop_monitor.dto.impl.CropDto;
import lk.ijse.crop_monitor.entity.CropDetails;
import lk.ijse.crop_monitor.exception.DataPersistFailedException;
import lk.ijse.crop_monitor.exception.NotFoundException;
import lk.ijse.crop_monitor.repository.CropDetailRepository;
import lk.ijse.crop_monitor.repository.CropRepository;
import lk.ijse.crop_monitor.service.CropDetailService;
import lk.ijse.crop_monitor.util.AppUtil;
import lk.ijse.crop_monitor.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CropDetailServiceImpl implements CropDetailService {
    private final CropDetailRepository cropDetailRepository;
    private final Mapping mapping;

    @Override
    public void saveCropDetail(CropDetailsDto cropDetailsDto) {
        cropDetailsDto.setLogCode(AppUtil.createCropDetailCode());
        CropDetails savedCropDetails = cropDetailRepository.save(mapping.convertToEntity(cropDetailsDto, CropDetails.class));
        if (savedCropDetails == null && savedCropDetails.getLogCode() == null){
            throw new DataPersistFailedException("Can't save Crop Details");
        }
    }

    @Override
    public CropDetailResponse getCropDetail(String logCode) {
        if (cropDetailRepository.existsById(logCode)){
            return mapping.convertToDto(cropDetailRepository.getCropDetailsByLogCode(logCode), CropDetailsDto.class);
        }else {
            return new CropDetailErrorResponse(0, "Crop Detail not found");
        }
    }

    @Override
    public void deleteCropDetail(String logCode) {
        Optional<CropDetails> selectedCropDetail = cropDetailRepository.findById(logCode);
        if (!selectedCropDetail.isPresent()){
            throw new NotFoundException("Crop Detail Not Found");
        }else {
            cropDetailRepository.deleteById(logCode);
        }
    }

    @Override
    public void updateCropDetail(CropDetailsDto cropDetailsDto) {

    }


}
