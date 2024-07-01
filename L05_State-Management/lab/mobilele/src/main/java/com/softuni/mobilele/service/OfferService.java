package com.softuni.mobilele.service;

import com.softuni.mobilele.model.AddOfferDTO;
import com.softuni.mobilele.model.OfferDetailsDTO;
import com.softuni.mobilele.model.OfferSummaryDTO;

import java.util.List;

public interface OfferService {

    long createOffer(AddOfferDTO addOfferDTO);

    OfferDetailsDTO getOfferDetails(Long id);

    void deleteOffer(Long id);

    List<OfferSummaryDTO> getAllOffersSummary();
}
