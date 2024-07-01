package com.softuni.mobilele.model;

import com.softuni.mobilele.model.enums.EngineTypeEnum;

public record OfferSummaryDTO(Long id,
                              String description,
                              Integer mileage,
                              EngineTypeEnum engineType) {
}
