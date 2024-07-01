package com.softuni.mobilele.service.impl;

import com.softuni.mobilele.model.UserLoginDTO;
import com.softuni.mobilele.model.UserRegistrationDTO;
import com.softuni.mobilele.model.entity.UserEntity;
import com.softuni.mobilele.repository.UserRepository;
import com.softuni.mobilele.service.CurrentUser;
import com.softuni.mobilele.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final CurrentUser currentUser;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder, CurrentUser currentUser) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.currentUser = currentUser;
    }

    @Override
    public void registerUser(UserRegistrationDTO userRegistration) {
        userRepository.save(map(userRegistration));
    }

    @Override
    public boolean login(UserLoginDTO userLoginDTO) {

        UserEntity userEntity = userRepository
                .findByEmail(userLoginDTO.email())
                .orElse(null);

        if (userLoginDTO.password() == null ||
                userEntity == null ||
                userEntity.getPassword() == null) {
            return false;
        }

        boolean success = passwordEncoder.matches(userLoginDTO.password(), userEntity.getPassword());

        if (success) {
            currentUser.setLoggedIn(true);
            currentUser.setFullName(userEntity.getFirstName() + " " + userEntity.getLastName());
        } else {
            currentUser.clean();
        }

        return false;
    }

    @Override
    public void logout() {
        currentUser.clean();
    }

    private UserEntity map(UserRegistrationDTO userRegistrationDTO) {
        UserEntity mappedEntity = modelMapper.map(userRegistrationDTO, UserEntity.class);
        mappedEntity.setPassword(passwordEncoder.encode(userRegistrationDTO.getPassword()));

        return mappedEntity;
    }
}
