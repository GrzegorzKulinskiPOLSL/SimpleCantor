package pl.projekt.simplecantor.database.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConverterParameter {

    @Id
    String currencyCode;
    Double bid;
    Double ask;

}
