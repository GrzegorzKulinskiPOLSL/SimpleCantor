package pl.projekt.simplecantor.service.management;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import pl.projekt.simplecantor.database.entity.Currency;
import pl.projekt.simplecantor.database.entity.ExchangeRate;
import pl.projekt.simplecantor.database.repository.CurrencyRepository;
import pl.projekt.simplecantor.database.repository.ExchangeRateRepository;
import pl.projekt.simplecantor.dto.ExchangeRateDto;
import pl.projekt.simplecantor.dto.external.ExchangeRateNBP;
import pl.projekt.simplecantor.dto.external.ExchangeRateNBPArrayList;
import pl.projekt.simplecantor.dto.external.RateNBP;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExchangeRateService {

    private final CurrencyRepository currencyRepository;
    private final ExchangeRateRepository exchangeRateRepository;
    private final RestTemplate restTemplate;
    private final ConverterService converterService;

    public void addRate(String currencyCode, ExchangeRateDto dto) {
        currencyRepository.findById(currencyCode).ifPresent(
                currency -> {
                    ExchangeRate exchangeRate = new ExchangeRate(dto.getCurrencySale(), dto.getCurrencyPurchase(), currencyCode);
                    currency.addExchange(exchangeRateRepository.save(exchangeRate));
                    currencyRepository.save(currency);
                }
        );
    }

    public void addCurrentRateFromApi() {
        Optional<ExchangeRateNBP> exchangeRateTables = getExchangeRateTables();
        exchangeRateTables.ifPresent(this::prepareToAdd);
    }


    private Optional<ExchangeRateNBP> getExchangeRateTables() throws ResourceAccessException {
        final String resourceUrl = "http://api.nbp.pl/api/exchangerates/tables/c/?format=json";
        Optional<ExchangeRateNBPArrayList> exchangeRateNBPS
                = Optional.ofNullable(restTemplate.getForObject(resourceUrl, ExchangeRateNBPArrayList.class));

        return exchangeRateNBPS.map(ExchangeRateNBPArrayList::getFirst);
    }

    private void prepareToAdd(ExchangeRateNBP exchangeRateTables) {

        List<Currency> availableCurrency = currencyRepository.findAll();
        saveAll(exchangeRateTables
                .getRates()
                .stream()
                .filter(rateNBP -> rateNBP.isIn(availableCurrency)).collect(Collectors.toList()));
    }

    private void saveAll(List<RateNBP> list) {
        list.forEach(rateNBP -> addRate(rateNBP.getCode(), converterService.calculates(new ExchangeRateDto(rateNBP))));
    }
}
