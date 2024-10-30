package lk.ijse.crop_monitor.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.crop_monitor.dto.impl.StaffDto;
import lk.ijse.crop_monitor.entity.Staff;
import lk.ijse.crop_monitor.exception.DataPersistFailedException;
import lk.ijse.crop_monitor.repository.StaffRepository;
import lk.ijse.crop_monitor.service.StaffService;
import lk.ijse.crop_monitor.util.AppUtil;
import lk.ijse.crop_monitor.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}
