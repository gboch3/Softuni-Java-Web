package bg.softuni.pathfinder.web;

import bg.softuni.pathfinder.model.CategoryType;
import bg.softuni.pathfinder.model.dto.RouteShortInfoDTO;
import bg.softuni.pathfinder.service.RouteService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class RoutesByCategoryController {

    private final RouteService routeService;

    public RoutesByCategoryController(RouteService routeService) {
        this.routeService = routeService;
    }

    @GetMapping("/pedestrian")
    public ModelAndView viewPedestrian() {
        ModelAndView modelAndView = new ModelAndView("pedestrian");
        List<RouteShortInfoDTO> routesByCategory = routeService.getAllByCategory(CategoryType.PEDESTRIAN);
        modelAndView.addObject("routesByCategory", routesByCategory);

        return modelAndView;
    }

    @GetMapping("/bicycle")
    public ModelAndView viewBicycle() {
        ModelAndView modelAndView = new ModelAndView("bicycle");
        List<RouteShortInfoDTO> routesByCategory = routeService.getAllByCategory(CategoryType.BICYCLE);
        modelAndView.addObject("routesByCategory", routesByCategory);

        return modelAndView;
    }

    @GetMapping("/motorcycle")
    public ModelAndView viewMotorcycle() {
        ModelAndView modelAndView = new ModelAndView("motorcycle");
        List<RouteShortInfoDTO> routesByCategory = routeService.getAllByCategory(CategoryType.MOTORCYCLE);
        modelAndView.addObject("routesByCategory", routesByCategory);

        return modelAndView;
    }

    @GetMapping("/car")
    public ModelAndView viewCar() {
        ModelAndView modelAndView = new ModelAndView("car");
        List<RouteShortInfoDTO> routesByCategory = routeService.getAllByCategory(CategoryType.CAR);
        modelAndView.addObject("routesByCategory", routesByCategory);

        return modelAndView;
    }
}
