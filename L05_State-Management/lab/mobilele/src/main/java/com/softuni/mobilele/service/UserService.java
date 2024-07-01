package com.softuni.mobilele.service;

import com.softuni.mobilele.model.UserLoginDTO;
import com.softuni.mobilele.model.UserRegistrationDTO;

public interface UserService {

    void registerUser(UserRegistrationDTO userRegistration);

    boolean login(UserLoginDTO userLoginDTO);

    void logout();
}
