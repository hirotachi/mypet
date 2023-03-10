package com.simplon.mypet.service;

import com.simplon.mypet.db.entity.User;
import com.simplon.mypet.db.repository.UserRepository;
import com.simplon.mypet.domain.user.UserDto;
import com.simplon.mypet.domain.user.UserResponse;
import com.simplon.mypet.exception.AlreadyExistsException;
import com.simplon.mypet.exception.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponse createUser(UserDto userDto) {
        User user = userDto.toUser();
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new AlreadyExistsException("User with login '" + user.getUsername() + "' already exists");
        }
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new AlreadyExistsException("User with email '" + user.getEmail() + "' already exists");
        }
        user = userRepository.save(user);
        return user.toUserResponse();
    }

    public Page<UserResponse> getAllUsers(Pageable pageable) {
        Page<User> users = userRepository.findAll(pageable);
        return users.map(User::toUserResponse);
    }

    public UserResponse getUserById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            throw new NotFoundException("User with id '" + id + "' not found");
        }
        return optionalUser.get().toUserResponse();
    }

    public UserResponse updateUser(Long id, UserDto userDto) {
        User userUpdates = userDto.toUser();
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            throw new NotFoundException("User with id '" + id + "' not found");
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
            throw new NotFoundException("User with id '" + id + "' not found");
        }
        userRepository.deleteById(id);
    }
}

