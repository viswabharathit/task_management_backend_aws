package com.sdp.taskandtimemanager.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

//LoginDTO

@Data
@Builder
@AllArgsConstructor
public class LoginRequest {
    private String email;
    private String password;
}
