package bg.softuni.pathfinder.model.dto;

import bg.softuni.pathfinder.model.Level;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserProfileDTO {
    private String username;
    private String fullName;
    private int age;
    private Level level;
}
