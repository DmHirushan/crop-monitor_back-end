package lk.ijse.crop_monitor.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.crop_monitor.dto.impl.CropDto;
import lk.ijse.crop_monitor.repository.CropRepository;
import lk.ijse.crop_monitor.service.CropService;
import lk.ijse.crop_monitor.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CropServiceImpl implements CropService {
    @Autowired
    private CropRepository cropRepository;
    @Autowired
    private Mapping mapping;
    @Override
    public void saveCrop(CropDto cropDto) {
        cropRepository.save(mapping.convertToEntity(cropDto));
    }
}
