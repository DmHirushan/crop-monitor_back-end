package lk.ijse.crop_monitor.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.crop_monitor.dto.impl.UserDto;
import lk.ijse.crop_monitor.entity.User;
import lk.ijse.crop_monitor.repository.UserRepository;
import lk.ijse.crop_monitor.service.UserService;
import lk.ijse.crop_monitor.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
