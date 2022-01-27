package pl.projekt.simplecantor.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import pl.projekt.simplecantor.dto.enums.Role;

import java.util.List;

@Data
@AllArgsConstructor
public class RegisterUser {

    private String username;
    private String password;
    private List<Role> roles;
}
