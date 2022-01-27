package pl.projekt.simplecantor.service.client;

import lombok.RequiredArgsConstructor;
import org.decimal4j.util.DoubleRounder;
import org.springframework.stereotype.Service;
import pl.projekt.simplecantor.database.entity.ExchangeHistory;
import pl.projekt.simplecantor.database.entity.ExchangeRate;
import pl.projekt.simplecantor.database.repository.CurrencyRepository;
import pl.projekt.simplecantor.database.repository.ExchangeHistoryRepository;
import pl.projekt.simplecantor.database.repository.ExchangeRateRepository;
import pl.projekt.simplecantor.dto.CurrencyCurrentRate;
import pl.projekt.simplecantor.dto.ExchangeHistoryDto;
import pl.projekt.simplecantor.dto.ExchangeRequestDto;
import pl.projekt.simplecantor.dto.ExchangeResultDto;
import pl.projekt.simplecantor.utility.TypeOperation;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final CurrencyRepository currencyRepository;
    private final ExchangeRateRepository exchangeRateRepository;
    private final ExchangeHistoryRepository exchangeHistoryRepository;

    public List<CurrencyCurrentRate> showAllCurrency() {
        return currencyRepository.findAll()
                .stream()
                .filter(currency -> !currency.getExchangesRates().isEmpty())
                .map(x -> {
                    int size = x.getExchangesRates().size() - 1;
                    return new CurrencyCurrentRate(x, x.getExchangesRates().get(size));
                }).collect(Collectors.toList());
    }

    public ExchangeResultDto doExchange(String currencyCode, ExchangeRequestDto dto) {
        Optional<ExchangeRate> ex = exchangeRateRepository.findFirstByCurrencyCodeOrderByAddDateDesc(currencyCode);
        double amount = dto.getAmount();
        double rate = getRateBasedOfOperation(dto.getOperationType(), ex.get());

        addHistory(new ExchangeHistory(currencyCode, dto.getOperationType(), amount, rate));

        return new ExchangeResultDto(dto.getOperationType(), rate, currencyCode, calculateExchange(rate, amount));
    }

    private void addHistory(ExchangeHistory exchangeHistory) {
        exchangeHistoryRepository.save(exchangeHistory);
    }

    private double calculateExchange(double rate, double amount) {
        return DoubleRounder.round(rate * amount, 2);
    }

    private double getRateBasedOfOperation(TypeOperation operation, ExchangeRate exchangeRate) {
        return operation.equals(TypeOperation.BUY) ? exchangeRate.getCurrencySale() : exchangeRate.getCurrencyPurchase();
    }

    public List<ExchangeHistoryDto> getHistory() {
        return exchangeHistoryRepository.findAll()
                .stream()
                .map(x -> new ExchangeHistoryDto(
                        x.getCurrencyCode(),
                        x.getExchangeDate(),
                        x.getTypeOperation(),
                        x.getAmount(), x.getRate()
                ))
                .collect(Collectors.toList());
    }
}
