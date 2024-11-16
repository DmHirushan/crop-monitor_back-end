package lk.ijse.crop_monitor.service;

import lk.ijse.crop_monitor.customObj.UserResponse;
import lk.ijse.crop_monitor.dto.impl.UserDto;
import lk.ijse.crop_monitor.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);
    void updateUser(UserDto userDto);
    void deleteUser(String userId);
    UserResponse getSelectedUser(String userId);
    List<UserDto> getAllUsers();
    UserDetailsService userDetailsService();
}
