package com.simplon.mypet.service;

import com.simplon.mypet.db.entity.User;
import com.simplon.mypet.db.repository.UserRepository;
import com.simplon.mypet.domain.user.UserDto;
import com.simplon.mypet.domain.user.UserResponse;
import com.simplon.mypet.exception.UserAlreadyExistsException;
import com.simplon.mypet.exception.UserNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponse createUser(UserDto userDto) {
        User user = userDto.toUser();
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new UserAlreadyExistsException("User with login '" + user.getUsername() + "' already exists");
        }
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException("User with email '" + user.getEmail() + "' already exists");
        }
        user = userRepository.save(user);
        return user.toUserResponse();
    }

    public List<UserResponse> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(User::toUserResponse).collect(Collectors.toList());
    }

    public UserResponse getUserById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException("User with id '" + id + "' not found");
        }
        return optionalUser.get().toUserResponse();
    }

    public UserResponse updateUser(Long id, UserDto userDto) {
        User userUpdates = userDto.toUser();
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException("User with id '" + id + "' not found");
        }
        User user = optionalUser.get();
        user.setUsername(userUpdates.getUsername());
        user.setEmail(userUpdates.getEmail());
        user.setAddress(userUpdates.getAddress());
        user.setPhoneNumber(userUpdates.getPhoneNumber());
        user = userRepository.save(user);
        return user.toUserResponse();
    }

    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException("User with id '" + id + "' not found");
        }
        userRepository.deleteById(id);
    }
}

