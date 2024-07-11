package bg.softuni.pathfinder.service;

import bg.softuni.pathfinder.data.CategoryRepository;
import bg.softuni.pathfinder.data.RouteRepository;
import bg.softuni.pathfinder.model.Category;
import bg.softuni.pathfinder.model.CategoryType;
import bg.softuni.pathfinder.model.Picture;
import bg.softuni.pathfinder.model.Route;
import bg.softuni.pathfinder.model.dto.AddRouteDTO;
import bg.softuni.pathfinder.model.dto.RouteInfoDTO;
import bg.softuni.pathfinder.model.dto.RouteShortInfoDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class RouteService {

    private final RouteRepository routeRepository;
    private final Random random = new Random();
    private final ModelMapper modelMapper;
    private final UserHelperService userHelperService;
    private final CategoryRepository categoryRepository;

    @Transactional
    public List<RouteShortInfoDTO> getAll() {
        return routeRepository.findAll()
                .stream()
                .map(this::mapToShortInfo)
                .toList();
    }

    @Transactional
    public RouteShortInfoDTO getRandomRoute() {
        long routeCount = routeRepository.count();
        long randomId = random.nextLong(routeCount) + 1;
        Optional<Route> route = routeRepository.findById(randomId);
        if (route.isEmpty()) {
            // throw exception; return empty
            throw new RuntimeException();
        }
        return mapToShortInfo(route.get());
    }

    private RouteShortInfoDTO mapToShortInfo(Route route) {
        RouteShortInfoDTO dto = modelMapper.map(route, RouteShortInfoDTO.class);

        Optional<Picture> first = route.getPictures().stream().findFirst();
        first.ifPresent(picture -> dto.setImageUrl(picture.getUrl()));

        return dto;
    }

    public RouteInfoDTO getRoute(long routeId) {
        Optional<Route> route = routeRepository.findById(routeId);

        return modelMapper.map(route, RouteInfoDTO.class);
    }

    public boolean add(AddRouteDTO data, MultipartFile gpxFile) throws IOException {
        Route route = modelMapper.map(data, Route.class);

        Category category = this.categoryRepository.findCategoryByName(data.getCategoryType());
        route.getCategories().add(category);
        route.setAuthor(userHelperService.getUser());
        this.routeRepository.save(route);


        try (InputStream inputStream = gpxFile.getInputStream()) {
            route.setGpxCoordinates(new String(inputStream.readAllBytes(), StandardCharsets.UTF_8));

            // originalFilename, fileLocation ->  /uploads/{userId}/{fileId}.{ext}
            Path destinationFile = Paths
                    .get("src", "main", "resources", "static", "uploads", String.valueOf(userHelperService.getUser().getId()), route.getId() + ".gpx")
                    .normalize();
                  //.toAbsolutePath()

            if (!Files.exists(destinationFile)) {
                Files.createDirectory(destinationFile);
            }
            Files.copy(inputStream, destinationFile,
                    StandardCopyOption.REPLACE_EXISTING);

            inputStream.close();
            gpxFile.getInputStream().close();
        }

        Route saved = this.routeRepository.save(route);

        if(saved.getId() > 0) {
            data.setId(route.getId());
            return true;
        }

        return false;
    }

    public List<RouteShortInfoDTO> getAllByCategory(CategoryType name) {
        Category category = this.categoryRepository.findCategoryByName(name);
        List<RouteShortInfoDTO> allByCategory = this.routeRepository
                .findAllByCategoriesContaining(category)
                .stream()
                .map(this::mapToShortInfo)
                .toList();

        return allByCategory;
    }
}
