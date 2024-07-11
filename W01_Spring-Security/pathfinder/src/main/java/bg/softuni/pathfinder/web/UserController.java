package bg.softuni.pathfinder.web;

import bg.softuni.pathfinder.model.Level;
import bg.softuni.pathfinder.model.dto.UserLoginDTO;
import bg.softuni.pathfinder.model.dto.UserRegisterDTO;
import bg.softuni.pathfinder.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @ModelAttribute("registerData")
    public UserRegisterDTO registerDTO() {
        return new UserRegisterDTO();
    }

    @GetMapping("/register")
    public String viewRegister(Model model) {
        model.addAttribute("levels", Level.values());
        return "register";
    }

    @PostMapping("/register")
    public String doRegister(
            @Valid UserRegisterDTO registerData,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes
    ) {

        if (userService.userExists(registerData.getUsername())) {
            bindingResult.rejectValue("username", "UsernameExists", "");
        }

        if (bindingResult.hasErrors()
                || !registerData.getPassword().equals(registerData.getConfirmPassword())
        ) {
            redirectAttributes.addFlashAttribute("registerData", registerData);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.registerData", bindingResult);

            return "redirect:/users/register";
        }

        userService.register(registerData);

        return "redirect:/users/login";
    }

    @GetMapping("/login")
    public ModelAndView viewLogin() {
        ModelAndView modelAndView = new ModelAndView("login");
        modelAndView.addObject("loginData", new UserLoginDTO());

        return modelAndView;
    }

    @GetMapping("/login-error")
    public ModelAndView viewLoginError() {
        ModelAndView modelAndView = new ModelAndView("login");

        modelAndView.addObject("showErrorMessage", true);
        modelAndView.addObject("loginData", new UserLoginDTO());

        return modelAndView;
    }

    @GetMapping("/profile")
    public ModelAndView viewProfile() {
        ModelAndView modelAndView = new ModelAndView("profile");

        modelAndView.addObject("profileData", userService.getProfileData());

        return modelAndView;
    }

}
