package lk.ijse.crop_monitor.dto.impl;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lk.ijse.crop_monitor.customObj.VehicleResponse;
import lk.ijse.crop_monitor.dto.SuperDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class VehicleDto implements SuperDto, VehicleResponse {
    @NotBlank(message = "Vehicle code is mandatory")
    @Size(max = 50, message = "Vehicle code must not exceed 50 characters")
    private String vehicleCode;

    @NotBlank(message = "License plate number is mandatory")
    @Size(max = 15, message = "License plate number must not exceed 15 characters")
    private String licensePlateNumber;

    @NotBlank(message = "Vehicle category is mandatory")
    @Size(max = 50, message = "Vehicle category must not exceed 50 characters")
    private String vehicleCategory;

    @NotBlank(message = "Fuel type is mandatory")
    @Pattern(regexp = "PETROL|DIESEL|ELECTRIC|HYBRID",
            message = "Fuel type must be PETROL, DIESEL, ELECTRIC, or HYBRID")
    private String fuelType;

    @NotBlank(message = "Status is mandatory")
    @Pattern(regexp = "AVAILABLE|NOTAVAILABLE|MAINTENANCE",
            message = "Status must be AVAILABLE, NOTAVAILABLE, or MAINTENANCE")
    private String status;

    @Size(max = 255, message = "Remarks must not exceed 255 characters")
    private String remarks;

    @NotBlank(message = "Staff ID is mandatory")
    @Size(max = 50, message = "Staff ID must not exceed 50 characters")
    private String staffId;// Reference to the staff member's ID
}
