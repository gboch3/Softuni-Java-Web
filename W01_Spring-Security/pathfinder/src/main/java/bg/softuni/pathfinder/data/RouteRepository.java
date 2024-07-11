package bg.softuni.pathfinder.data;

import bg.softuni.pathfinder.model.Category;
import bg.softuni.pathfinder.model.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RouteRepository extends JpaRepository<Route, Long> {
    Optional<Route> findById(long id);

    List<Route> findAllByCategoriesContaining(Category category);
}
