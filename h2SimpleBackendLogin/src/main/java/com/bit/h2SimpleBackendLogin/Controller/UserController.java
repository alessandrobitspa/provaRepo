package com.bit.h2SimpleBackendLogin.Controller;

import com.bit.h2SimpleBackendLogin.model.UserModel;
import com.bit.h2SimpleBackendLogin.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4230")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<UserModel> signup(@RequestParam String username, @RequestParam String password) {
        UserModel user = userService.signup(username, password);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String username, @RequestParam String password) {
        boolean success = userService.login(username, password);
        if (success) {
            return ResponseEntity.ok("Login effettuato con successo!");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Credenziali non valide!");
        }
    }

}