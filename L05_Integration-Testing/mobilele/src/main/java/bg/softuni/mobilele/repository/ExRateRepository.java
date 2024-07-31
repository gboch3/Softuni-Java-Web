package bg.softuni.mobilele.repository;

import bg.softuni.mobilele.model.entity.ExRateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ExRateRepository extends JpaRepository<ExRateEntity, Long> {
    Optional<ExRateEntity> findByCurrency(String currency);
}
