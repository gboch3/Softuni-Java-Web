package bg.softuni.mobilele.offers.web;

import bg.softuni.mobilele.offers.model.dto.AddOfferDTO;
import bg.softuni.mobilele.offers.model.dto.OfferDTO;
import bg.softuni.mobilele.offers.model.enums.EngineTypeEnum;
import bg.softuni.mobilele.offers.service.OfferService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/offers")
public class OfferController {

    private static final Logger LOGGER = LoggerFactory.getLogger(OfferController.class);
    private final OfferService offerService;

    public OfferController(OfferService offerService) {
        this.offerService = offerService;
    }

    @ModelAttribute("allEngineTypes")
    public EngineTypeEnum[] allEngineTypes() {
        return EngineTypeEnum.values();
    }

/*
  @GetMapping("/add")
  public String newOffer(Model model) {

    if (!model.containsAttribute("addOfferDTO")) {
      model.addAttribute("addOfferDTO", AddOfferDTO.empty());
    }

    return "offer-add";
  }
*/

/*
  @PostMapping("add")
  public String createOffer(
      @Valid AddOfferDTO addOfferDTO,
      BindingResult bindingResult,
      RedirectAttributes rAtt) {

    if(bindingResult.hasErrors()){
      rAtt.addFlashAttribute("addOfferDTO", addOfferDTO);
      rAtt.addFlashAttribute("org.springframework.validation.BindingResult.addOfferDTO", bindingResult);
      return "redirect:/offers/add";
    }


    long newOfferId = offerService.createOffer(addOfferDTO);

    return "redirect:/offers/" + newOfferId;
  }
*/

    @GetMapping("/{id}")
    public ResponseEntity<OfferDTO> findById(@PathVariable("id") Long id) {
        return ResponseEntity
                .ok(offerService.getOfferById(id));
    }


    @GetMapping
    public ResponseEntity<List<OfferDTO>> getAllOffers() {
        return ResponseEntity
                .ok(offerService.getAllOffers());
    }

    @PostMapping
    public ResponseEntity<Long> createOffer(@RequestBody AddOfferDTO addOfferDTO) throws InterruptedException {
        LOGGER.info("Going to create an offer {}", addOfferDTO);
        Long id = offerService.createOffer(addOfferDTO);

        return ResponseEntity
                .ok(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<OfferDTO> deleteById(@PathVariable("id") Long id) {
        offerService.deleteOffer(id);

        return ResponseEntity
                .ok()
                .build();
    }
}
