package pl.projekt.simplecantor.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import pl.projekt.simplecantor.dto.enums.TypeOperation;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class ExchangeHistoryDto {

    private String currencyCode;

    LocalDate exchangeDate;

    TypeOperation typeOperation;

    private double amount;

    private double rate;

}
