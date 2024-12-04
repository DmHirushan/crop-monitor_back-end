package lk.ijse.crop_monitor.dto.impl;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lk.ijse.crop_monitor.customObj.EquipmentResponse;
import lk.ijse.crop_monitor.dto.SuperDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EquipmentDto implements SuperDto, EquipmentResponse {
    @NotBlank(message = "Equipment ID is mandatory")
    @Size(max = 50, message = "Equipment ID must not exceed 50 characters")
    private String equipmentId;

    @NotBlank(message = "Name is mandatory")
    @Size(max = 100, message = "Name must not exceed 100 characters")
    private String name;

    @NotBlank(message = "Equipment type is mandatory")
    @Size(max = 50, message = "Equipment type must not exceed 50 characters")
    private String equipmentType;

    @NotBlank(message = "Status is mandatory")
    @Pattern(regexp = "^(AVAILABLE|NOTAVAILABLE)$",
            message = "Status must be either 'AVAILABLE' or 'NOTAVAILABLE'")
    private String status;

    @Size(max = 50, message = "Assigned Staff ID must not exceed 50 characters")
    private String assignedStaffId;

    @Size(max = 50, message = "Assigned Field Code must not exceed 50 characters")
    private String assignedFieldCode;
}
