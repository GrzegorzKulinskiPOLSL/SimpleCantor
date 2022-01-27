package pl.projekt.simplecantor.database.entity;

import lombok.Data;
import pl.projekt.simplecantor.dto.enums.Role;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class CantorUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String password;

    @ElementCollection
    private List<Role> roles = new ArrayList<>();


}
