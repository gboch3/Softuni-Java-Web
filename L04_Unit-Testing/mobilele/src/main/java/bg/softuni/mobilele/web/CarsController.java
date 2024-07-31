package bg.softuni.mobilele.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CarsController {

    @GetMapping("/brands/all")
    public String viewBrands() {
        return "brands";
    }
}
