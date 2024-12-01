package lk.ijse.crop_monitor.controller;

import lk.ijse.crop_monitor.dto.impl.UserDto;
import lk.ijse.crop_monitor.exception.DataPersistFailedException;
import lk.ijse.crop_monitor.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/user")
@CrossOrigin
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping
    public ResponseEntity<Void> saveUser(@RequestBody UserDto userDto) {
        logger.info("Request received to save user: {}", userDto);
        try {
            userService.saveUser(userDto);
            logger.info("User saved successfully: {}", userDto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (DataPersistFailedException e) {
            logger.error("Failed to persist user: {}", userDto, e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            logger.error("Unexpected error while saving user: {}", userDto, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
