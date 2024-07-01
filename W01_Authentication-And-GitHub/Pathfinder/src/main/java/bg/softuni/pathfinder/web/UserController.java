package bg.softuni.pathfinder.web;

import bg.softuni.pathfinder.model.Level;
import bg.softuni.pathfinder.web.dto.UserRegisterDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {

    @GetMapping("users/register")
    public String viewRegister(Model model) {
        model.addAttribute("registerData", new UserRegisterDTO());
//        model.addAttribute("levels", Level.values());

        return "register";
    }

    @PostMapping("users/register")
    public String doRegister(
            UserRegisterDTO data,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes
    ) {
//        if (bindingResult.hasErrors()) {
//            redirectAttributes.addFlashAttribute("registerData", data);
////            redirectAttributes.addAttribute("org.springframework.validation.BindingResult.UserRegisterDTO", bindingResult);
//
//            // handle errors
//            return "register";
//        }

        return "redirect:/users/login";
    }

    @GetMapping("users/login")
    public ModelAndView viewLogin() {
        ModelAndView modelAndView = new ModelAndView("login");

        return modelAndView;
    }

}
