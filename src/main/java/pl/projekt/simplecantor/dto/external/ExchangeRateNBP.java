package pl.projekt.simplecantor.dto.external;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ExchangeRateNBP {

    String table;
    String no;
    String tradingDate;
    String effectiveDate;
    List<RateNBP> rates;

    public ExchangeRateNBP(String table, String no, String tradingDate, String effectiveDate, List<RateNBP> rates) {
        this.table = table;
        this.no = no;
        this.tradingDate = tradingDate;
        this.effectiveDate = effectiveDate;
        this.rates = rates;
    }
}

