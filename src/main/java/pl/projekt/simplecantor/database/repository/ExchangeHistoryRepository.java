package pl.projekt.simplecantor.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.projekt.simplecantor.database.entity.ExchangeHistory;

public interface ExchangeHistoryRepository extends JpaRepository<ExchangeHistory, Integer> {
}
