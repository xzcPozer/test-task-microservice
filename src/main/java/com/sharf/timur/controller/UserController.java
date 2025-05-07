package com.sharf.timur.controller;

import com.sharf.timur.dto.user.UserRequest;
import com.sharf.timur.dto.user.UserResponse;
import com.sharf.timur.service.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}users")
public class UserController {

    private final UserService service;

    @PostMapping
    public ResponseEntity<Long> createUser(
            @Valid @RequestBody UserRequest request
    ) {
        log.info("sending request to create user with email: {}", request.email());
        Long userId = service.addUser(request);
        log.info("created user with id: {}", userId);
        return ResponseEntity.ok(userId);
    }

    @GetMapping("{id}")
    public ResponseEntity<UserResponse> getUserById(
            @PathVariable Long id
    ) {
        log.info("sending request to get user with id: {}", id);
        UserResponse response = service.getUser(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("{id}")
    public ResponseEntity<Long> updateUserById(
            @PathVariable Long id,
            @RequestBody UserRequest request
    ) {
        log.info("sending request to update user with id: {}", id);
        Long userId = service.updateUser(request, id);
        log.info("updated user with id: {}", userId);
        return ResponseEntity.ok(userId);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteUserById(
            @PathVariable Long id
    ) {
        log.info("sending request to delete user with id: {}", id);
        service.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
