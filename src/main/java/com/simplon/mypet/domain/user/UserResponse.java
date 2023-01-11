package com.simplon.mypet.domain.user;

import com.simplon.mypet.db.entity.User;

public class UserResponse {
    private final Long id;
    private final String username;
    private final String email;
    private final String address;
    private final String phoneNumber;
    private final int numberOfAdoptions;

    public UserResponse(Long id, String username, String email, String address, String phoneNumber, int numberOfAdoptions) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.numberOfAdoptions = numberOfAdoptions;
    }

    public static UserResponse fromUser(User user) {
        return new UserResponse(user.getId(), user.getUsername(), user.getEmail(), user.getAddress(), user.getPhoneNumber(), user.getAnimals().size());
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public int getNumberOfAdoptions() {
        return numberOfAdoptions;
    }
}
