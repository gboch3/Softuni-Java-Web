package bg.softuni.pathfinder.web;

import bg.softuni.pathfinder.model.CategoryType;
import bg.softuni.pathfinder.model.Level;
import bg.softuni.pathfinder.model.dto.AddRouteDTO;
import bg.softuni.pathfinder.model.dto.RouteInfoDTO;
import bg.softuni.pathfinder.model.dto.RouteShortInfoDTO;
import bg.softuni.pathfinder.service.RouteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class RouteController {
    private final RouteService routeService;

    @ModelAttribute("addRouteData")
    public AddRouteDTO addRouteData() {
        return new AddRouteDTO();
    }

    @ModelAttribute("routeData")
    public RouteInfoDTO routeData() {
        return new RouteInfoDTO();
    }

    @GetMapping("/routes")
    public String viewRoutes(Model model) {
        List<RouteShortInfoDTO> routes = routeService.getAll();

        model.addAttribute("allRoutes", routes);

        return "routes";
    }

    @GetMapping("/add-route")
    public ModelAndView addRoute() {

        ModelAndView modelAndView = new ModelAndView("add-route");

/*
        if (!userHelperService.isAuthenticated()) {
            modelAndView.setViewName("redirect:/users/login");

            return modelAndView;
        }
*/

        modelAndView.addObject("route", new RouteShortInfoDTO());
        modelAndView.addObject("levels", Level.values());
        modelAndView.addObject("categoryTypes", CategoryType.values());

        return modelAndView;
    }

    @PostMapping("/add-route")
    public String doAddRoute(
            @Valid AddRouteDTO data,
            @RequestParam("gpxCoordinates") MultipartFile file,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes
    ) throws IOException {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("addRouteData", addRouteData());
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addRouteData", bindingResult);

            return "redirect:/add-route";
        }

        boolean success = routeService.add(data, file);

        if (success) return "redirect:/route-details/" + data.getId();

        return "redirect:/add-route";
    }


    @GetMapping("/route-details/{routeId}")
    public ModelAndView viewRouteDetails(@PathVariable Long routeId) {
        ModelAndView modelAndView = new ModelAndView("route-details");
        RouteInfoDTO route = routeService.getRoute(routeId);

        if (route == null) {
            modelAndView.setViewName("redirect:/routes");
        }

        modelAndView.addObject("routeData", route);

        return modelAndView;
    }
}
