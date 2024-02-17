package org.example.controller;

import org.example.dto.LoginRequest;
import org.example.dto.SignupRequest;
import org.example.entity.User;
import org.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
    @Autowired
    UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        Optional<User> userOptional = userRepository.findByUsername(loginRequest.getUsername());

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (user.getPassword().equals(loginRequest.getPassword())) {

                return ResponseEntity.ok(Collections.singletonMap("message", "Login successful"));//ResponseEntity.ok(user);
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
    }

        @PostMapping("/signup")
        public ResponseEntity<?> registerUser(@RequestBody SignupRequest signupRequest) {
            // Check if the user already exists
            if (userRepository.findByUsername(signupRequest.getUsername()).isPresent()) {
                return ResponseEntity.badRequest().body("Error: Username is already taken!");
            }

            // Create a new user's account
            User user = new User();
            user.setUsername(signupRequest.getUsername());
            user.setPassword(signupRequest.getPassword());
            user.setFirstName(signupRequest.getFirstName());
            user.setLastName(signupRequest.getLastName());
            user.setDob(signupRequest.getDob());
            user.setRating(signupRequest.getRating());

            userRepository.save(user);

            return ResponseEntity.ok("User registered successfully!");
        }




    @GetMapping("users")
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @GetMapping("users/{id}")
    public User getUsers(@PathVariable String id) {
        Optional<User> optionalUser= userRepository.findById(Integer.parseInt(id));

        if(!optionalUser.isEmpty()) {
            return optionalUser.get();
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping("users")
    public User saveUser(User user) {
        return userRepository.save(user);
    }


}
