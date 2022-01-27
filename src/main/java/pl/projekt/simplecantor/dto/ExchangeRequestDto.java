package pl.projekt.simplecantor.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.projekt.simplecantor.dto.enums.TypeOperation;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExchangeRequestDto {

    private Double amount;
    private TypeOperation operationType;


}
