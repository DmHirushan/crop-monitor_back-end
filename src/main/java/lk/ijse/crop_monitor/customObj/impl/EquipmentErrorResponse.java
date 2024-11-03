package lk.ijse.crop_monitor.customObj.impl;

import lk.ijse.crop_monitor.customObj.EquipmentResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EquipmentErrorResponse implements EquipmentResponse {
    private int errorCode;
    private String errorMessage;
}
