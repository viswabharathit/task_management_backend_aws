package com.sdp.taskandtimemanager.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sdp.taskandtimemanager.service.UsersService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/admin/default")
@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
public class AdminController {

    private final UsersService authService;

    @PostMapping
    public ResponseEntity<?> createAdmin() {
        try {
            return new ResponseEntity<>(authService.createAdmin(), HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

}
