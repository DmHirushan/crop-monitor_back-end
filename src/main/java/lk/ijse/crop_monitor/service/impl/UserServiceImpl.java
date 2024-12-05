package lk.ijse.crop_monitor.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.crop_monitor.customObj.UserResponse;
import lk.ijse.crop_monitor.dto.impl.UserDto;
import lk.ijse.crop_monitor.entity.User;
import lk.ijse.crop_monitor.exception.NotFoundException;
import lk.ijse.crop_monitor.repository.UserRepository;
import lk.ijse.crop_monitor.service.UserService;
import lk.ijse.crop_monitor.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final Mapping mapping;

    @Override
    public void saveUser(UserDto userDto) {
        userRepository.save(mapping.convertToEntity(userDto, User.class));
    }

    @Override
    public void updateUser(UserDto userDto) {

    }

    @Override
    public void deleteUser(String userId) {

    }

    @Override
    public UserResponse getSelectedUser(String userId) {
        return null;
    }

    @Override
    public List<UserDto> getAllUsers() {
        return null;
    }

    @Override
    public UserDetailsService userDetailsService() {
        return email ->
                userRepository.findByEmail(email)
                        .orElseThrow(()-> new NotFoundException("User Not found"));
    }
}
