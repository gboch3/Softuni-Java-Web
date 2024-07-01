package com.softuni.mobilele.service.impl;

import com.softuni.mobilele.model.AddOfferDTO;
import com.softuni.mobilele.model.OfferDetailsDTO;
import com.softuni.mobilele.model.OfferSummaryDTO;
import com.softuni.mobilele.model.entity.OfferEntity;
import com.softuni.mobilele.repository.OfferRepository;
import com.softuni.mobilele.service.OfferService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfferServiceImpl implements OfferService {
    private final OfferRepository offerRepository;

    public OfferServiceImpl(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    @Override
    public long createOffer(AddOfferDTO addOfferDTO) {
        return offerRepository.save(map(addOfferDTO)).getId();
    }

    @Override
    public OfferDetailsDTO getOfferDetails(Long id) {
        return this.offerRepository
                .findById(id)
                .map(OfferServiceImpl::toOfferDetails)
                .orElseThrow();
    }

    @Override
    public void deleteOffer(Long id) {
        offerRepository.deleteById(id);
    }

    @Override
    public List<OfferSummaryDTO> getAllOffersSummary() {
        return offerRepository
                .findAll()
                .stream()
                .map(OfferServiceImpl::toOfferSummary)
                .toList();
    }

    private static OfferSummaryDTO toOfferSummary(OfferEntity offerEntity) {
        // todo use mapping library
        return new OfferSummaryDTO(offerEntity.getId(),
                offerEntity.getDescription(),
                offerEntity.getMileage(),
                offerEntity.getEngine());
    }

    private static OfferDetailsDTO toOfferDetails(OfferEntity offerEntity) {
        // todo use mapping library
        return new OfferDetailsDTO(offerEntity.getId(),
                offerEntity.getDescription(),
                offerEntity.getMileage(),
                offerEntity.getEngine());
    }

    private static OfferEntity map(AddOfferDTO addOfferDTO) {
        // todo - use mapped (e.g. ModelMapper)
        return new OfferEntity()
                .setDescription(addOfferDTO.description())
                .setEngine(addOfferDTO.engineType())
                .setMileage(addOfferDTO.mileage());
    }

}
