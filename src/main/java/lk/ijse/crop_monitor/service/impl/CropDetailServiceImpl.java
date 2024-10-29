package lk.ijse.crop_monitor.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.crop_monitor.dto.impl.CropDetailsDto;
import lk.ijse.crop_monitor.entity.CropDetails;
import lk.ijse.crop_monitor.exception.DataPersistFailedException;
import lk.ijse.crop_monitor.repository.CropDetailRepository;
import lk.ijse.crop_monitor.repository.CropRepository;
import lk.ijse.crop_monitor.service.CropDetailService;
import lk.ijse.crop_monitor.util.AppUtil;
import lk.ijse.crop_monitor.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}
