package bg.softuni.mobilele.service.impl;

import bg.softuni.mobilele.model.dto.AddOfferDTO;
import bg.softuni.mobilele.model.dto.OfferDetailsDTO;
import bg.softuni.mobilele.model.dto.OfferSummaryDTO;
import bg.softuni.mobilele.model.entity.OfferEntity;
import bg.softuni.mobilele.service.ExRateService;
import bg.softuni.mobilele.service.OfferService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class OfferServiceImpl implements OfferService {

    private Logger LOGGER = LoggerFactory.getLogger(OfferServiceImpl.class);
    private final ExRateService exRateService;
    private final RestClient offerRestClient;

    public OfferServiceImpl(
            ExRateService exRateService,
            @Qualifier("offersRestClient") RestClient offerRestClient
    ) {
        this.exRateService = exRateService;
        this.offerRestClient = offerRestClient;
    }

    @Override
    public Long createOffer(AddOfferDTO addOfferDTO){
        LOGGER.info("Creating new offer...");

        // todo - fix baseUrl.
        return offerRestClient
                .post()
                .uri("http://localhost:8081/offers")
                .body(addOfferDTO)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
    }

    @Override
    public void deleteOffer(long id) {
        offerRestClient
                .delete()
                .uri("http://localhost:8081/offers/{id}", id)
                .retrieve()
                .toBodilessEntity();
    }

    @Override
    public OfferDetailsDTO getOfferDetails(Long id) {

        return offerRestClient
                .get()
                .uri("http://localhost:8081/offers/{id}", id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(OfferDetailsDTO.class);
    }

    @Override
    public List<OfferSummaryDTO> getAllOffersSummary() {
        LOGGER.info("Get all offers...");

        return offerRestClient
                .get()
                .uri("http://localhost:8081/offers")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
    }

    private OfferDetailsDTO toOfferDetails(OfferEntity offerEntity) {
        return new OfferDetailsDTO(offerEntity.getId(),
                offerEntity.getDescription(),
                offerEntity.getMileage(),
                offerEntity.getPrice(),
                offerEntity.getEngine(),
                exRateService.allSupportedCurrencies());
    }

}
