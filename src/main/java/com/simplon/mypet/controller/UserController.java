package com.simplon.mypet.controller;

import com.simplon.mypet.domain.user.UserDto;
import com.simplon.mypet.domain.user.UserResponse;
import com.simplon.mypet.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/users")
public class UserController {

    private final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody UserDto userDto) {
        LOGGER.info("Creating user: {}", userDto);
        UserResponse createdUser = userService.createUser(userDto);
        LOGGER.info("Created user: {}", createdUser);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        LOGGER.info("Retrieving all users");
        List<UserResponse> users = userService.getAllUsers();
        LOGGER.info("Retrieved {} users", users.size());
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id) {
        LOGGER.info("Retrieving user with id: {}", id);
        UserResponse user = userService.getUserById(id);
        LOGGER.info("Retrieved user: {}", user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
        LOGGER.info("Updating user with id: {}", id);
        UserResponse updatedUser = userService.updateUser(id, userDto);
        LOGGER.info("Updated user: {}", updatedUser);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        LOGGER.info("Deleting user with id: {}", id);
        userService.deleteUser(id);
        LOGGER.info("Deleted user with id: {}", id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

