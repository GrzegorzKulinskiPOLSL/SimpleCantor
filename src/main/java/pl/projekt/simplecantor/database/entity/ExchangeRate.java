package pl.projekt.simplecantor.database.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class ExchangeRate {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Double currencySale;

    private Double currencyPurchase;

    private String currencyCode;

    private LocalDateTime addDate;

    public ExchangeRate(Double currencySale, Double currencyPurchase, String currencyCode) {
        this.currencySale = currencySale;
        this.currencyPurchase = currencyPurchase;
        this.addDate = LocalDateTime.now();
        this.currencyCode = currencyCode;
    }
}
