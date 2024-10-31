package lk.ijse.crop_monitor.service;

import lk.ijse.crop_monitor.customObj.StaffResponse;
import lk.ijse.crop_monitor.dto.impl.CropDto;
import lk.ijse.crop_monitor.dto.impl.StaffDto;

import java.util.List;

public interface StaffService {
    void saveStaffMember(StaffDto staffDto);
    StaffResponse getStaffMember(String id);
    void deleteStaffMember(String id);
    void updateStaffMember(StaffDto staffDto);
    List<StaffDto> getAllStaffMembers();
}
