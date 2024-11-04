package lk.ijse.crop_monitor.service.impl;

import lk.ijse.crop_monitor.customObj.CropResponse;
import lk.ijse.crop_monitor.customObj.FieldResponse;
import lk.ijse.crop_monitor.customObj.impl.CropDetailErrorResponse;
import lk.ijse.crop_monitor.customObj.impl.FieldErrorResponse;
import lk.ijse.crop_monitor.dto.impl.CropDetailsDto;
import lk.ijse.crop_monitor.dto.impl.CropDto;
import lk.ijse.crop_monitor.dto.impl.FieldDto;
import lk.ijse.crop_monitor.entity.*;
import lk.ijse.crop_monitor.exception.DataPersistFailedException;
import lk.ijse.crop_monitor.exception.NotFoundException;
import lk.ijse.crop_monitor.repository.FieldRepository;
import lk.ijse.crop_monitor.repository.StaffRepository;
import lk.ijse.crop_monitor.service.FieldService;
import lk.ijse.crop_monitor.util.AppUtil;
import lk.ijse.crop_monitor.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FieldServiceImpl implements FieldService {
    private final FieldRepository fieldRepository;
    private final StaffRepository staffRepository;
    private final Mapping mapping;


    @Override
    public void saveField(FieldDto fieldDto) {
        Point point = new Point(25, 45);
        fieldDto.setFieldCode(AppUtil.createFieldCode());
        Field savedField = fieldRepository.save(mapping.convertToEntity(fieldDto, Field.class));
        if (savedField == null && savedField.getFieldCode() == null){
            throw new DataPersistFailedException("Can't save Field");
        }
    }

    @Override
    public FieldResponse getField(String fieldCode) {
        if (fieldRepository.existsById(fieldCode)){
            return mapping.convertToDto(fieldRepository.getFieldByFieldCode(fieldCode), FieldDto.class);
        }else {
            return new FieldErrorResponse(0, "Field not found");
        }
    }

    @Override
    public void deleteField(String fieldCode) {
        Optional<Field> selectedField = fieldRepository.findById(fieldCode);
        if (!selectedField.isPresent()){
            throw new NotFoundException("Field not found");
        }else {
            fieldRepository.deleteById(fieldCode);
        }
    }

    @Override
    public void updateField(FieldDto fieldDto, List<String> staffIds) {
        Optional <Field> tmpField = fieldRepository.findById(fieldDto.getFieldCode());
        if (!tmpField.isPresent()){
            throw new NotFoundException("Field Not Found");
        }else {
            Field field = mapping.convertToEntity(fieldDto, Field.class);
            List<Staff> staff = new ArrayList<>();
            for (String staffId : staffIds) {
                Optional<Staff> optional = staffRepository.findById(staffId);
                optional.ifPresent(staff::add);
            }
            field.setStaff(staff);
            fieldRepository.save(field);
        }
    }

    @Override
    public List<FieldDto> getAllFields() {
        return mapping.convertToDto(fieldRepository.findAll(), FieldDto.class);
    }
}
