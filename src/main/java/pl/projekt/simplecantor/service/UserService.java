package pl.projekt.simplecantor.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.projekt.simplecantor.database.entity.CantorUser;
import pl.projekt.simplecantor.database.repository.UserRepository;
import pl.projekt.simplecantor.dto.JwtResponse;
import pl.projekt.simplecantor.dto.BasicResponse;
import pl.projekt.simplecantor.dto.RegisterUser;
import pl.projekt.simplecantor.dto.UserLogin;
import pl.projekt.simplecantor.dto.enums.Role;
import pl.projekt.simplecantor.security.model.UserDetailsImpl;
import pl.projekt.simplecantor.utility.JwtUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final JwtUtils jwtUtils;

    public ResponseEntity<?> createUser(RegisterUser signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new BasicResponse("Error: Username is already taken!"));
        }

        CantorUser cantorUser = new CantorUser();
        cantorUser.setUsername(signUpRequest.getUsername());
        cantorUser.setPassword(encoder.encode(signUpRequest.getPassword()));
        if (userRepository.countAllByRolesContaining(Role.ROLE_ADMIN) == 0) {
            cantorUser.setRoles(Collections.singletonList(Role.ROLE_ADMIN));
        } else {
            cantorUser.setRoles(Collections.singletonList(Role.ROLE_USER));
        }
        userRepository.save(cantorUser);

        return ResponseEntity.ok(new BasicResponse("User registered successfully!"));
    }

    public JwtResponse login(UserLogin loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), roles);
    }
}
