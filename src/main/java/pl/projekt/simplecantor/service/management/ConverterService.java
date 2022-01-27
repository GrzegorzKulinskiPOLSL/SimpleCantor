package pl.projekt.simplecantor.service.management;

import lombok.RequiredArgsConstructor;
import org.decimal4j.util.DoubleRounder;
import org.springframework.stereotype.Service;
import pl.projekt.simplecantor.database.entity.ConverterParameter;
import pl.projekt.simplecantor.database.repository.ConverterParametersRepository;
import pl.projekt.simplecantor.dto.ExchangeRateDto;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ConverterService {

    private final ConverterParametersRepository repository;

    public ConverterParameter setParameters(ConverterParameter converterParameters) {
        return repository.save(checkEmptyValues(converterParameters));
    }

    public ExchangeRateDto calculates(ExchangeRateDto dto) {
        ConverterParameter converterParameters = getParametersOf(dto.getCurrencyCode());

        double sell = converterParameters.getBid();
        double buy = converterParameters.getAsk();
        double beforePurchase = dto.getCurrencyPurchase();
        double beforeSale = dto.getCurrencySale();

        dto.setCurrencySale(DoubleRounder.round(quickPercentage(beforeSale, sell) + beforeSale, 4));

        dto.setCurrencyPurchase(DoubleRounder.round(quickPercentage(beforePurchase, buy) + beforePurchase, 4));

        return dto;
    }

    public List<ConverterParameter> getAllSetParameters() {
        return repository.findAll();
    }


    private double quickPercentage(double actual, double percentage) {
        return DoubleRounder.round((actual * percentage / 100.0), 4);
    }

    private ConverterParameter getParametersOf(String id) {
        Optional<ConverterParameter> optionalConverterParameters = repository.findById(id);

        return optionalConverterParameters.orElseGet(() ->
                repository.findById("ALL").
                        orElseGet(() -> setParameters(new ConverterParameter("ALL", 1.0, 1.0))));
    }

    private ConverterParameter checkEmptyValues(ConverterParameter testedValue) {
        if (testedValue.getAsk() == null || testedValue.getBid() == null) {
            ConverterParameter last = getParametersOf(testedValue.getCurrencyCode());
            testedValue.setAsk(Optional.ofNullable(testedValue.getAsk()).orElse(last.getAsk()));
            testedValue.setBid(Optional.ofNullable(testedValue.getBid()).orElse(last.getBid()));
        }
        return testedValue;
    }
}
