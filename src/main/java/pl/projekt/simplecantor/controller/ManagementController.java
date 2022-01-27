package pl.projekt.simplecantor.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.projekt.simplecantor.database.entity.ConverterParameter;
import pl.projekt.simplecantor.database.entity.Currency;
import pl.projekt.simplecantor.dto.CurrencyDto;
import pl.projekt.simplecantor.dto.ExchangeRateDto;
import pl.projekt.simplecantor.service.management.ConverterService;
import pl.projekt.simplecantor.service.management.CurrencyService;
import pl.projekt.simplecantor.service.management.ExchangeRateService;
import pl.projekt.simplecantor.utility.RestUtility;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("currencies")
@CrossOrigin(origins = "http://localhost:4200")
public class ManagementController {
    private final CurrencyService currencyService;
    private final ExchangeRateService exchangeRateService;
    private final ConverterService converterService;

    @GetMapping("/")
    public List<Currency> getAllCurrencies() {
        return currencyService.getAllCurrency();
    }

    @PostMapping("add")
    public ResponseEntity<String> addCurrency(@RequestBody CurrencyDto dto) {
        String currencyCode = currencyService.addCurrency(dto);

        return new ResponseEntity<>("", RestUtility.getHeader("/currencies/{currencyCode}", currencyCode), HttpStatus.CREATED);
    }

    @PostMapping("/addAllRateApi")
    public ResponseEntity<String> addAllRateFromApi() {
        exchangeRateService.addCurrentRateFromApi();
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PostMapping("/{currencyCode}/addRate")
    @ResponseBody
    public ResponseEntity<String> addRate(
            @PathVariable String currencyCode, @RequestBody ExchangeRateDto dto) {
        dto.setCurrencyCode(currencyCode);
        exchangeRateService.addRate(currencyCode, dto);

        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PostMapping("/{currencyCode}/setParameters")
    @ResponseBody
    public ResponseEntity<String> setParameters(
            @PathVariable String currencyCode, @RequestBody ConverterParameter converterParameters) {
        converterParameters.setCurrencyCode(currencyCode);
        converterService.setParameters(converterParameters);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @GetMapping("/currenciesParameters")
    public List<ConverterParameter> getAllConverterValue() {
        return converterService.getAllSetParameters();
    }
}
