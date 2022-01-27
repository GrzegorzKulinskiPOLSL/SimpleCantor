package pl.projekt.simplecantor.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.projekt.simplecantor.database.entity.Currency;
import pl.projekt.simplecantor.database.entity.ExchangeRate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyCurrentRate {

    String currencyName;
    String currencyCode;
    double currencyPurchase;
    double currencySale;

    public CurrencyCurrentRate(Currency currency, ExchangeRate exchangeRate) {
        this.currencyName = currency.getName();
        this.currencyCode = currency.getCurrencyCode();
        this.currencySale = exchangeRate.getCurrencySale();
        this.currencyPurchase = exchangeRate.getCurrencyPurchase();
    }
}
