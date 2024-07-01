package com.softuni.mobilele.service.impl;

import com.softuni.mobilele.model.UserRegistrationDTO;
import com.softuni.mobilele.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public void registerUser(UserRegistrationDTO userRegistration) {
        // TODO:
        System.out.println("The user received is: " + userRegistration);
    }
}
