package com.womensafety.Womensafety.Controller;

import com.womensafety.Womensafety.Model.LoginRequest;
import com.womensafety.Womensafety.Model.User;
import com.womensafety.Womensafety.Service.JWTservice;
import com.womensafety.Womensafety.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.management.remote.JMXAuthenticator;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    private final JWTservice jwTservice;

    private final AuthenticationManager authenticationManager;

    public UserController(UserService userService,AuthenticationManager authenticationManager,JWTservice jwTservice) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwTservice = jwTservice;
    }

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/signup")
    public ResponseEntity<User> signup(@RequestBody User user) {
        User registeredUser = userService.registerUser(user);
        System.out.println(registeredUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(registeredUser);
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        User registeredUser = userService.registerUser(user);
        return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<?> getUserByUsername(@PathVariable String username) {
        // Your logic to get user by username
        Optional<User> user = userService.findByUsername(username);
        System.out.println(user);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{usersId}")
    public ResponseEntity<?> getUserById(@PathVariable Long usersId) {
        // Your logic to get user by ID
        Optional<User> user = userService.findById(usersId);
        return user.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/listofuser")
    public List<User> getUserList(){
        return userService.getUserList();
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();
        Optional<User> userOpt = userService.findByUsername(username);
        System.out.println("Attempting to login user: " + username);

        if (userOpt.isPresent()) {
            User user = userOpt.get();
            if (passwordEncoder.matches(password, user.getPassword())) {
                return ResponseEntity.ok(user);
            } else {
                // Password mismatch
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }

    @PostMapping("/loginjwt")
    public String loginjwt(@RequestBody User user) {
        Optional<User> userOpt = userService.findByUsername(user.getUsername());
        if (userOpt.isPresent()) {
            User existingUser = userOpt.get();
            if (passwordEncoder.matches(user.getPassword(), existingUser.getPassword())) {
                Authentication authentication = authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
                );
                if (authentication.isAuthenticated()) {
                    return jwTservice.GenerateToken(user.getUsername());
                }
            }
        }
        return "failed";
    }

}
