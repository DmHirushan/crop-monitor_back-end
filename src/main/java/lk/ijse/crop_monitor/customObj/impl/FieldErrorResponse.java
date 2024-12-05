package lk.ijse.crop_monitor.customObj.impl;

import lk.ijse.crop_monitor.customObj.FieldResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FieldErrorResponse implements FieldResponse {
    private int errorCode;
    private String errorMessage;
}
