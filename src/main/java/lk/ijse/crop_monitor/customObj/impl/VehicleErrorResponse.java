package lk.ijse.crop_monitor.customObj.impl;

import lk.ijse.crop_monitor.customObj.VehicleResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class VehicleErrorResponse implements VehicleResponse {
    private int errorCode;
    private String errorMessage;
}
