package lk.ijse.crop_monitor.customObj.impl;

import lk.ijse.crop_monitor.customObj.UserResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserErrorResponse implements UserResponse {
    private int errorCode;
    private String errorMessage;
}
