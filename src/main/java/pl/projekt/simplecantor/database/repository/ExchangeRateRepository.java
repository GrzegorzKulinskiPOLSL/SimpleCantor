package pl.projekt.simplecantor.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.projekt.simplecantor.database.entity.ExchangeRate;

import java.util.Optional;

public interface ExchangeRateRepository extends JpaRepository<ExchangeRate, Integer> {
    Optional<ExchangeRate> findFirstByCurrencyCodeOrderByAddDateDesc(String currencyCode);
}
