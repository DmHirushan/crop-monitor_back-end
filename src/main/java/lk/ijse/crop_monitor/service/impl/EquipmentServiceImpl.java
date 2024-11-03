package lk.ijse.crop_monitor.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.crop_monitor.customObj.CropResponse;
import lk.ijse.crop_monitor.customObj.EquipmentResponse;
import lk.ijse.crop_monitor.customObj.impl.CropErrorResponse;
import lk.ijse.crop_monitor.customObj.impl.EquipmentErrorResponse;
import lk.ijse.crop_monitor.dto.impl.CropDto;
import lk.ijse.crop_monitor.dto.impl.EquipmentDto;
import lk.ijse.crop_monitor.entity.Crop;
import lk.ijse.crop_monitor.entity.Equipment;
import lk.ijse.crop_monitor.exception.DataPersistFailedException;
import lk.ijse.crop_monitor.exception.NotFoundException;
import lk.ijse.crop_monitor.repository.EquipmentRepository;
import lk.ijse.crop_monitor.service.EquipmentService;
import lk.ijse.crop_monitor.util.AppUtil;
import lk.ijse.crop_monitor.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class EquipmentServiceImpl implements EquipmentService {
    private final EquipmentRepository equipmentRepository;
    private final Mapping mapping;

    @Override
    public void saveEquipment(EquipmentDto equipmentDto) {
        System.out.println(equipmentDto);
        equipmentDto.setEquipmentId(AppUtil.createEquipmentId());
        Equipment savedEquipment = equipmentRepository.save(mapping.convertToEntity(equipmentDto, Equipment.class));
        if (savedEquipment == null && savedEquipment.getEquipmentId() == null) {
            throw new DataPersistFailedException("Can't save Equipment!");
        }
    }

    @Override
    public EquipmentResponse getEquipment(String equipmentId) {
        if (equipmentRepository.existsById(equipmentId)) {
            return mapping.convertToDto(equipmentRepository.getEquipmentByEquipmentId(equipmentId), EquipmentDto.class);
        }else {
            return new EquipmentErrorResponse(0, "Equipment not found") {
            };
        }
    }

    @Override
    public void deleteEquipment(String equipmentId) {
        Optional<Equipment> selectedEquipment = equipmentRepository.findById(equipmentId);
        if (!selectedEquipment.isPresent()){
            throw new NotFoundException("Equipment not found");
        }else {
            equipmentRepository.deleteById(equipmentId);
        }
    }

    @Override
    public void updateEquipment(EquipmentDto equipmentDto) {
        Optional<Equipment> tmpEquipment = equipmentRepository.findById(equipmentDto.getEquipmentId());
        if (!tmpEquipment.isPresent()){
            throw new NotFoundException("Equipment not found");
        }else {
            equipmentRepository.save(mapping.convertToEntity(equipmentDto, Equipment.class));
        }
    }

    @Override
    public List<EquipmentDto> getAllEquipments() {
        return mapping.convertToDto(equipmentRepository.findAll(), EquipmentDto.class);
    }
}
