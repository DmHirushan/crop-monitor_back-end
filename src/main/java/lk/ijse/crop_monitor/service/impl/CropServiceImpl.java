package lk.ijse.crop_monitor.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.crop_monitor.customObj.CropResponse;
import lk.ijse.crop_monitor.customObj.impl.CropErrorResponse;
import lk.ijse.crop_monitor.dto.impl.CropDto;
import lk.ijse.crop_monitor.entity.Crop;
import lk.ijse.crop_monitor.exception.DataPersistFailedException;
import lk.ijse.crop_monitor.exception.NotFoundException;
import lk.ijse.crop_monitor.repository.CropRepository;
import lk.ijse.crop_monitor.service.CropService;
import lk.ijse.crop_monitor.util.AppUtil;
import lk.ijse.crop_monitor.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Override
    public CropResponse getCrop(String cropCode) {
        if (cropRepository.existsById(cropCode)) {
            return mapping.convertToDto(cropRepository.getCropByCropCode(cropCode), CropDto.class);
        }else {
            return new CropErrorResponse(0, "Crop not found");
        }
    }

    @Override
    public void deleteCrop(String cropCode) {
        Optional<Crop> selectedCrop = cropRepository.findById(cropCode);
        if (!selectedCrop.isPresent()){
            throw new NotFoundException("User not found");
        }else {
            cropRepository.deleteById(cropCode);
        }
    }

    @Override
    public void updateCrop(CropDto cropDto) {
        Optional<Crop> tmpCrop = cropRepository.findById(cropDto.getCropCode());
        if (!tmpCrop.isPresent()){
            throw new NotFoundException("Crop not found");
        }else {
            tmpCrop.get().setCropCommonName(cropDto.getCropCommonName());
            tmpCrop.get().setCropScientificName(cropDto.getCropScientificName());
            tmpCrop.get().setCropImage(cropDto.getCropImage());
            tmpCrop.get().setCategory(cropDto.getCategory());
            tmpCrop.get().setCropSeason(cropDto.getCropSeason());
            tmpCrop.get().getField().setFieldCode((cropDto.getFieldCode()));
            tmpCrop.get().getCropDetails().setLogCode((cropDto.getFieldCode()));
        }
    }

    @Override
    public List<CropDto> getAllCrops() {
        return mapping.convertToDto(cropRepository.findAll(), CropDto.class);
    }


}
