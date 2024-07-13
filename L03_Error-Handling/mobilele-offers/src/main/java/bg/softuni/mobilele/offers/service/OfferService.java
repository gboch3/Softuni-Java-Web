package bg.softuni.mobilele.offers.service;

import bg.softuni.mobilele.offers.model.dto.AddOfferDTO;
import bg.softuni.mobilele.offers.model.dto.OfferDTO;

import java.util.List;

public interface OfferService {

  Long createOffer(AddOfferDTO addOfferDTO);

  void deleteOffer(Long orderId);

  OfferDTO getOfferById(Long id);

  List<OfferDTO> getAllOffers();
}
