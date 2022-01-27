package pl.projekt.simplecantor.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.projekt.simplecantor.database.entity.Currency;

public interface CurrencyRepository extends JpaRepository<Currency, String> {
}
