package com.sdp.taskandtimemanager.auth;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

//RegisterDTO

@Data
@Builder
@AllArgsConstructor
public class RegisterRequest {
    private Long userid;
    private String name;
    private String password;
    private String email;
    private String role;
    // private LocalDate dob;
    private String contact;
}