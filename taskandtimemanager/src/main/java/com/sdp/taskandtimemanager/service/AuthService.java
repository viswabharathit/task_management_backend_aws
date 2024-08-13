package com.sdp.taskandtimemanager.service;

import com.sdp.taskandtimemanager.auth.LoginRequest;
// import com.sdp.taskandtimemanager.auth.LoginResponse;
import com.sdp.taskandtimemanager.auth.RegisterRequest;

public interface AuthService {
    String register(RegisterRequest registerRequest);

    String login(LoginRequest loginRequest);

    String createAdmin();
}