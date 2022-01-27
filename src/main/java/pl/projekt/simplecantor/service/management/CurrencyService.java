package pl.projekt.simplecantor.service.management;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.projekt.simplecantor.database.entity.Currency;
import pl.projekt.simplecantor.database.repository.CurrencyRepository;
import pl.projekt.simplecantor.dto.CurrencyDto;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CurrencyService {
    private final CurrencyRepository currencyRepository;

    public String addCurrency(CurrencyDto dto) {
        currencyRepository.findById(dto.getCurrencyCode()).ifPresent(x -> {
            throw new RuntimeException("Currency code already exist");
        });

        return currencyRepository.save(
                new Currency(dto.getCurrencyCode(), dto.getName())
        ).getCurrencyCode();
    }

    public List<Currency> getAllCurrency() {
        return currencyRepository.findAll();
    }

}
