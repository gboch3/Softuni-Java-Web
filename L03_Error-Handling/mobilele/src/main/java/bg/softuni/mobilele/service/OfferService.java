package bg.softuni.mobilele.service;

import bg.softuni.mobilele.model.dto.AddOfferDTO;
import bg.softuni.mobilele.model.dto.OfferDetailsDTO;
import bg.softuni.mobilele.model.dto.OfferSummaryDTO;
import java.util.List;

public interface OfferService {

  Long createOffer(AddOfferDTO addOfferDTO);

  void deleteOffer(long orderId);

  OfferDetailsDTO getOfferDetails(Long id);

  List<OfferSummaryDTO> getAllOffersSummary();
}
