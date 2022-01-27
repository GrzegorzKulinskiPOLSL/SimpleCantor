package pl.projekt.simplecantor.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.projekt.simplecantor.database.entity.ConverterParameter;

public interface ConverterParametersRepository extends JpaRepository<ConverterParameter, String> {
}
