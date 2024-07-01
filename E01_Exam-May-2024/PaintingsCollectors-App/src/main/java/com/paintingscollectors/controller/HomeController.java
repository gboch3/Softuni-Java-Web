package com.paintingscollectors.controller;

import com.paintingscollectors.config.UserSession;
import com.paintingscollectors.model.dto.PaintingInfoDTO;
import com.paintingscollectors.model.entity.Painting;
import com.paintingscollectors.model.entity.StyleName;
import com.paintingscollectors.service.PaintingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
public class HomeController {

    private final UserSession userSession;
    private final PaintingService paintingService;

    public HomeController(UserSession userSession, PaintingService paintingService) {
        this.userSession = userSession;
        this.paintingService = paintingService;
    }

    @GetMapping({"/"})
    public String nonLoggedIndex() {
        return this.userSession.isLoggedIn() ? "redirect:/home" : "index";
    }

    @GetMapping({"/home"})
        public String loggedInIndex(Model model) {
        if (!this.userSession.isLoggedIn()) {
            return "redirect:/";
        } else {
            Map<StyleName, List<Painting>> allRecipes =
                    paintingService.findAllByCategory();

//            List<PaintingInfoDTO> favourites =
//                    paintingService.findFavourites(userSession.id())
//                            .stream()
//                            .map(PaintingInfoDTO::new)
//                            .toList();
//
//        }
            return "home";
        }

    }
}
