package lk.ijse.crop_monitor.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.crop_monitor.customObj.CropResponse;
import lk.ijse.crop_monitor.customObj.impl.CropErrorResponse;
import lk.ijse.crop_monitor.dto.impl.CropDto;
import lk.ijse.crop_monitor.entity.Crop;
import lk.ijse.crop_monitor.entity.Field;
import lk.ijse.crop_monitor.exception.DataPersistFailedException;
import lk.ijse.crop_monitor.exception.NotFoundException;
import lk.ijse.crop_monitor.repository.CropRepository;
import lk.ijse.crop_monitor.repository.FieldRepository;
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
    private final FieldRepository fieldRepository;
    private final Mapping mapping;

    @Override
    public void saveCrop(CropDto cropDTO, String fieldCode) {
        cropDTO.setCropCode(AppUtil.createCropCode());
        Crop crop = mapping.convertToDto(cropDTO, Crop.class);
        Field field = fieldRepository.findById(fieldCode).orElseThrow(
                () -> new NotFoundException("Field not found")
        );
        crop.setField(field);
        Crop save = cropRepository.save(crop);
        if (save == null){
            throw new DataPersistFailedException("Crop save failed");
        }
    }

    @Override
    public CropResponse getCrop(String id) {
        Optional<Crop> byCropCode = cropRepository.findById(id);
        if (byCropCode.isPresent()){
            CropDto cropDTO = mapping.convertToDto(byCropCode.get(), CropDto.class);
            return cropDTO;
        }else {
            return new CropErrorResponse(0,"Crop not found");
        }
    }

    @Override
    public void updateCrop(CropDto cropDTO, String fieldCode, String id) {
        Optional<Crop> byCropCode = cropRepository.findById(id);
        if (byCropCode.isPresent()){
            Field field = fieldRepository.findById(fieldCode).orElseThrow(
                    () -> new NotFoundException("Field not found")
            );
            byCropCode.get().setField(field);
            byCropCode.get().setCropCommonName(cropDTO.getCropCommonName());
            byCropCode.get().setCategory(cropDTO.getCategory());
            byCropCode.get().setCropSeason(cropDTO.getCropSeason());
            byCropCode.get().setCropScientificName(cropDTO.getCropScientificName());
            byCropCode.get().setCropImage(cropDTO.getCropImage());
        }else {
            throw new NotFoundException("Crop not found");
        }
    }

    @Override
    public void deleteCrop(String id) {
        Optional<Crop> byCropCode = cropRepository.findById(id);
        if (byCropCode.isPresent()){
            cropRepository.deleteById(id);
        }else {
            throw new NotFoundException("Crop not found");
        }
    }

    @Override
    public List<CropDto> getAllCrops() {
        return mapping.convertToDto(cropRepository.findAll(), CropDto.class);
    }

}
