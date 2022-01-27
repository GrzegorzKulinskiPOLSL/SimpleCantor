package pl.projekt.simplecantor.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.projekt.simplecantor.dto.external.RateNBP;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExchangeRateDto {
    private Double currencySale;
    private Double currencyPurchase;
    String currencyCode;

    public ExchangeRateDto(RateNBP rateNBP) {
        currencyCode = rateNBP.getCode();
        currencyPurchase = rateNBP.getAsk();
        currencySale = rateNBP.getBid();
    }
}
