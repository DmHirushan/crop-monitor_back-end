package lk.ijse.crop_monitor.dto.impl;

import lk.ijse.crop_monitor.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDto {
    private String email;
    private Role role;    // Represented as a String or Enum name for the Role
    private String password;
}
