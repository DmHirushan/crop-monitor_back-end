package lk.ijse.crop_monitor.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.crop_monitor.dto.impl.CropDto;
import lk.ijse.crop_monitor.entity.Crop;
import lk.ijse.crop_monitor.exception.DataPersistFailedException;
import lk.ijse.crop_monitor.repository.CropRepository;
import lk.ijse.crop_monitor.service.CropService;
import lk.ijse.crop_monitor.util.AppUtil;
import lk.ijse.crop_monitor.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class CropServiceImpl implements CropService {
    private final CropRepository cropRepository;
    private final Mapping mapping;

    @Override
    public void saveCrop(CropDto cropDto) {
        cropDto.setCropCode(AppUtil.createCropCode());
        Crop savedUser = cropRepository.save(mapping.convertToEntity(cropDto, Crop.class));
        if (savedUser == null && savedUser.getCropCode() == null) {
            throw new DataPersistFailedException("Can't save the crop!");
        }

    }
}
