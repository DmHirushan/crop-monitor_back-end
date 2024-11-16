package lk.ijse.crop_monitor.service;

import lk.ijse.crop_monitor.dto.impl.UserDto;
import lk.ijse.crop_monitor.jwtModels.JwtAuthResponse;
import lk.ijse.crop_monitor.jwtModels.SignIn;

public interface AuthenticationService {
    JwtAuthResponse signIn(SignIn signIn);
    JwtAuthResponse signUp(UserDto signUp);
    JwtAuthResponse refreshToken(String accessToken);
}
