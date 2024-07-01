package bg.softuni.pathfinder.data;

import bg.softuni.pathfinder.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
