package bg.softuni.pathfinder.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Random;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index(Model model) {
        int sofiaTemp = new Random().nextInt(10,30);
        int sozopolTemp = new Random().nextInt(10,30);
        model.addAttribute("sofiaTemperature", sofiaTemp);
        model.addAttribute("sozopolTemperature", sozopolTemp);

        return "index";
    }

    @GetMapping("/about")
    public ModelAndView index() {
        return new ModelAndView("about");
    }
}
