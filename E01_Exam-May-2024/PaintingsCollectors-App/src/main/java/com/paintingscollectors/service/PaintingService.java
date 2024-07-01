package com.paintingscollectors.service;

import com.paintingscollectors.model.dto.AddPaintingDTO;
import com.paintingscollectors.model.entity.Painting;
import com.paintingscollectors.model.entity.Style;
import com.paintingscollectors.model.entity.StyleName;
import com.paintingscollectors.repository.PaintingRepository;
import com.paintingscollectors.repository.StyleRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PaintingService {
    private final StyleRepository styleRepository;
    private final PaintingRepository paintingRepository;

    public PaintingService(StyleRepository styleRepository, PaintingRepository paintingRepository) {
        this.styleRepository = styleRepository;
        this.paintingRepository = paintingRepository;
    }

    public Map<StyleName, List<Painting>> findAllByCategory() {
        Map<StyleName, List<Painting>> result = new HashMap<>();

        List<Style> allStyles = styleRepository.findAll();

        for (Style style : allStyles) {
            List<Painting> paintings = paintingRepository.findAllByStyle(style);

            result.put(style.getName(), paintings);
        }

        return result;
    }

    public List<Painting> findFavourites(Long id) {
        //TODO
        return null;
    }

    public boolean create(AddPaintingDTO data) {
        //TODO
        return false;
    }

    public void addToFavourites(Long id, long paintingId) {
        //TODO
    }
}
