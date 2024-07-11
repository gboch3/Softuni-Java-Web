package bg.softuni.pathfinder.data;

import bg.softuni.pathfinder.model.Category;
import bg.softuni.pathfinder.model.CategoryType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findCategoryByName(CategoryType categoryName);
}
