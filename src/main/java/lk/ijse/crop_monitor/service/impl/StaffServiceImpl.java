package lk.ijse.crop_monitor.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.crop_monitor.customObj.StaffResponse;
import lk.ijse.crop_monitor.customObj.impl.CropErrorResponse;
import lk.ijse.crop_monitor.customObj.impl.StaffErrorResponse;
import lk.ijse.crop_monitor.dto.impl.CropDto;
import lk.ijse.crop_monitor.dto.impl.StaffDto;
import lk.ijse.crop_monitor.entity.Crop;
import lk.ijse.crop_monitor.entity.Gender;
import lk.ijse.crop_monitor.entity.Role;
import lk.ijse.crop_monitor.entity.Staff;
import lk.ijse.crop_monitor.exception.DataPersistFailedException;
import lk.ijse.crop_monitor.exception.NotFoundException;
import lk.ijse.crop_monitor.repository.StaffRepository;
import lk.ijse.crop_monitor.service.StaffService;
import lk.ijse.crop_monitor.util.AppUtil;
import lk.ijse.crop_monitor.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class StaffServiceImpl implements StaffService {
    private final StaffRepository staffRepository;
    private final Mapping mapping;

    @Override
    public void saveStaffMember(StaffDto staffDto) {
        staffDto.setId(AppUtil.createStaffId());
        var savedStaffMember = staffRepository.save(mapping.convertToEntity(staffDto, Staff.class));
        if (savedStaffMember == null){
            throw new DataPersistFailedException("Can't save the Staff Member");
        }
    }

    @Override
    public StaffResponse getStaffMember(String id) {
        if (staffRepository.existsById(id)) {
            return mapping.convertToDto(staffRepository.getStaffMemberById(id), StaffDto.class);
        }else {
            return new StaffErrorResponse(0, "Staff Member not found");
        }
    }

    @Override
    public void deleteStaffMember(String id) {
        var selectedCrop = staffRepository.findById(id);
        if (!selectedCrop.isPresent()){
            throw new NotFoundException("User not found");
        }else {
            staffRepository.deleteById(id);
        }
    }

    @Override
    public void updateStaffMember(StaffDto staffDto) {
        var tmpStaffMember = staffRepository.findById(staffDto.getId());
        if (!tmpStaffMember.isPresent()){
            throw new NotFoundException("Staff Member Not Found");
        }else {

           staffRepository.save(mapping.convertToEntity(staffDto, Staff.class));
        }
    }

    @Override
    public List<StaffDto> getAllStaffMembers() {
        return mapping.convertToDto(staffRepository.findAll(), StaffDto.class);
    }

}
