package lk.ijse.crop_monitor.customObj.impl;

import lk.ijse.crop_monitor.customObj.CropDetailResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CropDetailErrorResponse implements CropDetailResponse {
    private int errorCode;
    private String errorMessage;
}
