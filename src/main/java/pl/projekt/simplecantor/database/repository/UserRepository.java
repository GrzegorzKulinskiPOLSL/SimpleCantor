package pl.projekt.simplecantor.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.projekt.simplecantor.database.entity.CantorUser;
import pl.projekt.simplecantor.dto.enums.Role;

import java.util.Optional;

public interface UserRepository extends JpaRepository<CantorUser, Long> {
    Optional<CantorUser> findUserByUsername(String username);
    Boolean existsByUsername(String username);
    Long countAllByRolesContaining(Role role);
}
