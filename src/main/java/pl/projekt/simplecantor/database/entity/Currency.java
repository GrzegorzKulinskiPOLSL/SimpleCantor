package pl.projekt.simplecantor.database.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Currency {

    @Id
    private String currencyCode;

    private String name;

    LocalDate createDate;

    @OneToMany(fetch = FetchType.LAZY)
    private List<ExchangeRate> exchangesRates;

    public Currency(String currencyCode, String name) {
        this.currencyCode = currencyCode;
        this.name = name;
        this.createDate = LocalDate.now();
        this.exchangesRates = new ArrayList<>();
    }

    public void addExchange(ExchangeRate ex) {
        exchangesRates.add(ex);
    }
}
