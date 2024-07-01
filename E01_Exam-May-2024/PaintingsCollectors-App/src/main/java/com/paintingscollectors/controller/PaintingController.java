package com.paintingscollectors.controller;

import com.paintingscollectors.config.UserSession;
import com.paintingscollectors.model.dto.AddPaintingDTO;
import com.paintingscollectors.service.PaintingService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class PaintingController {

    private final UserSession userSession;
    private final PaintingService paintingService;

    public PaintingController(UserSession userSession, PaintingService paintingService) {
        this.userSession = userSession;
        this.paintingService = paintingService;
    }

    @ModelAttribute("paintingData")
    public AddPaintingDTO recipeData() {
        return new AddPaintingDTO();
    }

    @GetMapping("/add-painting")
    public String addPainting() {
        if (!userSession.isLoggedIn()) {
            return "redirect:/";
        }

        return "add-painting";
    }

    @PostMapping("/add-painting")
    public String doAddPainting(
            @Valid AddPaintingDTO data,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes
    ) {
        if (!userSession.isLoggedIn()) {
            return "redirect:/";
        }

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("paintingData", data);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.paintingData", bindingResult);

            return "redirect:/add-painting";
        }

        boolean isSuccess = paintingService.create(data);

        if (!isSuccess) {
            redirectAttributes.addFlashAttribute("paintingData", data);

            return "redirect:/add-painting";
        }

        return "redirect:/home";
    }

    @PostMapping("/add-to-favourites/{paintingId}")
    public String addToFavourites(@PathVariable long paintingId) {
        if (!userSession.isLoggedIn()) {
            return "redirect:/";
        }

        paintingService.addToFavourites(userSession.id(), paintingId);

        return "redirect:/home";
    }
}
