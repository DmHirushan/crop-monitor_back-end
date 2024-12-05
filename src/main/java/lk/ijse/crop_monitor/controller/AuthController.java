package lk.ijse.crop_monitor.controller;

import lk.ijse.crop_monitor.dto.impl.UserDto;
import lk.ijse.crop_monitor.jwtModels.JwtAuthResponse;
import lk.ijse.crop_monitor.jwtModels.SignIn;
import lk.ijse.crop_monitor.service.AuthenticationService;
import lk.ijse.crop_monitor.util.AppUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
@CrossOrigin("*")
public class AuthController {
    private final AuthenticationService authenticationService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping(value = "signup",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JwtAuthResponse> signUp(@RequestBody UserDto userDto){
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        return ResponseEntity.ok(authenticationService.signUp(userDto));
    }
    @PostMapping(value = "signin")
    public ResponseEntity<JwtAuthResponse> signIn(@RequestBody SignIn signIn) {
        return ResponseEntity.ok(authenticationService.signIn(signIn));
    }
    @PostMapping("refresh")
    public ResponseEntity<JwtAuthResponse> refreshToken (@RequestParam ("refreshToken") String refreshToken) {
        return ResponseEntity.ok(authenticationService.refreshToken(refreshToken));
    }
}
