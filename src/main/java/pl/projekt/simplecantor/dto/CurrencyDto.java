package pl.projekt.simplecantor.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CurrencyDto {
    private String currencyCode;
    private String name;

}
