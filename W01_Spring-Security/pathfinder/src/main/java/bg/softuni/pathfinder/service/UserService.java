package bg.softuni.pathfinder.service;

import bg.softuni.pathfinder.data.UserRepository;
import bg.softuni.pathfinder.model.User;
import bg.softuni.pathfinder.model.dto.UserProfileDTO;
import bg.softuni.pathfinder.model.dto.UserRegisterDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final UserHelperService userHelperService;

    public boolean userExists(String username) {
        return this.userRepository.findByUsername(username).isPresent();
    }

    public void register(UserRegisterDTO userRegisterDTO) {
        User user = this.modelMapper.map(userRegisterDTO, User.class);

        user.setPassword(this.passwordEncoder.encode(userRegisterDTO.getPassword()));

        this.userRepository.save(user);
    }

    public UserProfileDTO getProfileData() {
        return modelMapper.map(userHelperService.getUser(), UserProfileDTO.class);
    }
}
