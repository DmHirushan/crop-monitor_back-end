package lk.ijse.crop_monitor.service;

import lk.ijse.crop_monitor.dto.impl.UserDto;
import lk.ijse.crop_monitor.entity.User;

public interface UserService {
    void saveUser(UserDto userDto);
}
