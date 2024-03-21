package com.getarrays.userservice.service;


import com.getarrays.userservice.domain.User;

public interface UserService {

    User saveUser(User user);
    Boolean verifyToken(String token);
}
