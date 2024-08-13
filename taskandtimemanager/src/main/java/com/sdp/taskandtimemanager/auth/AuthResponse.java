package com.sdp.taskandtimemanager.auth;

import com.sdp.taskandtimemanager.model.Users.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {

    private String token;
    private Role role;

}