package bg.softuni.mobilele.offers.service.impl;

import bg.softuni.mobilele.offers.model.dto.AddOfferDTO;
import bg.softuni.mobilele.offers.model.dto.OfferDTO;
import bg.softuni.mobilele.offers.model.entity.OfferEntity;
import bg.softuni.mobilele.offers.repository.OfferRepository;
import bg.softuni.mobilele.offers.service.OfferService;
import bg.softuni.mobilele.offers.service.exception.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfferServiceImpl implements OfferService {

    private final OfferRepository offerRepository;

    public OfferServiceImpl(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    @Override
    public OfferDTO createOffer(AddOfferDTO addOfferDTO) {
        OfferEntity offerEntity = offerRepository.save(map(addOfferDTO));
        return map(offerEntity);
//        return offerRepository.save(map(addOfferDTO)).getId();
    }

    @Override
    public void deleteOffer(Long offerId) {
        offerRepository.deleteById(offerId);
    }

    public OfferDTO getOfferById(Long id) {
        return offerRepository
                .findById(id)
                .map(OfferServiceImpl::map)
                .orElseThrow(ObjectNotFoundException::new);
    }

    public List<OfferDTO> getAllOffers() {
        return offerRepository
                .findAll()
                .stream()
                .map(OfferServiceImpl::map)
                .toList();
    }

    private static OfferEntity map(AddOfferDTO addOfferDTO) {
        return new OfferEntity()
                .setDescription(addOfferDTO.description())
                .setMileage(addOfferDTO.mileage())
                .setPrice(addOfferDTO.price())
                .setEngine(addOfferDTO.engineType());
    }

    private static OfferDTO map(OfferEntity offerEntity) {
        return new OfferDTO(
                offerEntity.getId(),
                offerEntity.getDescription(),
                offerEntity.getMileage(),
                offerEntity.getPrice(),
                offerEntity.getEngine()
        );
    }
}
