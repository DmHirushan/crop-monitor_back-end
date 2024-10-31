package lk.ijse.crop_monitor.customObj.impl;

import lk.ijse.crop_monitor.customObj.StaffResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class StaffErrorResponse implements StaffResponse {
    private int errorCode;
    private String errorMessage;
}
