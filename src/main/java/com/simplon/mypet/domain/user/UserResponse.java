package com.simplon.mypet.domain.user;

import com.simplon.mypet.db.entity.User;

public record UserResponse(Long id, String username, String email, String address, String phoneNumber,
                           int numberOfAdoptions) {

    public static UserResponse fromUser(User user) {
        return new UserResponse(user.getId(), user.getUsername(), user.getEmail(), user.getAddress(), user.getPhoneNumber(), user.getAnimals().size());
    }
}
