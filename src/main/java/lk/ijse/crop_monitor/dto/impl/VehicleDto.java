package lk.ijse.crop_monitor.dto.impl;

import lk.ijse.crop_monitor.customObj.VehicleResponse;
import lk.ijse.crop_monitor.dto.SuperDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class VehicleDto implements SuperDto, VehicleResponse {
    private String vehicleCode;
    private String licensePlateNumber;
    private String vehicleCategory;
    private String fuelType;
    private String status;
    private String remarks;
    private StaffDto staff; // Reference to the staff member's ID
}
