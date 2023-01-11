package com.simplon.mypet.domain.user;

import com.simplon.mypet.db.entity.User;

import java.io.Serializable;

/**
 * A DTO for the {@link com.simplon.mypet.db.entity.User} entity
 */
public record UserDto(String username, String password, String email, String address,
                      String phoneNumber) implements Serializable {

    public User toUser() {
        User user = new User();
        user.setUsername(this.username);
        user.setEmail(this.email);
        user.setAddress(this.address);
        user.setPhoneNumber(this.phoneNumber);
        return user;
    }
}