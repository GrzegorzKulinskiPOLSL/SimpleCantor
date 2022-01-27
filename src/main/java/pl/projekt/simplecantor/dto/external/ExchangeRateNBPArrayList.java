package pl.projekt.simplecantor.dto.external;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class ExchangeRateNBPArrayList extends ArrayList<ExchangeRateNBP> {

    public ExchangeRateNBP getFirst() {
        return get(0);
    }
}
