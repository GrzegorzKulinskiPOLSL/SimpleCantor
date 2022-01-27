package pl.projekt.simplecantor.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.projekt.simplecantor.dto.RegisterUser;
import pl.projekt.simplecantor.dto.UserLogin;
import pl.projekt.simplecantor.service.UserService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController {
   private final UserService userService;

    @PostMapping("login")
    public ResponseEntity<?> authenticateUser(@RequestBody UserLogin dto) {
        return ResponseEntity.ok(userService.login(dto));
    }

    @PostMapping("register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterUser dto) {
        return userService.createUser(dto);
    }
}
