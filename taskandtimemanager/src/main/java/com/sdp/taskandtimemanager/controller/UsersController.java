package com.sdp.taskandtimemanager.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.http.HttpStatus.OK;
import com.sdp.taskandtimemanager.auth.LoginRequest;
import com.sdp.taskandtimemanager.auth.RegisterRequest;
import com.sdp.taskandtimemanager.model.Users;
import com.sdp.taskandtimemanager.service.UsersService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users/auth")
@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
@Tag(name = "Authentication", description = "Endpoints for user authentication")
public class UsersController {

    @Autowired
    private UsersService service;

    @GetMapping("/findAll")
    public List<Users> findAll() {
        return service.findAllUsers();
    }

    @GetMapping("/findById/{userId}")
    public Users findById(@PathVariable Long userId) {
        return service.findUserById(userId);
    }

    // getbyemail
    @GetMapping("/findByEmail")
    public ResponseEntity<Long> findUserIdByEmail(@RequestParam String email) {
        Long userId = service.findUserIdByEmail(email);
        if (userId != null) {
            return ResponseEntity.ok(userId);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/register")
    @Operation(summary = "Register a new user", description = "Allows users to register by providing necessary registration details.")
    public ResponseEntity<?> register(
            @Parameter(description = "Registration details of the user") @RequestBody RegisterRequest registerRequest) {
        return new ResponseEntity<>(service.register(registerRequest), OK);
    }

    @PostMapping("/register/pm")
    @Operation(summary = "Register a manager", description = "Allows Project Managers to register by providing necessary registration details.")
    public ResponseEntity<?> registerpm(
            @Parameter(description = "Registration details of the Project Manager") @RequestBody RegisterRequest registerRequest) {
        return new ResponseEntity<>(service.registerManager(registerRequest), OK);
    }

    @PostMapping("/login")
    @Operation(summary = "Authenticate user", description = "Allows users to authenticate by providing valid login credentials.")
    public ResponseEntity<?> login(
            @Parameter(description = "Login credentials of the user") @RequestBody LoginRequest loginRequest) {
        return new ResponseEntity<>(service.login(loginRequest), OK);
    }

    @PutMapping("/update/{userId}")
    public Users update(@PathVariable Long userId, @RequestBody Users user) {
        return service.updateUser(userId, user);
    }

    @PatchMapping("/updateSpecific/{userId}")
    public Users patch(@PathVariable Long userId, @RequestBody Users user) {
        return service.patchUser(userId, user);
    }

    @DeleteMapping("delete/{userId}")
    public void delete(@PathVariable Long userId) {
        service.deleteUser(userId);
    }

    @GetMapping("/profile")
    public ResponseEntity<Users> getUserProfile() {
        Long userId = service.getCurrentManagerId(); // Fetches user ID from the security context
        Users user = service.findUserById(userId);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/profile")
    public ResponseEntity<Users> updateUserProfile(@RequestBody Users updatedUser) {
        Long userId = service.getCurrentManagerId();
        Users updatedProfile = service.updateUser(userId, updatedUser);
        return ResponseEntity.ok(updatedProfile);
    }

    @PutMapping("/profile/password")
    public ResponseEntity<String> updateUserPassword(@RequestBody Map<String, String> passwordMap) {
        Long userId = service.getCurrentManagerId();
        String oldPassword = passwordMap.get("oldPassword");
        String newPassword = passwordMap.get("newPassword");

        boolean isPasswordUpdated = service.updatePassword(userId, oldPassword, newPassword);
        if (isPasswordUpdated) {
            return ResponseEntity.ok("Password updated successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Incorrect old password.");
        }
    }

}
