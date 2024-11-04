package lk.ijse.crop_monitor.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.crop_monitor.customObj.CropDetailResponse;
import lk.ijse.crop_monitor.customObj.impl.CropDetailErrorResponse;
import lk.ijse.crop_monitor.dto.impl.CropDetailsDto;
import lk.ijse.crop_monitor.entity.Crop;
import lk.ijse.crop_monitor.entity.CropDetails;
import lk.ijse.crop_monitor.entity.Field;
import lk.ijse.crop_monitor.entity.Staff;
import lk.ijse.crop_monitor.exception.DataPersistFailedException;
import lk.ijse.crop_monitor.exception.NotFoundException;
import lk.ijse.crop_monitor.repository.CropDetailRepository;
import lk.ijse.crop_monitor.repository.CropRepository;
import lk.ijse.crop_monitor.repository.FieldRepository;
import lk.ijse.crop_monitor.repository.StaffRepository;
import lk.ijse.crop_monitor.service.CropDetailService;
import lk.ijse.crop_monitor.util.AppUtil;
import lk.ijse.crop_monitor.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CropDetailServiceImpl implements CropDetailService {
    private final CropDetailRepository cropDetailRepository;
    private final FieldRepository fieldRepository;
    private final CropRepository cropRepository;
    private final StaffRepository staffRepository;
    private final Mapping mapping;

    @Override
    public void saveCropDetails(CropDetailsDto cropDetailsDTO) {

        List<Field> filed = new ArrayList<>();
        List<Crop> crops = new ArrayList<>();
        List<Staff> staff = new ArrayList<>();

        for (String fieldCode : cropDetailsDTO.getFieldCodes()) {
            fieldRepository.findById(fieldCode).ifPresent(filed::add);
        }

        for (String cropCode : cropDetailsDTO.getCropCodes()) {
            cropRepository.findById(cropCode).ifPresent(crops::add);
        }

        for (String staffId : cropDetailsDTO.getStaffIds()) {
            staffRepository.findById(staffId).ifPresent(staff::add);
        }

        String logCode = AppUtil.createCropDetailCode();
        cropDetailsDTO.setLogCode(logCode);
        CropDetails cropDetails = mapping.convertToEntity(cropDetailsDTO, CropDetails.class);
        cropDetails.setField(filed);
        cropDetails.setCrop(crops);
        cropDetails.setStaff(staff);
        CropDetails save = cropDetailRepository.save(cropDetails);
        if (save == null){
            throw new DataPersistFailedException("Crop details save failed");
        }
    }

    @Override
    public void updateCropDetails(CropDetailsDto cropDetailsDTO , String logCode) {
        Optional<CropDetails> cropDetailsByLogCode = cropDetailRepository.findById(logCode);
        if (cropDetailsByLogCode.isPresent()){
            cropDetailsByLogCode.get().setLogDetails(cropDetailsDTO.getLogDetails());
            cropDetailsByLogCode.get().setObservedImage(cropDetailsDTO.getObservedImage());
        }else {
            throw new NotFoundException("Crop details not found");
        }
    }

    @Override
    public CropDetailResponse findCropDetailsByLogCode(String logCode) {
        Optional<CropDetails> cropDetailsByLogCode = cropDetailRepository.findById(logCode);
        if (cropDetailsByLogCode.isPresent()){
            CropDetailsDto cropDetailsDTO = mapping.convertToDto(cropDetailsByLogCode.get(), CropDetailsDto.class);
            if (cropDetailsByLogCode.get().getField() != null){
                List<String> fieldCodes = new ArrayList<>();
                cropDetailsByLogCode.get().getField().forEach(
                        field -> fieldCodes.add(field.getFieldCode())
                );
                cropDetailsDTO.setFieldCodes(fieldCodes);
            }
            if (cropDetailsByLogCode.get().getCrop() != null){
                List<String> cropCodes = new ArrayList<>();
                cropDetailsByLogCode.get().getCrop().forEach(
                        crop -> cropCodes.add(crop.getCropCode())
                );
                cropDetailsDTO.setCropCodes(cropCodes);
            }
            if (cropDetailsByLogCode.get().getStaff() != null){
                List<String> staffIds = new ArrayList<>();
                cropDetailsByLogCode.get().getStaff().forEach(
                        staff -> staffIds.add(staff.getId())
                );
                cropDetailsDTO.setStaffIds(staffIds);
            }
            return cropDetailsDTO;
        }else {
            return new CropDetailErrorResponse(0,"Crop details not found");
        }
    }

    @Override
    public void deleteCropDetailsByLogCode(String logCode) {
        Optional<CropDetails> cropDetailsByLogCode = cropDetailRepository.findById(logCode);
        if (cropDetailsByLogCode.isPresent()) {
            cropDetailRepository.delete(cropDetailsByLogCode.get());
        }else {
            throw new NotFoundException("Crop details not found");
        }
    }

    @Override
    public List<CropDetailsDto> getAllCropDetails() {
        List<CropDetails> allCropDetails = cropDetailRepository.findAll();
        return mapping.convertToDto(allCropDetails, CropDetailsDto.class);
    }


}
