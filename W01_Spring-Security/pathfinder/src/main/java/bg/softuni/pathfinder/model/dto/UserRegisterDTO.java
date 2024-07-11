package bg.softuni.pathfinder.model.dto;

import bg.softuni.pathfinder.model.Level;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserRegisterDTO {
    @NotBlank
    @Size(min = 3)
    private String username;

    @NotEmpty
    @Size(min = 5)
    private String fullName;

    @NotEmpty
    @Email
    private String email;

    @Min(0)
    @Max(90)
    private Integer age;

    @NotEmpty
    @Size(min = 5)
    private String password;

    @NotEmpty
    @Size(min = 5)
    private String confirmPassword;

    private Level level;

}
