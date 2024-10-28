package lk.ijse.crop_monitor.dto.impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EquipmentDto {
    private String equipmentId;
    private String name;
    private String equipmentType;       // Represented as a String or Enum name for simplicity
    private String status;              // Represented as a String or Enum name for availability status
    private String assignedStaffId;     // ID of the assigned staff member, if any
    private String assignedFieldCode;   // Code of the assigned field, if any
}
