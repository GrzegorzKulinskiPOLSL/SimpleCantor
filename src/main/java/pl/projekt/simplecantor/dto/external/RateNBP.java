package pl.projekt.simplecantor.dto.external;

import lombok.Data;
import lombok.NoArgsConstructor;
import pl.projekt.simplecantor.database.entity.Currency;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
public class RateNBP implements Serializable {
    String currency;
    String code;
    double bid;
    double ask;


    public boolean isIn(List<Currency> list) {
        return list.stream().anyMatch(currency -> Objects.equals(currency.getCurrencyCode(), this.code));
    }

    public RateNBP(String currency, String code, double bid, double ask) {
        this.currency = currency;
        this.code = code;
        this.bid = bid;
        this.ask = ask;
    }
}
