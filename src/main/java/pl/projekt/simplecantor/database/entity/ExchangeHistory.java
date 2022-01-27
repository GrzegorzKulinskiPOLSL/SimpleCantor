package pl.projekt.simplecantor.database.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import pl.projekt.simplecantor.dto.enums.TypeOperation;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
public class ExchangeHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String currencyCode;

    LocalDate exchangeDate;

    TypeOperation typeOperation;

    private double amount;

    private double rate;

    public ExchangeHistory(String currencyCode, TypeOperation typeOperation, double amount, double rate) {
        this.currencyCode = currencyCode;
        this.exchangeDate = LocalDate.now();
        this.typeOperation = typeOperation;
        this.amount = amount;
        this.rate = rate;
    }

}
