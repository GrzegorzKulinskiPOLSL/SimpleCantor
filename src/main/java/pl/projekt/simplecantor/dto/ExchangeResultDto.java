package pl.projekt.simplecantor.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.projekt.simplecantor.utility.TypeOperation;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExchangeResultDto {

    TypeOperation typeOfOperation;
    Double rate;
    String currencyCode;
    Double amountGet;


}
